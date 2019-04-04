package es.escuelaestech.chopo.examenandroidjesusperez;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Enemies {

    private ArrayList<Enemy> enemiesList;

    public Enemies() {
        this.enemiesList = new ArrayList<>();
    }

    public ArrayList<Enemy> getHousesList() {
        return enemiesList;
    }

    public String toJSON(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public Enemies fromJSON(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Enemies.class);
    }

    public void addEnemy(Enemy enemy) {
        enemiesList.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemiesList.remove(enemy);
    }

    @Override
    public String toString() {
        return "Enemies{" +
                "enemiesList=" + enemiesList +
                '}';
    }
}
