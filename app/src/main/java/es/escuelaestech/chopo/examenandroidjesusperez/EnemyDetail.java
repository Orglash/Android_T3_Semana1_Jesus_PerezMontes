package es.escuelaestech.chopo.examenandroidjesusperez;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EnemyDetail extends AppCompatActivity {
    TextView name;
    TextView age;
    TextView deed;
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_detail);
        inicialize();
        String nameToGet = intent.getStringExtra("ENEMY_NAME");
        String ageToGet = intent.getStringExtra("ENEMY_AGE");
        String deedToGet = intent.getStringExtra("ENEMY_DEED");

        name.setText(nameToGet);
        age.setText(ageToGet);
        deed.setText(deedToGet);
    }

    private void inicialize(){
        name = findViewById(R.id.show_name);
        age = findViewById(R.id.show_age);
        deed = findViewById(R.id.show_deed);
        intent = getIntent();
    }
}
