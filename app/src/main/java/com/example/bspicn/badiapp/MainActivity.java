package com.example.bspicn.badiapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import com.android.volley.toolbox.Volley;

import com.example.bspicn.badiapp.dal.onBadiResponseListener;
import com.example.bspicn.badiapp.model.Badi;
import com.example.bspicn.badiapp.dal.BadiDao;

public class MainActivity extends AppCompatActivity implements onBadiResponseListener {

    private int badiId;
    private String badiName;
    public int i;

    Spinner kantoneSpinner;

    Button button2;
    Button button3;

    ArrayAdapter<Badi> badiAdapter;
    ArrayAdapter<Badi> kantonAdapter;
    ArrayAdapter<String> ortAdapter;
    List<Badi> allBadis;

    ListView badis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button3 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button2);
        setTitle("Übersicht");
        addBadisToClickableList();
        Intent intent = getIntent();
        badiId = intent.getIntExtra("badiId", 0);
        badiName = intent.getStringExtra("badiName");

        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                button2.setClickable(true);
                button3.setClickable(false);
                startActivity(intent);
            }
        });

        kantoneSpinner = findViewById(R.id.kantone);
        final ArrayAdapter<String> kantonAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Kantone));
        kantoneSpinner.setAdapter(kantonAdapter);

        kantoneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedkanton;

                //await for ascny function
                if (allBadis == null) {
                    return;
                }

                badiAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
                MainActivity.this.kantonAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
                badiAdapter.addAll(allBadis);

                switch (position) {
                    case 0:
                        onSuccess(allBadis);
                        break;
                    case 1:
                        selectedkanton = "AG";
                        kanton(selectedkanton);
                        break;
                    case 2:
                        selectedkanton = "AR";
                        kanton(selectedkanton);
                        break;
                    case 3:
                        selectedkanton = "AI";
                        kanton(selectedkanton);
                        break;
                    case 4:
                        selectedkanton = "BL";
                        kanton(selectedkanton);
                        break;
                    case 5:
                        selectedkanton = "BS";
                        kanton(selectedkanton);
                        break;
                    case 6:
                        selectedkanton = "BE";
                        kanton(selectedkanton);
                        break;
                    case 7:
                        selectedkanton = "FR";
                        kanton(selectedkanton);
                        break;
                    case 8:
                        selectedkanton = "GE";
                        kanton(selectedkanton);
                        break;
                    case 9:
                        selectedkanton = "GL";
                        kanton(selectedkanton);
                        break;
                    case 10:
                        selectedkanton = "GR";
                        kanton(selectedkanton);
                        break;
                    case 11:
                        selectedkanton = "JU";
                        kanton(selectedkanton);
                        break;
                    case 12:
                        selectedkanton = "LU";
                        kanton(selectedkanton);
                        break;
                    case 13:
                        selectedkanton = "NE";
                        kanton(selectedkanton);
                        break;
                    case 14:
                        selectedkanton = "NW";
                        kanton(selectedkanton);
                        break;
                    case 15:
                        selectedkanton = "OW";
                        kanton(selectedkanton);
                        break;
                    case 16:
                        selectedkanton = "SH";
                        kanton(selectedkanton);
                        break;
                    case 17:
                        selectedkanton = "SZ";
                        kanton(selectedkanton);
                        break;
                    case 18:
                        selectedkanton = "SO";
                        kanton(selectedkanton);
                        break;
                    case 19:
                        selectedkanton = "SG";
                        kanton(selectedkanton);
                        break;
                    case 20:
                        selectedkanton = "TI";
                        kanton(selectedkanton);
                        break;
                    case 21:
                        selectedkanton = "TG";
                        kanton(selectedkanton);
                        break;
                    case 22:
                        selectedkanton = "UR";
                        kanton(selectedkanton);
                        break;
                    case 23:
                        selectedkanton = "VD";
                        kanton(selectedkanton);
                        break;
                    case 24:
                        selectedkanton = "VS";
                        kanton(selectedkanton);
                        break;
                    case 25:
                        selectedkanton = "ZH";
                        kanton(selectedkanton);
                        break;
                    case 26:
                        selectedkanton = "ZG";
                        kanton(selectedkanton);
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println(parent);
            }
        });
    }

    private void addBadisToClickableList() {
        BadiDao.getAll(Volley.newRequestQueue(getApplicationContext()), this);
    }

    public void onSuccess(List<Badi> badisReceived) {
        badis = findViewById(R.id.badiliste);
        allBadis = badisReceived;
        ArrayAdapter<Badi> badiAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        badiAdapter.addAll(badisReceived);
        badis.setAdapter(badiAdapter);
        AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView parent, View v, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), BadiDetailsActivity.class);
                        Badi selected = (Badi) parent.getItemAtPosition(position);
                        intent.putExtra("badiId", selected.getId());
                        intent.putExtra("badiName", selected.getName());
                        intent.putExtra("ort", selected.getOrt());
                        startActivity(intent);
                    }
                };
        badis.setOnItemClickListener(mListClickedHandler);
    }

    private void kanton(String selectedkanton) {

        final  ArrayList<String> ortStringList = new ArrayList<>();
        final ArrayList<Badi> ortListe = new ArrayList<>();
        final ArrayAdapter<Badi> ortListeAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);

        ortStringList.add("Ort Wählen");

        final Spinner ortspinner = findViewById(R.id.ort);
        int j = 0;
        int i = 0;
        for (final Badi badi : allBadis) {
            String kantones = badi.getKanton();
            if (kantones.equals(selectedkanton)) {
                kantonAdapter.add(badiAdapter.getItem(i));
                badis = findViewById(R.id.badiliste);
                badis.setAdapter(kantonAdapter);
                j++;

                //OrtSpinner
                ortStringList.add(badi.getOrt());
                ortListe.add(badi);


                ortAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ortStringList);
                ortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ortspinner.setAdapter(ortAdapter);

                ortspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    //OrtSpinnerListeItems
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if (id == 0) {
                            return;
                        }
                        ortListeAdapter.clear();

                        for (Badi ortBadi: allBadis){
                            String ortBadis = ortBadi.getOrt();

                            if(ortBadis.equals(ortStringList.get(position))){
                                ortListeAdapter.add(ortBadi);
                            }
                        }
                        badis.setAdapter(ortListeAdapter);
                    }
                    @Override
                    public void onNothingSelected(AdapterView <?> parent) {
                    }
                });
            }
            i++;
        }

        if(j==0){
            kantonAdapter.clear();
            badis.setAdapter(kantonAdapter);
        }
    }
}

