package com.mzbcacak.kele.cityinfosrbija;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
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


public class ListaObjekata extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private static String url;
    private static String url1;
    String pretrazi;
    private RequestQueue requestQueue;
    private IzaberiGrad izaberiGrad;
    ListView l;
    int x;
    private ArrayList<Baza> baza =new ArrayList<Baza>();
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


        izaberiGrad = new IzaberiGrad(this);
        url = izaberiGrad.getUrlPoVrstama();
        url1 = izaberiGrad.getUrlPoNazivu();
        l = (ListView) findViewById(R.id.listaV);
        final String poruka = headerPoruka.getString("pretrazi");
        x = headerPoruka.getInt("funkcija");
        final HeaderFragment hfc = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.fragment7);
        if(x==0){
            pretrazi = url1+poruka;
            hfc.omiljeno.setVisibility(View.GONE);
        }
        else if (x==1){
            pretrazi = url+poruka;
        }

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
                    if(poruka.equals("Crkve")||poruka.equals("Apoteke")||poruka.equals("Hipermarketi")||poruka.equals("Pijace")||poruka.equals("Restorani")||
                            poruka.equals("Pekare")||poruka.equals("Kafići")){
                        baza.add(new Baza(0,poruka,"objekat","","","","","","Pronadji najbliži",""));
                    }else if(poruka.contains("Benzinske")){
                        baza.add(new Baza(0,"Benzinske pumpe","objekat","","","","","","Pronadji najbliži",""));
                    }
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
                    }
                    progressDialog.hide();
                    if(baza.size()==1&&baza.get(0).getAdresa().equals("objekat")){
                        baza.clear();
                    }
                    if(x==1){
                        if(poruka.contains("Benzinske")){
                            hfc.promeniNaslov(baza.get(1).getVrsta());
                            hfc.promeniLogo(baza.get(1).getVrsta());
                        }else {
                            hfc.promeniNaslov(baza.get(0).getVrsta());
                            hfc.promeniLogo(baza.get(0).getVrsta());
                        }
                    }
                    TOTAL_LIST_ITEMS=baza.size();

                    Btnfooter();
                    loadList(0);
                    CheckBtnBackGroud(0);
                    SharedPreferences loginData = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String om = loginData.getString("omiljeno", "");
                    if(om.contains(hfc.na.getText().toString())) {
                        hfc.omiljeno.setImageResource(R.drawable.srce);
                    }

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

                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(80, LinearLayout.LayoutParams.WRAP_CONTENT);
                ll.addView(btns[i], lp1);

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
                btns[index].setBackgroundResource(R.drawable.krug);
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

    @Override
    public void onBackPressed() {
        FooterFragment ff = (FooterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
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
    protected void onPause() {
        super.onPause();
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


