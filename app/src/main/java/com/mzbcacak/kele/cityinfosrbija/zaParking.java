package com.mzbcacak.kele.cityinfosrbija;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class zaParking extends AppCompatActivity implements MapFragment.tag{

    private RequestQueue requestQueue;
    private ProgressDialog progressDialog;
    private IzaberiGrad izaberiGrad;
    private ArrayList<Baza> bazaP =new ArrayList<Baza>();
    private MapFragment mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_za_parking);

        izaberiGrad = new IzaberiGrad(this);
        String url = izaberiGrad.getUrlParking()+"Cacak";
        mp = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.mapaParking);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Ucitava");
        progressDialog.show();

        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray objekti = response.getJSONArray("Podatci");
                    int len = objekti.length();
                    for (int k = 0; k < len; k++) {
                        JSONObject o = objekti.getJSONObject(k);
                        if(o.getString("prva").equals("kamera")){
                            mp.pozicjaKamereP(o.getString("druga"),14);
                        }else {
                            mp.dodajLiniju(o.getString("prva"), o.getString("druga"), o.getString("zona"));
                        }

                    }
                    progressDialog.hide();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(),"Nema podataka",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void markerClick(int poz){
        Intent detalji = new Intent(this,Detalji.class);
        detalji.putExtra("prebaci",bazaP.get(poz));
        startActivity(detalji);
    }

    @Override
    public void getTaga(String t) {

    }

    @Override
    public void onBackPressed() {
        FooterFragment ff = (FooterFragment) getSupportFragmentManager().findFragmentById(R.id.footerP);
        if(ff.otvorena==1){
            ff.popupWindow.dismiss();
            ff.otvorena = 0;
        }else if(ff.otvorenaP==1){
            ff.popupWindowP.dismiss();
            ff.otvorenaP = 0;
        }else {
            super.onBackPressed();
        }
    }
}
