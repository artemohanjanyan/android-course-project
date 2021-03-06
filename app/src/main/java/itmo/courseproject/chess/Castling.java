package itmo.courseproject.chess;


import java.util.List;

public final class Castling extends Move {

    private static final byte MOVE_ID;
    private static final MoveFactory f = new MoveFactory() {
        @Override
        public Move create(byte... bytes) {
            boolean color = (bytes[0] >> 6) == 0;

            // there Point used to store two start columns, row <- king column, column <- rook column
            Point startColumns = Move.byteToField(bytes[0]);

            return new Castling(color, startColumns.row, startColumns.column);
        }
    };

    static {
        MOVE_ID = (byte) factoryMap.size();
        factoryMap.add(f);
    }

    private final boolean color;
    private final int startRookColumn;

    Castling(boolean color, int startKingColumn, int startRookColumn) {
        super(color ? 7 : 0, startKingColumn, color ? 7 : 0, startKingColumn > startRookColumn ? 2 : 6);
        this.color = color;
        this.startRookColumn = startRookColumn;
    }

    @Override
    void execute(Desk desk) {
        desk.d[startRow][startColumn] = null;
        desk.d[startRow][startRookColumn] = null;

        int d = startColumn > startRookColumn ? 1 : -1;
        desk.d[endRow][endColumn] = color ? Figure.BLACK_KING : Figure.WHITE_KING;
        desk.d[endRow][endColumn + d] = color ? Figure.BLACK_ROOK : Figure.WHITE_ROOK;
    }

    @Override
    public byte[] toBytes() {
        return new byte[]{MOVE_ID, (byte) (Move.fieldToByte(startColumn, startRookColumn) + (color ? 1 << 6 : 0))};
    }

    @Override
    public List<Point> getChangedFields() {
        List<Point> ret = super.getChangedFields();
        ret.add(new Point(startRow, startRookColumn));
        ret.add(new Point(endRow, endColumn + (startColumn > startRookColumn ? 1 : -1)));
        return ret;
    }

    @Override
    public String getNotation(Desk desk) {
        return startColumn < startRookColumn ? "O-O" : "O-O-O";
    }
}
