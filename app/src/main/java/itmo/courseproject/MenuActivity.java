package itmo.courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = (Button) findViewById(R.id.local_game_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, LocalGameConfigurationActivity.class)
                //Intent intent = new Intent(MenuActivity.this, LocalGameActivity.class)
        //                .putExtra(GameActivity.GAME, GameActivity.CHESS_960)
        //                .putExtra(GameActivity.SEED, new Random().nextLong())
                        ;
                startActivity(intent);
            }
        });

        button = (Button) findViewById(R.id.bt_game_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DeviceChooser.class);
                startActivity(intent);
            }
        });
    }
}
