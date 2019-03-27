package es.escuelaestech.chopo.examenandroidjesusperez;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class EnemiesAdapter extends ArrayAdapter<Enemy> {
    public EnemiesAdapter(@NonNull Context context, List<Enemy> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_enemies,
                    parent,
                    false);
        }

        TextView name = convertView.findViewById(R.id.enemy_name);
        TextView age = convertView.findViewById(R.id.enemy_age);
        TextView deed = convertView.findViewById(R.id.enemy_deed);

        Enemy item = getItem(position);

        name.setText(item.getName());
        age.setText(item.getAge());
        deed.setText(item.getDeed());

        return convertView;
    }
}


