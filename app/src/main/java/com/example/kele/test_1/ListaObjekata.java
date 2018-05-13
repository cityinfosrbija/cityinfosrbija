package com.example.kele.test_1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mzbcacak.kele.cityinfosrbija.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ListaObjekata extends AppCompatActivity {
    private ProgressDialog progressDialog;
    String url;
    String url1;
    String pretrazi;
    private RequestQueue requestQueue;
    private IzaberiGrad izaberiGrad;
    ListView l;
    int x;
    ArrayList<Baza> baza =new ArrayList<Baza>();
    public int TOTAL_LIST_ITEMS = 100;
    public int NUM_ITEMS_PAGE   = 10;
    private int noOfBtns;
    private Button[] btns;
    private PretragaRezultati adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_objekata);
        Bundle headerPoruka = getIntent().getExtras();

        izaberiGrad = new IzaberiGrad();
        url = izaberiGrad.getUrlPoVrstama();
        url1=izaberiGrad.getUrlPoNazivu();
        l = (ListView) findViewById(R.id.listaV);
        String poruka = headerPoruka.getString("pretrazi");
        x = headerPoruka.getInt("funkcija");
        if(x==0){
            pretrazi = url1+poruka;
        }
        else if (x==1){
            pretrazi = url+poruka;
        }

        final HeaderFragment hfc = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.fragment7);
        //hfc.promeniNaslov(poruka);
        //adapter = new PretragaRezultati(this,baza);
        //l.setAdapter(adapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Ucitava");
        progressDialog.show();


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                pretrazi, new Response.Listener<JSONObject>()
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
                                o.getString("naziv")));
                    }
                    //adapter.notifyDataSetChanged();
                    progressDialog.hide();
                    if(x==1){
                        //hfc.promeniNaslov(baza.get(0).getVrsta());
                        hfc.promeniLogo(baza.get(0).getVrsta());
                    }
                    TOTAL_LIST_ITEMS=baza.size();
                    //Toast.makeText(getApplicationContext(), TOTAL_LIST_ITEMS, Toast.LENGTH_SHORT).show();
                    //String s = Integer.parseInt(String.valueOf(TOTAL_LIST_ITEMS));
                    hfc.promeniNaslov(String.valueOf(TOTAL_LIST_ITEMS));
                    Btnfooter();
                    loadList(0);
                    CheckBtnBackGroud(0);

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

    private void Btnfooter()
    {
        int val = TOTAL_LIST_ITEMS%NUM_ITEMS_PAGE;
        val = val==0?0:1;
        noOfBtns=TOTAL_LIST_ITEMS/NUM_ITEMS_PAGE+val;

        LinearLayout ll = (LinearLayout) findViewById(R.id.stranice);
        btns = new Button[noOfBtns];

        if(TOTAL_LIST_ITEMS<11) {
            ll.setVisibility(View.GONE);
            for (int i = 0; i < noOfBtns; i++) {
                btns[i] = new Button(this);
                btns[i].setBackgroundColor(Color.TRANSPARENT);
                btns[i].setText("" + (i + 1));

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ll.addView(btns[i], lp);

                final int j = i;
                btns[j].setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        loadList(j);
                        CheckBtnBackGroud(j);
                    }
                });
            }
        }else{
            for (int i = 0; i < noOfBtns; i++) {
                btns[i] = new Button(this);
                btns[i].setBackgroundColor(Color.TRANSPARENT);
                btns[i].setText("" + (i + 1));

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ll.addView(btns[i], lp);

                final int j = i;
                btns[j].setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        loadList(j);
                        CheckBtnBackGroud(j);
                    }
                });
            }
        }

    }
    private void CheckBtnBackGroud(int index)
    {
        for(int i=0;i<noOfBtns;i++)
        {
            if(i==index)
            {
                btns[index].setBackgroundColor(Color.GRAY);
                btns[i].setTextColor(Color.WHITE);
            }
            else
            {
                btns[i].setBackgroundColor(Color.TRANSPARENT);
                btns[i].setTextColor(Color.WHITE);
            }
        }

    }
    private void loadList(int number)
    {
        ArrayList<Baza> sort = new ArrayList<Baza>();

        int start = number * NUM_ITEMS_PAGE;
        for(int i=start;i<(start)+NUM_ITEMS_PAGE;i++)
        {
            if(i<baza.size())
            {
                sort.add(baza.get(i));
            }
            else
            {
                break;
            }
        }
        adapter = new PretragaRezultati(this,sort);
        l.setAdapter(adapter);
    }

}

