package com.mzbcacak.kele.cityinfosrbija;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

public class NajbliziObjekti extends AppCompatActivity implements MapFragment.tag{
    private MapFragment mp;
    private HeaderFragment hf;
    private RequestQueue requestQueue;
    private IzaberiGrad iza;
    private ProgressDialog progressDialog;
    private ArrayList<Baza> baza = new ArrayList<Baza>();
    private String poruka;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_najblizi_objekti);

        mp = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.mapaParkingN);
        hf = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.najHeader);
        iza = new IzaberiGrad(this);
        Bundle najb = getIntent().getExtras();
        poruka = najb.getString("niz");

        if(poruka.contains(" ")){
            String sk1[] = poruka.split(" ");
            url = iza.getUrlPoVrstama()+sk1[0];
        }else{
            url = iza.getUrlPoVrstama()+poruka;
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Ucitava");
        progressDialog.show();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

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
                        baza.add(new Baza(Integer.parseInt(o.getString("id")),
                                o.getString("vrste"),
                                o.getString("adresa"),
                                o.getString("fiksni"),
                                o.getString("mobilni"),
                                o.getString("email"),
                                o.getString("website"),
                                o.getString("map"),
                                o.getString("naziv"),
                                o.getString("ikonice")));
                        mp.koordinateNaj(o.getString("map"),o.getString("naziv"),o.getString("adresa"));
                    }
                    hf.promeniNaslov(baza.get(0).getVrsta());
                    hf.promeniLogo(baza.get(0).getVrsta());
                    progressDialog.hide();
                    mp.pozicijaKamereCeoEkran(baza.size());

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Nema podataka",Toast.LENGTH_SHORT).show();
                    progressDialog.hide();
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

    public void otvoriDetalje(String pozicija){
        Intent i = new Intent(NajbliziObjekti.this,Detalji.class);
        i.putExtra("prebaci", baza.get(Integer.parseInt(pozicija)));
        startActivity(i);
    }
    @Override
    public void getTaga(String t) {

    }

    @Override
    public void onBackPressed() {
        FooterFragment ff = (FooterFragment) getSupportFragmentManager().findFragmentById(R.id.najFooter);
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

    @Override
    protected void onResume() {
        IzaberiGrad izaberiGrad = new IzaberiGrad(this);
        if(izaberiGrad.getFont()!=1) {
            FontsOverride.setDefaultFont(this, "MONOSPACE", "quatt.ttf");
            izaberiGrad.setFont(1);
        }
        super.onResume();
    }
}
