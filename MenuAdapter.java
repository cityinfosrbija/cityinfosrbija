package com.mzbcacak.kele.cityinfosrbija;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kele on 7/17/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    Context context;
    private List<String> textIspod;
    private int[] ikoniceNiz;
    private String[] dalje ={"Ustanove","Zdravstvo","Školstvo","Sport","Saobraćaj","Transport","Ishrana","Taxi","Kalendar"};

    public MenuAdapter(Context context, List<String> textIspod, int[] ikoniceNiz) {
        this.context = context;
        this.textIspod = textIspod;
        this.ikoniceNiz = ikoniceNiz;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_menu_adapter,parent,false);
        return new MenuAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.ikonica.setImageResource(ikoniceNiz[position]);
        holder.textI.setText(textIspod.get(position));
        holder.ikonica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sledeci;
                if(dalje[position].equals("Taxi")){
                    sledeci = new Intent(context, Pozivi.class);
                }else if(dalje[position].equals("Kalendar")){
                    sledeci = new Intent(context, Kalendar.class);
                }else {
                    sledeci = new Intent(context, MeniSledeci.class);
                    sledeci.putExtra("dalje", dalje[position]);
                }
                context.startActivity(sledeci);
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
        }
    }
}



