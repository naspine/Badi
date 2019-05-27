package com.example.bspicn.badiapp;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.example.bspicn.badiapp.dal.BadiDao;
import com.example.bspicn.badiapp.dal.onBadiResponseListener;
import com.example.bspicn.badiapp.model.Badi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements onBadiResponseListener {
    private int badiId;
    private String badiName;
    MapView map = null;
    MapView mMapView;
    Button btnKarteMap;
    Button btnAlleBadisMap;
    TextView infoDauer;
    ProgressBar progressBar;
    MyLocationNewOverlay mLocationOverlay;
    double latitude;
    double longitude;
    String ort;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inflate and create the map
        setContentView(R.layout.activity_maps);
        infoDauer = findViewById(R.id.infoDauer);
        progressBar = findViewById(R.id.loading_maps_progress);


        //Button Stuff
        btnAlleBadisMap = findViewById(R.id.btnKarteMap);
        btnKarteMap = findViewById(R.id.btnAlleBadisMap);
        setTitle("Bädälä");
        infoDauer.setVisibility(View.VISIBLE);
        infoDauer.setText("Die Standtorte der Bäder werden geladen, dies kann einen Moment dauern. ");

        progressBar.setVisibility(View.VISIBLE);
        addBadisToClickableList();


        btnKarteMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                btnAlleBadisMap.setClickable(false);
                btnKarteMap.setClickable(true);

            }
        });
    }


    private void addBadisToClickableList() {
        BadiDao.getAll(Volley.newRequestQueue(getApplicationContext()), this);
    }

    public void onSuccess(List<Badi> badisReceived) {

        List<Badi> b = badisReceived;
        String adresse;

        for (Badi badi : b) {
            adresse = badi.getAdresses();
            badiName = badi.getName();
            ort = badi.getOrt();
            geocash(adresse);
            System.out.println(badi.getId());

        }
        infoDauer.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);


    }


    private void geocash(String adress) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocationName(adress + " Schweiz", 1);
            if (addresses.size() > 0) {
                latitude = addresses.get(0).getLatitude();
                longitude = addresses.get(0).getLongitude();
            }
        } catch (Exception e) {
            generateAlertDialog();

        }


        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        //Map kreieren und Kartenausschnitt bestimmen
        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        mapController.setZoom(8);
        GeoPoint startPoint = new GeoPoint(latitude, longitude);
        mapController.setCenter(startPoint);

        //Item
        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        items.add(new OverlayItem(badiName, badiName, new GeoPoint(latitude, longitude)));

        //PUnkt anzeigen mit Badiname und Ort
        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle(badiName + ", " + ort);
        map.getOverlays().add(startMarker);
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
        dialogBuilder.setMessage("Die Badis konnten nicht geladen werden. Versuchens Sie es später noch einmal").setTitle("Fehler");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

}
