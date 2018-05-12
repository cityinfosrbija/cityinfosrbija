package com.mzbcacak.kele.cityinfosrbija;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PretragaRezultati extends ArrayAdapter<Baza>{
    private final Context context;
    private final ArrayList<Baza> podatci;
    private IzaberiGrad iz;
    public PretragaRezultati(Context context, ArrayList<Baza> podatci) {
        super(context,R.layout.pretraga, podatci);

        this.context = context;
        this.podatci = podatci;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View pretraga = inflater.inflate(R.layout.pretraga, parent, false);

        iz = new IzaberiGrad(context);
        final TextView n = (TextView) pretraga.findViewById(R.id.naziv);
        final TextView a = (TextView) pretraga.findViewById(R.id.ulica);
        final ImageView logo = (ImageView) pretraga.findViewById(R.id.logoItema);
        n.setText(podatci.get(position).getIme());
        a.setText(podatci.get(position).getAdresa());
        String vrste = podatci.get(position).getVrsta();
        if(vrste.equals("Bankomati") && podatci.get(position).getUrl().equals("")){
            logo.setImageResource(iz.ikonice.get("Banke"));
        }else {
            if(podatci.get(position).getUrl().equals("")){
                logo.setImageResource(iz.ikonice.get(vrste));
            }else {
                Picasso.with(context).load(podatci.get(position).getUrl()).into(logo);
            }
        }


        if((vrste.equals("Benzinske pumpe")||vrste.equals("Crkve")||vrste.equals("Apoteke")||vrste.equals("Hipermarketi")||vrste.equals("Pijace")||vrste.equals("Restorani")||
                vrste.equals("Pekare")||vrste.equals("Kafići") )&& podatci.get(position).getAdresa().equals("objekat")){
            pretraga.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent naj = new Intent(context,NajbliziObjekti.class);
                    naj.putExtra("niz",podatci.get(0).getVrsta());
                    if(proveriDozvole()==false) {
                        context.startActivity(naj);
                    }
                }
            });
        }else {
            pretraga.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, Detalji.class);
                    i.putExtra("prebaci", podatci.get(position));
                    if(proveriDozvole()==false) {
                        context.startActivity(i);
                    }
                }
            });
        }
        return pretraga;
    }

    public boolean proveriDozvole(){
        boolean flag = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION};
            for (int i = 0; i < permissions.length; i++) {
                if (context.checkSelfPermission(permissions[i]) == PackageManager.PERMISSION_DENIED) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                ActivityCompat.requestPermissions((Activity) context, permissions, 1);
            }
        }
        return flag;
    }


}
