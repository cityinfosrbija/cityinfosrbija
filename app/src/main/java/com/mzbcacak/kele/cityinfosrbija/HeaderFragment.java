package com.mzbcacak.kele.cityinfosrbija;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class HeaderFragment extends Fragment {
    private static String grad;
    public SearchView prt;
    public TextView na;
    public ImageButton omiljeno;
    private IzaberiGrad iz;
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
        omiljeno.setVisibility(View.GONE);
        iz = new IzaberiGrad(getActivity());
        grad = iz.getIzabraniGrad();
        na.setText(grad);
        if(grad==null){
            grad = "Priboj";
        }else {
            switch (grad){
                case "Kraljevo":grb.setImageResource(R.drawable.grbkraljevo);break;
                case "Priboj":grb.setImageResource(R.drawable.grbpriboj);break;
                case "Loznica":grb.setImageResource(R.drawable.grbloznica);break;
                case "Zlatibor":grb.setImageResource(R.drawable.grbzlatibor);break;
                case "Vrnjačka Banja":grb.setImageResource(R.drawable.grbvrnjacka);break;
                case "Kragujevac":grb.setImageResource(R.drawable.grbkragujevac);break;
                case "Lučani":grb.setImageResource(R.drawable.grblucani);break;
                case "Požega":grb.setImageResource(R.drawable.grbpozega);break;
                case "Užice":grb.setImageResource(R.drawable.grbuzice);break;
                default:grb.setImageResource(R.drawable.grbcacak);break;
            }
        }

        if(getActivity() instanceof ListaObjekata){
            omiljeno.setVisibility(View.VISIBLE);
        }else if(getActivity() instanceof Banke){
            omiljeno.setVisibility(View.VISIBLE);
        }

        if(getActivity() instanceof Pozivi){
            na.setText("Taxi");
            grb.setImageResource(R.drawable.fd);
        }else if(getActivity() instanceof Banke){
            na.setText("Banke");
            grb.setImageResource(R.drawable.ag);
        }else if(getActivity() instanceof zaParking ){
            omiljeno.setVisibility(View.VISIBLE);
            na.setText("Parking zone");
            grb.setImageResource(R.drawable.ea);
        }else if(getActivity() instanceof PlatiParking){
            omiljeno.setVisibility(View.VISIBLE);
            na.setText("Parking");
            grb.setImageResource(R.drawable.ea);
        }else if(getActivity() instanceof Kontak) {
            na.setText("Kontakt");
        }

        prt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent pretrazi = new Intent(getActivity(), ListaObjekata.class);
                if(query.contains(" ")){
                    query = query.replace(" ","+");
                }
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
                if(getActivity() instanceof zaParking){
                    if (om.contains("Parking")) {
                        Toast.makeText(getActivity(), "Vec je u omiljeno", Toast.LENGTH_LONG).show();
                    } else {
                        editor.putString("omiljeno", om + "Parking,");
                        editor.apply();
                        omiljeno.setImageResource(R.drawable.srce);
                        Toast.makeText(getActivity(), "Dodato u omiljeno", Toast.LENGTH_LONG).show();
                    }
                }else {
                    if (om.contains(na.getText().toString())) {
                        Toast.makeText(getActivity(), "Vec je u omiljeno", Toast.LENGTH_LONG).show();
                    } else {
                        editor.putString("omiljeno", om + na.getText().toString() + ",");
                        editor.apply();
                        omiljeno.setImageResource(R.drawable.srce);
                        Toast.makeText(getActivity(), "Dodato u omiljeno", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        SharedPreferences loginData = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String om = loginData.getString("omiljeno", "");
        if(om.contains(na.getText().toString())) {
            omiljeno.setImageResource(R.drawable.srce);
        }

        return view;
    }

    public void promeniNaslov(String n){
        na.setText(n);
    }

    public void promeniLogo(String vrsta){
        IzaberiGrad i = new IzaberiGrad();
        if(vrsta.equals("Bankomati")) {
            grb.setImageResource(i.ikonice.get("Banke"));
        }else{
            grb.setImageResource(i.ikonice.get(vrsta));
        }
    }

    public void logoDetalji(String url){
        Picasso.with(getActivity()).load(url).into(grb);
    }

    @Override
    public void onResume() {
        super.onResume();
        iz = new IzaberiGrad();
        grad = iz.getIzabraniGrad();
    }
}
