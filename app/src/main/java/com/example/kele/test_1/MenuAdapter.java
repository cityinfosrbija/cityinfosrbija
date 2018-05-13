package com.example.kele.test_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mzbcacak.kele.cityinfosrbija.R;

import java.util.List;

/**
 * Created by Kele on 7/17/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    Context context;
    private List<String> textIspod;
    private int[] ikoniceNiz;

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
                Intent sledeci = new Intent(context,MeniSledeci.class);
                sledeci.putExtra("dalje",textIspod.get(position));
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

            //ikonica.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
    }
}



