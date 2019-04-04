package es.escuelaestech.chopo.examenandroidjesusperez;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText input_name, input_age, input_deed;
    TextInputLayout inputLayoutName, inputLayoutAge, inputLayoutDeed;
    ListView eList;
    Enemies enemiesList;
    ArrayList<Enemy> enemies;
    ArrayAdapter<Enemy> eAdapter;
    String datamined;
    String enemiesJson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        inicialize();
        final Button button = findViewById(R.id.add_btn);
        final Button saveButton = findViewById(R.id.save_btn);
        final Button loadButton = findViewById(R.id.load_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = input_name.getText().toString();
                String age = input_age.getText().toString();
                String deed = input_deed.getText().toString();
                if(submitEnemy()){
                    Enemy enemy = new Enemy(name, age, deed);
                    enemiesList.addEnemy(enemy);
                    //enemies.add(enemy);
                    eAdapter.add(enemy);
                    eAdapter.notifyDataSetChanged();
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enemiesJson= enemiesList.toJSON();
                SharedPreferences prefs =
                        getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("lista", enemiesJson);
                editor.commit();
            }
        });
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                SharedPreferences prefs =
                        getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

                    enemiesList.fromJSON(prefs.getString("lista", ""));
            }
        });

        eList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Enemy currentEnemy = eAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, EnemyDetail.class);
                String nameToSend = currentEnemy.getName();
                String ageToSend = currentEnemy.getAge();
                String deedToSend = currentEnemy.getDeed();
                intent.putExtra("ENEMY_NAME", nameToSend );
                intent.putExtra("ENEMY_AGE", ageToSend );
                intent.putExtra("ENEMY_DEED", deedToSend );
                startActivity(intent);
                Toast.makeText(eAdapter.getContext(),
                        "Detalles de: \n" + currentEnemy.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inicialize(){
        enemies= new ArrayList<>();
        enemiesList = new Enemies();
        input_name=findViewById(R.id.input_name);
        input_age=findViewById(R.id.input_age);
        input_deed=findViewById(R.id.input_deed);
        inputLayoutName = findViewById(R.id.input_layout_name);
        inputLayoutDeed = findViewById(R.id.input_layout_deed);
        inputLayoutAge = findViewById(R.id.input_layout_age);
        eList = findViewById(R.id.enemies_list);
        eAdapter= new EnemiesAdapter(this, enemies);
        eList.setAdapter(eAdapter);
        enemiesJson="";


    }

    private boolean submitEnemy(){
        boolean validated=false;
        if( validateName() && validateAge() && validateDeed()){
            validated= true;
        }
        return validated;
    }

    private boolean validateName(){
        String name = input_name.getText().toString().trim();
        if (name.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.err_msg_name, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateAge(){
        String age = input_age.getText().toString().trim();
        if (age.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.err_msg_age_empty, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if(Integer.parseInt(age)<0 && Integer.parseInt(age)>150){
                Toast.makeText(getApplicationContext(), getString(R.string.err_msg_age), Toast.LENGTH_SHORT).show();
                return false;
            }else{
                inputLayoutAge.setErrorEnabled(false);
            }
        }

        return true;
    }

    private boolean validateDeed(){
        String deed = input_deed.getText().toString().trim();
        if (deed.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.err_msg_deed, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            inputLayoutDeed.setErrorEnabled(false);
        }

        return true;
    }
}
