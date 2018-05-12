package com.example.kele.test_1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PretragaRezultati extends ArrayAdapter<Baza>{
    PretragaRezultati p;
    private final Context context;
    private final ArrayList<Baza> podatci;
    private IzaberiGrad iz = new IzaberiGrad();
    public PretragaRezultati(Context context, ArrayList<Baza> podatci) {
        super(context,R.layout.pretraga, podatci);

        this.context = context;
        this.podatci = podatci;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View pretraga = inflater.inflate(R.layout.pretraga, parent, false);

        final TextView n = (TextView) pretraga.findViewById(R.id.naziv);
        final TextView a = (TextView) pretraga.findViewById(R.id.ulica);
        final ImageView logo = (ImageView) pretraga.findViewById(R.id.logoItema);
        final ImageView ikonica = (ImageView) pretraga.findViewById(R.id.vrstaItema);
        n.setText(podatci.get(position).getIme());
        a.setText(podatci.get(position).getAdresa());
        logo.setImageResource(iz.ikonice.get(podatci.get(position).getVrsta()));
        ikonica.setImageResource(iz.ikonice.get(podatci.get(position).getVrsta()));

        pretraga.setOnClickListener(new View.OnClickListener() {
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
