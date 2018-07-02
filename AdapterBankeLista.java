package com.mzbcacak.kele.cityinfosrbija;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kele on 7/19/2017.
 */

public class AdapterBankeLista extends ArrayAdapter<Baza> {
    private final Context context;
    private final ArrayList<Baza> podatci;
    private MapFragment mapF1;
    BankeMapaLista bml;
    private String slova[] = {"A","B","C","D","E","F","G","H"};
    int pozicijaL = 0;

    public AdapterBankeLista (Context context, ArrayList<Baza> podatci){
        super(context,R.layout.adapter_banke_liste, podatci);

        this.context = context;
        this.podatci = podatci;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View pretraga = inflater.inflate(R.layout.adapter_banke_liste, parent, false);
        pretraga.setClickable(true);
        pretraga.setFocusable(true);
        final TextView n = (TextView) pretraga.findViewById(R.id.nazivBL);
        final TextView a = (TextView) pretraga.findViewById(R.id.ulicaBL);
        final ImageButton dalje = (ImageButton) pretraga.findViewById(R.id.prikaziDetalje);
        final TextView pozicija = (TextView) pretraga.findViewById(R.id.pozicijaMarkera);
        pozicija.setText(slova[position]);
        //pozicijaL++;
        n.setText(podatci.get(position).getIme());
        a.setText(podatci.get(position).getAdresa());
        dalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Detalji.class);
                i.putExtra("prebaci",podatci.get(position));
                context.startActivity(i);
            }
        });

        return pretraga;
    }



}
