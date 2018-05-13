package com.example.kele.test_1;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mzbcacak.kele.cityinfosrbija.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BankeMapaLista extends AppCompatActivity implements MapFragment.tag{
    private ProgressDialog progressDialog;
    ArrayList<Baza> baza;
    private ListView l;
    private String nazivBanke;
    private int logoB;
    private int iz;
    private ImageView logoBanke;
    private TextView nazivBankeL;
    private String url1 = "";
    private String zaBanke = "https://cacakandroid.000webhostapp.com/banke.php?naziv=";
    private String zaBankomate = "https://cacakandroid.000webhostapp.com/bankomati.php?naziv=";
    private HeaderFragment hfc;
    private MapFragment mapF;
    private RequestQueue requestQueue;

    public BankeMapaLista() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banke_mapa_lista);

        baza = new ArrayList<Baza>();
        l = (ListView) findViewById(R.id.bankeListView);

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
        //logoBanke.setImageResource(logoB);
        //nazivBankeL.setText(nazivBanke);
        mapF = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.mapaFragment);
        hfc = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        final AdapterBankeLista adapter = new AdapterBankeLista(this,baza);
        l.setAdapter(adapter);


        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Ilija govno",Toast.LENGTH_LONG).show();
                //frag(position);
                //updateView(position);
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Ucitava");
        progressDialog.show();

        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url1, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray objekti = response.getJSONArray("Podatci");
                    int len = objekti.length();
                    for(int k=0; k<len;k++){
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
                    if(baza.size()>0) {
                        hfc.promeniNaslov(baza.get(0).getIme());
                        hfc.grb.setImageResource(logoB);
                        for (int i = 0; i < baza.size(); i++) {
                            mapF.koordinate(baza.get(i).getGoogleMap());
                        }
                        adapter.notifyDataSetChanged();
                        progressDialog.hide();
                        mapF.promeniMarker(baza.size());
                        mapF.pozicijaKamere(baza.size());
                    }else{
                        progressDialog.hide();
                    }
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

    @Override
    public void getTaga(String t) {
        int i = Integer.parseInt(t);
        Toast.makeText(this,baza.get(i).getIme(),Toast.LENGTH_LONG).show();
    }
    public void frag(int p){
        mapF.promeniMarker(p);
    }
    private void updateView(int index){
        View v = l.getChildAt(index);
        RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.adapterLayout);
        rl.setBackgroundColor(Color.CYAN);
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    public void promeni(){
        View view = getViewByPosition(1,l);
        RelativeLayout rla = (RelativeLayout) view.findViewById(R.id.adapterLayout);
        rla.setBackgroundColor(Color.CYAN);
    }
}
