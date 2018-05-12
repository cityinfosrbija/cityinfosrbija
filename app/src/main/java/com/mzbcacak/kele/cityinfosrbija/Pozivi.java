package com.mzbcacak.kele.cityinfosrbija;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.List;

public class Pozivi extends AppCompatActivity {
    private List<String> textIspod;
    private List<String> telefoni;
    private List<Integer> ikoniceL;
    private RecyclerView recyclerView;
    private GridLayoutManager glm;
    private MenuSledeciAdapter menuAdapter;
    Intent prikazi;
    private RequestQueue requestQueue;
    private IzaberiGrad izaberiGrad;
    private List<String> ikonica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pozivi);
        textIspod = new ArrayList<String>();
        ikoniceL = new ArrayList<Integer>();
        telefoni = new ArrayList<String>();
        ikonica =  new ArrayList<String>();
        recyclerView = (RecyclerView) findViewById(R.id.recyView2);
        prikazi = new Intent(this,ListaObjekata.class);
        textIspod.clear();
        ikoniceL.clear();
        izaberiGrad = new IzaberiGrad(this);

        final HeaderFragment hfc = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.meniHeader2);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();

        glm = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(glm);

        menuAdapter = new MenuSledeciAdapter(this,textIspod,ikoniceL,1,telefoni,ikonica);
        recyclerView.setAdapter(menuAdapter);

        String url = izaberiGrad.getUrlPoVrstama()+"taxi";
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
                        textIspod.add(o.getString("naziv"));
                        ikoniceL.add(izaberiGrad.ikonice.get("Taxi udruženja"));
                        if(o.getString("fiksni").equals("")){
                            telefoni.add(o.getString("mobilni"));
                        }else{
                            telefoni.add(o.getString("fiksni"));
                        }
                        ikonica.add(o.getString("ikonice"));
                    }
                    menuAdapter.notifyDataSetChanged();
                    progressDialog.hide();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Nema podataka",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
