package com.example.bspicn.badiapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;

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

    }
}
