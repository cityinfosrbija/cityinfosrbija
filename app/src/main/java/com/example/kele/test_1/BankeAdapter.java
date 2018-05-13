package com.example.kele.test_1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mzbcacak.kele.cityinfosrbija.R;

import java.util.List;

/**
 * Created by Kele on 7/7/2017.
 */

class BankeAdapter extends RecyclerView.Adapter<BankeAdapter.ViewHolder>{
    Context context;
    List<String> banke1;
    ImageView logo;
    private AlertDialog.Builder provera;
    private int[] logoNiz = {
            R.drawable.addiko,
            R.drawable.aik,
            R.drawable.alpha,
            R.drawable.erste,
            R.drawable.eurobank,
            R.drawable.halkbank,
            R.drawable.intesa,
            R.drawable.komercijalna,
            R.drawable.marfin,
            R.drawable.nlb,
            R.drawable.opportunity,
            R.drawable.otp,
            R.drawable.piraeus,
            R.drawable.procredit,
            R.drawable.raiffeisen,
            R.drawable.sber,
            R.drawable.societe,
            R.drawable.unicredit,
            R.drawable.vojvodjanska,
    };

    public BankeAdapter(Context conten, List<String> banke1) {
        this.context = conten;
        this.banke1 = banke1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_adapter,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //holder.bank.setText(banke1.get(position));
        holder.logo.setImageResource(logoNiz[position]);
        holder.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provera = new AlertDialog.Builder(context);
                provera.setMessage("Prikazi:");
                provera.setCancelable(false);
                provera.setPositiveButton("Ekspoziture", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(context,BankeMapaLista.class);
                        i.putExtra("nazivBanke",banke1.get(position));
                        i.putExtra("logo",logoNiz[position]);
                        i.putExtra("vrstaB",0);
                        context.startActivity(i);
                    }
                });
                provera.setNegativeButton("Bankomate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(context,BankeMapaLista.class);
                        i.putExtra("nazivBanke",banke1.get(position));
                        i.putExtra("logo",logoNiz[position]);
                        i.putExtra("vrstaB",1);
                        context.startActivity(i);
                    }
                });
                provera.create().show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return banke1.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        //public Button bank;
        public ImageView logo;
        public ViewHolder(View itemView) {
            super(itemView);
            logo = (ImageView) itemView.findViewById(R.id.logoBanke);
        }
    }
}
