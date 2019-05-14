package com.example.bspicn.badiapp.helper;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;

import com.example.bspicn.badiapp.model.Badi;
import com.example.bspicn.badiapp.model.Becken;
import com.example.bspicn.badiapp.model.Preis;
import com.example.bspicn.badiapp.model.Oeffnungszeit;

public class WieWarmJsonParser {
    public static Badi createBadiFromJsonString(String badiJsonString) throws JSONException {
        Badi badi = new Badi();
        Preis preis = new Preis();
        Oeffnungszeit oeffnungszeit = new Oeffnungszeit();

        JSONObject jsonObj = new JSONObject(badiJsonString);
        badi.setId(Integer.parseInt(jsonObj.getString("badid")));
        badi.setName(jsonObj.getString("badname"));
        badi.setKanton(jsonObj.getString("kanton"));
        badi.setOrt(jsonObj.getString("ort"));


        JSONObject beckenJson = jsonObj.getJSONObject("becken");
        Iterator keys = beckenJson.keys();
        oeffnungszeit.setOeffnungszeit(jsonObj.getString("zeiten"));
        badi.addOeffnungszeit(oeffnungszeit);

        preis.setPreis(jsonObj.getString("preise"));
        badi.addPreis(preis);
        while (keys.hasNext()) {

            Becken becken = new Becken();
            String key = (String) keys.next();
            JSONObject subObj = beckenJson.getJSONObject(key);
            becken.setName(subObj.getString("beckenname"));
            becken.setTemperature(Double.parseDouble(subObj.getString("temp")));
            badi.addBecken(becken);



        } return badi;
    }
}
