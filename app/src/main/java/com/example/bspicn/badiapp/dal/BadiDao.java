package com.example.bspicn.badiapp.dal;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bspicn.badiapp.model.Badi;

import org.json.JSONException;
import org.json.JSONObject;

public class BadiDao {

    public static List<Badi> getAll(RequestQueue queue, final onBadiResponseListener cb) {

       final List<Badi> availableBadis = new ArrayList<>();

       for ( int i = 1; i<230; i++){

           final boolean finished = i == 229;

            String URL = "https://www.wiewarm.ch:443/api/v1/bad.json/"+i;
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    URL,
                    null,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int badie = (Integer)response.get("badid");
                                System.out.println(badie);
                                String badname = (String) response.get("badname");
                                String ort = (String) response.get("ort");
                                String kanton = (String) response.get("kanton");
                                String adresse1 = (String) response.get("adresse1");
                                String adresse2 = (String) response.get("adresse2");

                                availableBadis.add(new Badi(badie,badname, ort, kanton, adresse1, adresse2));

                                if (finished) {
                                    cb.onSuccess(availableBadis);
                                }

                            } catch (JSONException e) {
                                System.out.println("error occurred: " + e.getMessage());
                            } catch (ClassCastException e) {
                                System.out.println("class cast occurred: " + e.getMessage());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Rest Respmse", error.toString());
                        }
                    }
            );
         queue.add(objectRequest);
        }
       return availableBadis;
    }

}
