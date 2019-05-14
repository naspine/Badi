package com.example.bspicn.badiapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bspicn.badiapp.helper.WieWarmJsonParser;
import com.example.bspicn.badiapp.model.Badi;
import com.example.bspicn.badiapp.model.Becken;
import com.example.bspicn.badiapp.model.Oeffnungszeit;
import com.example.bspicn.badiapp.model.Preis;

import org.json.JSONException;

public class BadiMoreDetailsActivity extends AppCompatActivity {

    private int badiId;
    private String badiName;
    private ProgressBar progressBar;

    private static final String WIE_WARM_API_URL = "https://www.wiewarm.ch/api/v1/bad.json/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badi_more_details);
        setTitle("Mehr Infos");
        Intent intent = getIntent();
        badiId = intent.getIntExtra("badiId", 0);
        badiName = intent.getStringExtra("badiName");
        getBadiPreis(WIE_WARM_API_URL + badiId);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getBadiPreis(String url) {
        final ArrayAdapter<Oeffnungszeit> ZeitInfosAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        final ArrayAdapter<Preis> PreisInfosAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    Badi badi = WieWarmJsonParser.createBadiFromJsonString(response);
                    ZeitInfosAdapter.addAll(badi.getOeffnungszeit());
                    System.out.println(ZeitInfosAdapter);
                    ListView zeitInfoList = findViewById(R.id.oeffnungzeit);
                    zeitInfoList.setAdapter(ZeitInfosAdapter);

                    PreisInfosAdapter.addAll(badi.getPreis());
                    System.out.println(PreisInfosAdapter);
                    ListView preisInfoList = findViewById(R.id.preis);
                    preisInfoList.setAdapter(PreisInfosAdapter);
                    progressBar.setVisibility(View.GONE);
                }
                catch (JSONException e)
                {
                    generateAlertDialog();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error)
            {
                generateAlertDialog();
            }
        });
        queue.add(stringRequest);
    }
    private void generateAlertDialog() {
        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder dialogBuilder;
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Closes this activity
                finish();
            }
        });
        dialogBuilder.setMessage("Die weiteren Badidetails konnten nicht geladen werden. Versuche es später nochmals.").setTitle("Fehler");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}

