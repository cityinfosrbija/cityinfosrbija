package com.example.kele.test_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.mzbcacak.kele.cityinfosrbija.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HeaderFragment extends Fragment {
    private String[] omiljenoLista;
    public SearchView prt;
    public TextView na;
    public ImageButton omiljeno;
    public ImageView grb;
    String string;

    public HeaderFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_header,container,false);
        na = (TextView) view.findViewById(R.id.naslov);
        prt = (SearchView) view.findViewById(R.id.pretraga);
        omiljeno = (ImageButton) view.findViewById(R.id.omiljeno);
        grb = (ImageView) view.findViewById(R.id.grbGrada);

        na.setText("Čačak");

        if(!(getActivity() instanceof ListaObjekata)){
            omiljeno.setVisibility(View.GONE);
        }

        prt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent pretrazi = new Intent(getActivity(), ListaObjekata.class);
                pretrazi.putExtra("pretrazi", query);
                pretrazi.putExtra("funkcija",0);
                startActivity(pretrazi);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        prt.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                na.setVisibility(View.GONE);
                grb.setVisibility(View.GONE);
                omiljeno.setVisibility(View.GONE);
            }
        });

        string = na.getText().toString();
        omiljeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences loginData = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = loginData.edit();
                String om = loginData.getString("omiljeno", "");
                if(om.contains(na.getText().toString())) {
                    Toast.makeText(getActivity(),"Vec je u omiljeno",Toast.LENGTH_LONG).show();
                }
                else{
                    editor.putString("omiljeno", om + na.getText().toString()+ ",");
                    editor.apply();
                    Toast.makeText(getActivity(),"Dodato u omiljeno",Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    public void promeniNaslov(String n){
        na.setText(n);
    }

    public void promeniLogo(String vrsta){
        IzaberiGrad i = new IzaberiGrad();
        grb.setImageResource(i.ikonice.get(vrsta));
    }

}
