package com.example.bspicn.badiapp;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.bspicn.badiapp.dal.BadiDao;
import com.example.bspicn.badiapp.model.Badi;


public class MainActivity extends AppCompatActivity {
    Button button2;
    Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button3 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button2);
        setTitle("Übersicht");
        addBadisToClickableList();

        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                button2.setClickable(true);
                button3.setClickable(false);
                startActivity(intent);
            }
        });
    }

    private void addBadisToClickableList(){
        ListView badis = findViewById(R.id.badiliste);
        ArrayAdapter<Badi> badiAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        badiAdapter.addAll(BadiDao.getAll());
        badis.setAdapter(badiAdapter);

        AdapterView.OnItemClickListener mListClickedHandler = new
        AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView parent, View v, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(), BadiDetailsActivity.class);
                Badi selected = (Badi)parent.getItemAtPosition(position);
                intent.putExtra("badiId", selected.getId());
                intent.putExtra("badiName", selected.getName());
        startActivity(intent);
            } };
        badis.setOnItemClickListener(mListClickedHandler);
    }
}
