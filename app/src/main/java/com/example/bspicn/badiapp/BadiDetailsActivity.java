package com.example.bspicn.badiapp;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.example.bspicn.badiapp.model.Adresse;
import com.example.bspicn.badiapp.model.Badi;
import com.example.bspicn.badiapp.model.Becken;

import org.json.JSONException;
import org.osmdroid.api.IMapController;
import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BadiDetailsActivity extends AppCompatActivity {
    private int badiId;
    private String badiName;
    private ProgressBar progressBar;
    TextView textView;
    Button button;
    private static final String WIE_WARM_API_URL = "https://www.wiewarm.ch/api/v1/bad.json/";
    String adresseGeo;
    double latitude;
    double longitude;
    private String ort;

    MapView map = null;

    public BadiDetailsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badi_details);

        button = findViewById(R.id.button1);
        progressBar = findViewById(R.id.loading_badi_details_progress);
        Intent intent = getIntent();
        badiId = intent.getIntExtra("badiId", 0);
        badiName = intent.getStringExtra("badiName");
        ort = intent.getStringExtra("ort");
        final String name = intent.getStringExtra("badiName");
        setTitle(name);
        progressBar.setVisibility(View.VISIBLE);
        getBadiTemp(WIE_WARM_API_URL + badiId);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BadiMoreDetailsActivity.class);
                intent.putExtra("badiId", badiId);
                intent.putExtra("badiName", badiName);
                startActivity(intent);
            }
        });

    }

    private void Geocash(String adress) {
        textView = findViewById(R.id.no_address);
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocationName(adress, 1);

            if (addresses.size() > 0) {
                latitude = addresses.get(0).getLatitude();
                longitude = addresses.get(0).getLongitude();
                System.out.println(latitude);
                System.out.println(longitude);
            }
            System.out.println("here1");
        } catch (Exception e) {
            System.out.println("here");
        }
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        //inflate and create the map

        map = (MapView) findViewById(R.id.mapview);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        mapController.setZoom(15.5);
        GeoPoint startPoint = new GeoPoint(latitude, longitude);
        mapController.setCenter(startPoint);

        if (longitude == 0.0) {

            textView.setVisibility(View.VISIBLE);
            mapController.setZoom(3);
        } else {
            textView.setVisibility(View.GONE);


            //your items
            ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
            items.add(new OverlayItem(badiName, badiName, new GeoPoint(latitude, longitude))); // Lat/Lon decimal degrees

            Marker startMarker = new Marker(map);
            startMarker.setPosition(startPoint);
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            startMarker.setTitle(badiName + ", " + ort);
            map.getOverlays().add(startMarker);
        }
    }

    private void getBadiTemp(String url) {
        final ArrayAdapter<Adresse> adressenInfosAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        final ArrayAdapter<Becken> beckenInfosAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Badi badi = WieWarmJsonParser.createBadiFromJsonString(response);
                    beckenInfosAdapter.addAll(badi.getBecken());
                    //System.out.println(beckenInfosAdapter);
                    ListView badiInfoList = findViewById(R.id.becken_infos);
                    badiInfoList.setAdapter(beckenInfosAdapter);
                    progressBar.setVisibility(View.GONE);

                    adresseGeo = badi.getAdresses();

                    Geocash(adresseGeo);
                    progressBar.setVisibility(View.GONE);

                } catch (JSONException e) {
                    generateAlertDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
        dialogBuilder.setMessage("Die Badidetails konnten nicht geladen werden. Versuche es später nochmals.").setTitle("Fehler");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
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

}
