package com.example.kele.test_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mzbcacak.kele.cityinfosrbija.R;

import java.util.List;


public class MenuSledeciAdapter extends RecyclerView.Adapter<MenuSledeciAdapter.ViewHolder>{
    Context context;
    private List<String> textIspod;
    private List<Integer> ikonice;

    public MenuSledeciAdapter(Context context, List<String> textIspod, List<Integer> ikonice) {
        this.context = context;
        this.textIspod = textIspod;
        this.ikonice = ikonice;
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
        holder.ikonica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textIspod.get(position).equals("Banke")){
                    Intent b = new Intent(context,Banke.class);
                    context.startActivity(b);
                }else if(textIspod.get(position).equals("Škole za decu sa SP")){
                    Intent sp = new Intent(context,ListaObjekata.class);
                    sp.putExtra("pretrazi", "decu");
                    sp.putExtra("funkcija", 1);
                    context.startActivity(sp);
                }else {
                    Intent i = new Intent(context,ListaObjekata.class);
                    if(textIspod.get(position).contains(" ")==false) {
                        i.putExtra("pretrazi", textIspod.get(position));
                        i.putExtra("funkcija", 1);
                        context.startActivity(i);
                    }else{
                        String podeliNaziv[] = textIspod.get(position).split(" ");
                        if(podeliNaziv[0].equals("Auto")){
                            i.putExtra("pretrazi", podeliNaziv[1]);
                        }
                        else {
                            i.putExtra("pretrazi", podeliNaziv[0]);
                        }
                        i.putExtra("funkcija", 1);
                        context.startActivity(i);

                    }
                }
            }
        });
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
