package com.example.bspicn.badiapp;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

import com.android.volley.toolbox.Volley;
import com.example.bspicn.badiapp.dal.onBadiResponseListener;
import com.example.bspicn.badiapp.model.Badi;
import com.example.bspicn.badiapp.dal.BadiDao;


//implements onBadiResponseListener
public class MainActivity extends AppCompatActivity implements onBadiResponseListener {

    String URL;
    Spinner kantoneSpinner;
    Button button2;
    Button button3;
    private int badiId;
    private String badiName;

    ListView badis;
    private static final String WIE_WARM_API_URL = "https://www.wiewarm.ch/api/v1/bad.json/";


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


        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                button2.setClickable(true);
                button3.setClickable(false);
                startActivity(intent);
            }
        });
        spinnerKanton();
        spinnerOrt();
        spinnerBadiname();
    }

    private void addBadisToClickableList() {
        BadiDao.getAll(Volley.newRequestQueue(getApplicationContext()), this);
    }

    public void onSuccess(List<Badi> badisReceived) {
        ListView badis = findViewById(R.id.badiliste);
        ArrayAdapter<Badi> badiAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);

        badiAdapter.addAll(badisReceived);
        badis.setAdapter(badiAdapter);

        AdapterView.OnItemClickListener mListClickedHandler = new
                AdapterView.OnItemClickListener() {
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

    private String spinnerKanton() {
        ListView kanton;
        Spinner kantoneSpinner = (Spinner) findViewById(R.id.kantone);
        ArrayAdapter<String> kantonAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Kantone));
        kantonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kantoneSpinner.setAdapter((kantonAdapter));
        String derKanton = kantoneSpinner.getSelectedItem().toString();

        switch (derKanton) {
            case "Aargau":
                return "AG";
            case "Appenzell Ausserrhoden":
                return "AR";
            case "Appenzell Innerrhoden":
                return "AI";
            case "Basel-Landschaft":
                return "BL";
            case "Basel-Stadt":
                return "BS";
            case "Bern":
                return "BE";
            case "Freiburg":
                return "FR";
            case "Genf":
                return "GE";
            case "Glarus":
                return "GL";
            case "Graubünden":
                return "GR";
            case "Jura":
                return "JU";
            case "Luzern":
                return "LU";
            case "Neuenburg":
                return "NE";
            case "Nidwalden ":
                return "NW";
            case "Obwalden":
                return "OW";
            case "Schaffhausen":
                return "SH";
            case "Schwyz":
                return "SZ";
            case "Soloturn":
                return "SO";
            case "St. Gallen":
                return "SG";
            case "Tessin":
                return "TI";
            case "Thurgau":
                return "TG";
            case "Uri":
                return "UR";
            case "Waadt":
                return "VD";
            case "Wallis":
                return "VS";
            case "Zürich":
                return "ZH";
            case "Zug":
                return "ZG";
            default:
                break;
        }
        /*
         * !!liste mit allen Badis nach kanton sortieren!!
         * */
        return "";
    }

    private void spinnerOrt() {
        Spinner ortspinner = (Spinner) findViewById(R.id.ort);
        ArrayAdapter<String> ortAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Ort));
        ortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ortspinner.setAdapter((ortAdapter));

    }

    private void spinnerBadiname() {

        Spinner badinamespinner = (Spinner) findViewById(R.id.Badiname);
        ArrayAdapter<String> badinameAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Badiname));
        badinameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        badinamespinner.setAdapter((badinameAdapter));
    }
}

