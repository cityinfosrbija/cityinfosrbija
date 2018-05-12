package com.mzbcacak.kele.cityinfosrbija;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MenuSledeciAdapter extends RecyclerView.Adapter<MenuSledeciAdapter.ViewHolder>{
    Context context;
    private List<String> textIspod;
    private List<Integer> ikonice;
    private int izaberi = 0;
    private List<String> telefon;
    private List<String> ikonica;

    public MenuSledeciAdapter(Context context, List<String> textIspod, List<Integer> ikonice) {
        this.context = context;
        this.textIspod = textIspod;
        this.ikonice = ikonice;
    }

    public MenuSledeciAdapter(Context context, List<String> textIspod, List<Integer> ikonice, int izaberi, List<String> telefon, List<String> ikonica) {
        this.context = context;
        this.textIspod = textIspod;
        this.ikonice = ikonice;
        this.ikonica = ikonica;
        this.izaberi = izaberi;
        this.telefon = telefon;
    }

    public MenuSledeciAdapter(Context context, List<String> textIspod, List<Integer> ikonice, int izaberi, List<String> telefon) {
        this.context = context;
        this.textIspod = textIspod;
        this.ikonice = ikonice;
        this.izaberi = izaberi;
        this.telefon = telefon;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_menu_adapter,parent,false);
        return new MenuSledeciAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.ikonica.setImageResource(ikonice.get(position));
        holder.textI.setText(textIspod.get(position));
        final AlertDialog.Builder provera = new AlertDialog.Builder(context);
        holder.ikonica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (izaberi != 1) {
                    if (textIspod.get(position).equals("Banke")) {
                        Intent b = new Intent(context, Banke.class);
                        context.startActivity(b);
                    } else if (textIspod.get(position).equals("Škole za decu sa SP")) {
                        Intent sp = new Intent(context, ListaObjekata.class);
                        sp.putExtra("pretrazi", "decu");
                        sp.putExtra("funkcija", 1);
                        context.startActivity(sp);
                    }else if(textIspod.get(position).equals("Pošte")){
                        Intent i = new Intent(context,BankeMapaLista.class);
                        i.putExtra("nazivBanke","");
                        i.putExtra("logo",0);
                        i.putExtra("vrstaB",2);
                        context.startActivity(i);
                    }else if(textIspod.get(position).equals("Parking")){
                        IzaberiGrad iz = new IzaberiGrad(context);
                        if(iz.getIzabraniGrad().equals("Čačak")) {
                            Intent parking = new Intent(context, PlatiParking.class);
                            context.startActivity(parking);
                        }else{
                            Toast.makeText(context,"Nema podataka",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Intent i = new Intent(context, ListaObjekata.class);
                        if (textIspod.get(position).contains(" ") == false) {
                            i.putExtra("pretrazi", textIspod.get(position));
                            i.putExtra("funkcija", 1);
                            context.startActivity(i);
                        } else {
                            String podeliNaziv[] = textIspod.get(position).split(" ");
                            if (podeliNaziv[0].equals("Auto")) {
                                i.putExtra("pretrazi", podeliNaziv[1]);
                            } else {
                                i.putExtra("pretrazi", podeliNaziv[0]);
                            }
                            i.putExtra("funkcija", 1);
                            context.startActivity(i);

                        }
                    }
                }else {
                    provera.setMessage("Pozivate "+telefon.get(position));
                    provera.setCancelable(false);
                    provera.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:"+telefon.get(position).trim()));
                            context.startActivity(callIntent);
                        }
                    });
                    provera.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    provera.create().show();
                }
            }

        });
        if(izaberi==1 && context instanceof Pozivi){
            if (!ikonica.get(position).equals("")){
                Picasso.with(context).load(ikonica.get(position)).into(holder.ikonica);
            }
        }
    }

    @Override
    public int getItemCount() {
        return textIspod.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ikonica;
        public TextView textI;
        public ViewHolder(View itemView) {
            super(itemView);
            ikonica = (ImageView) itemView.findViewById(R.id.meniVrstaS);
            textI = (TextView) itemView.findViewById(R.id.meniVrstaT);

            //ikonica.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
    }
}
