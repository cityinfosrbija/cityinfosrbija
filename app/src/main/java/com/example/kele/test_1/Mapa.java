package com.example.kele.test_1;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

public class Mapa extends AppCompatActivity implements MapFragment.tag {

    String url1 = "";
    String zaBanke = "https://cacakandroid.000webhostapp.com/banke.php?naziv=";
    String zaBankomate = "https://cacakandroid.000webhostapp.com/bankomati.php?naziv=";
    ArrayList<Baza> baza =new ArrayList<Baza>();
    private String nazivBanke;
    private ProgressDialog progressDialog;
    private RequestQueue requestQueue;
    public static TextView adresa;
    private FooterFragment footerFragment;
    private MapFragment mp;
    RelativeLayout r,r2;
    int iz;
    int logoB;
    private TextView tel;
    private TextView mob;
    private TextView email;
    private TextView web;
    private ImageView logo;
    private TextView naslov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        r = (RelativeLayout) findViewById(R.id.mapaRelative);
        r2 = (RelativeLayout) findViewById(R.id.relative);
        r2.setVisibility(View.GONE);
        Bundle nB = getIntent().getExtras();
        nazivBanke = nB.getString("nazivBanke");
        logoB = nB.getInt("logo");
        iz = nB.getInt("vrstaB");
        if(iz==0) {
            url1 = zaBanke + nazivBanke;
        }
        else{
            url1 = zaBankomate + nazivBanke;
        }
        adresa = (TextView) findViewById(R.id.detaljiAdresaD);
        tel = (TextView) findViewById(R.id.detaljiTelefon);
        mob = (TextView) findViewById(R.id.detaljiMobilni);
        email = (TextView) findViewById(R.id.detaljiMail);
        web = (TextView) findViewById(R.id.detaljiWeb);
        logo = (ImageView) findViewById(R.id.logoNaslov);
        naslov = (TextView) findViewById(R.id.textNaslov);

        naslov.setText(nazivBanke);
        logo.setImageResource(logoB);
        //r.setScrollingEnabled(false);

        mp = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment19);

        footerFragment = (FooterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment18);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Ucitava");
        progressDialog.show();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url1, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray objekti = response.getJSONArray("Podatci");
                    int len = objekti.length();
                    for(int k=0; k<6 && k<len;k++){
                        JSONObject o = objekti.getJSONObject(k);
                        baza.add(new Baza(Integer.parseInt(o.getString("id")),
                                o.getString("vrste"),
                                o.getString("adresa"),
                                o.getString("fiksni"),
                                o.getString("mobilni"),
                                o.getString("email"),
                                o.getString("website"),
                                o.getString("map"),
                                o.getString("naziv")));
                    }
                    for(int i=0;i<baza.size();i++) {
                        mp.koordinate(baza.get(i).getGoogleMap());
                    }
                    progressDialog.hide();
                    //mp.koordinate(baza.get(0).getGoogleMap());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void getTaga(String t) {
        int i = Integer.parseInt(t);
        adresa.setText(baza.get(i).getAdresa());
        tel.setText(baza.get(i).getFiksni());
        mob.setText(baza.get(i).getMobilni());
        email.setText(baza.get(i).getEmail());
        web.setText(baza.get(i).getWebsite());
        final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (250 * scale + 0.5f);
        RelativeLayout.LayoutParams rel_btn = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, pixels);
        rel_btn.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rel_btn.setMargins(0,0,0,footerFragment.footerToolbar.getHeight());
        r2.setVisibility(View.VISIBLE);
        r.setLayoutParams(rel_btn);
    }
}
