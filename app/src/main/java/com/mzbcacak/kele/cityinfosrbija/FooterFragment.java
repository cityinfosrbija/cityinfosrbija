package com.mzbcacak.kele.cityinfosrbija;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
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
import java.util.Arrays;
import java.util.List;

public class FooterFragment extends Fragment {
    public static ImageButton meni;
    public static ImageButton info;
    public static ImageButton pozivi;
    public static ImageButton mojaLista;
    public static ImageButton podesavanja;
    public static Toolbar footerToolbar;
    private String url;
    private ArrayList<String> omiljenoAL;
    private ArrayList<Integer> ikn;
    private ArrayList<String> hitne;
    private ArrayList<Integer> iknH;
    private List<String> telefoni;
    private RecyclerView recyclerView;
    private GridLayoutManager glm;
    private MenuSledeciAdapter menuAdapter;
    private RequestQueue requestQueue;
    private IzaberiGrad izaberiGrad;
    private ArrayList<Baza> baza = new ArrayList<Baza>();
    int otvorena;
    int otvorenaP;
    Intent prikazi;
    public View popupView;
    public View popupViewP;
    public PopupWindow popupWindow;
    public PopupWindow popupWindowP;

    public FooterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_footer,container,false);
        meni = (ImageButton) view.findViewById(R.id.imageMeni);
        info = (ImageButton) view.findViewById(R.id.imageInfo);
        pozivi = (ImageButton) view.findViewById(R.id.imagePozivi);
        mojaLista = (ImageButton) view.findViewById(R.id.imageProfil);
        footerToolbar = (Toolbar) view.findViewById(R.id.footerToolbar);
        podesavanja = (ImageButton) view.findViewById(R.id.imagePodesavanja);

        prikazi = new Intent(getActivity(),ListaObjekata.class);

        otvorena = 0;
        otvorenaP = 0;
        izaberiGrad = new IzaberiGrad(getActivity());
        if(izaberiGrad.getIzabraniGrad().equals("Vrnjačka Banja")){
            url = "http://cityinfosrbija.tk/hitneSluzbe.php?pretraga=Vrnjačka";
        }else {
            url = "http://cityinfosrbija.tk/hitneSluzbe.php?pretraga=" + izaberiGrad.getIzabraniGrad();
        }
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        final int height = displaymetrics.heightPixels;
        final int width = displaymetrics.widthPixels;


        meni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(getActivity() instanceof GlavniMeni)) {
                    Intent m = new Intent(getActivity(), GlavniMeni.class);
                    m.putExtra("grad","");
                    startActivity(m);
                }
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(getActivity() instanceof Info)) {
                    Intent iInfo = new Intent(getActivity(), Info.class);
                    startActivity(iInfo);
                }
            }
        });

        pozivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupViewP = LayoutInflater.from(getActivity()).inflate(R.layout.activity_moja_lista, null);
                recyclerView = (RecyclerView) popupViewP.findViewById(R.id.recyViewFooter);

                requestQueue = Volley.newRequestQueue(getActivity());
                hitne = new ArrayList<String>();
                iknH = new ArrayList<Integer>();
                telefoni = new ArrayList<String>();
                final IzaberiGrad iz = new IzaberiGrad(getActivity());

                glm = new GridLayoutManager(getActivity(), 3);
                recyclerView.setLayoutManager(glm);

                menuAdapter = new MenuSledeciAdapter(getActivity(), hitne, iknH,1,telefoni);
                recyclerView.setAdapter(menuAdapter);

                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.show();

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
                                hitne.add(o.getString("vrste"));
                                iknH.add(iz.ikonice.get(o.getString("vrste")));
                                telefoni.add(o.getString("telefon"));
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
                        progressDialog.hide();
                        Toast.makeText(getActivity(),"Nema podataka",Toast.LENGTH_LONG).show();
                    }
                });
                requestQueue.add(jsonObjectRequest);
                if(otvorenaP==0) {
                    popupWindowP = new PopupWindow(popupViewP, width, (height - (2 * footerToolbar.getHeight() + getStatusBarHeight())));
                    popupWindowP.showAtLocation(popupViewP, Gravity.BOTTOM, 0, footerToolbar.getHeight());
                    popupWindowP.setFocusable(true);
                    otvorenaP = 1;
                    popupViewP.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindowP.dismiss();
                            otvorenaP = 0;
                        }
                    });
                }
            }
        });

        podesavanja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(getActivity() instanceof Podesavanja)) {
                    Intent pod = new Intent(getActivity(), Podesavanja.class);
                    startActivity(pod);
                }
            }
        });

        mojaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_moja_lista, null);
                recyclerView = (RecyclerView) popupView.findViewById(R.id.recyViewFooter);

                SharedPreferences loginData = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String om = loginData.getString("omiljeno", "");
                if(om.equals("")) {
                    Toast.makeText(getActivity(),"Lista je prazna",Toast.LENGTH_SHORT).show();
                }else{
                    IzaberiGrad zaIkonice = new IzaberiGrad(getActivity());
                    omiljenoAL = new ArrayList<String>(Arrays.asList(om.split(",")));
                    ikn = new ArrayList<Integer>();
                    int l = omiljenoAL.size();
                    for (int i = 0; i < l; i++) {
                        ikn.add(zaIkonice.ikonice.get(omiljenoAL.get(i)));
                    }

                    glm = new GridLayoutManager(getActivity(), 3);
                    recyclerView.setLayoutManager(glm);

                    menuAdapter = new MenuSledeciAdapter(getActivity(), omiljenoAL, ikn);
                    recyclerView.setAdapter(menuAdapter);
                    menuAdapter.notifyDataSetChanged();

                    if (otvorena == 0) {
                        popupWindow = new PopupWindow(popupView, width, (height - (2 * footerToolbar.getHeight() + getStatusBarHeight())));
                        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, footerToolbar.getHeight());
                        popupWindow.setFocusable(true);
                        otvorena = 1;
                        popupView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                                otvorena = 0;
                            }
                        });
                    }
                }
            }
        });
        return view;
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    View.OnClickListener getOnClickDoSomething(final Button button)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                prikazi.putExtra("pretrazi",button.getText().toString());
                prikazi.putExtra("funkcija",1);
                startActivity(prikazi);
            }
        };
    }



}
