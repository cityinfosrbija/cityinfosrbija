package com.mzbcacak.kele.cityinfosrbija;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Slajder extends PagerAdapter{

    private int[] sSlike={R.drawable.cacak1, R.drawable.cacak2, R.drawable.cacak3, R.drawable.cacak4};
    private int[] sSlikeK={R.drawable.kraljevo1, R.drawable.kraljevo2, R.drawable.kraljevo3, R.drawable.kraljevo4};
    private int[] sSlikeP={R.drawable.priboj1, R.drawable.priboj2, R.drawable.priboj3, R.drawable.priboj4};
    private int[] sSlikeL={R.drawable.loznica1, R.drawable.loznica2, R.drawable.loznica3, R.drawable.loznica4};
    private int[] sSlikeZ={R.drawable.zlatibor1, R.drawable.zlatibor2, R.drawable.zlatibor3, R.drawable.zlatibor4};
    private int[] sSlikeVB={R.drawable.vrnjacka1, R.drawable.vrnjacka2, R.drawable.vrnjacka3, R.drawable.vrnjacka4};
    private int[] sSlikeUzice={R.drawable.uzice1, R.drawable.uzice2, R.drawable.uzice3, R.drawable.uzice1};
    private int[] sSlikePozega={R.drawable.pozega1, R.drawable.pozega2, R.drawable.pozega3, R.drawable.pozega1};
    private int[] sSlikeLucani={R.drawable.lucani1, R.drawable.lucani2, R.drawable.lucani3, R.drawable.lucani1};
    private int[] sSlikeKragujevac={R.drawable.kragujevac1, R.drawable.kragujevac2, R.drawable.kragujevac3, R.drawable.kragujevac1};
    private Context cnt;
    private IzaberiGrad za;
    private LayoutInflater inf;

    public Slajder(Context cnt){
        this.cnt=cnt;
        za = new IzaberiGrad(cnt);
    }
    @Override
    public int getCount() {
        return sSlike.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inf = (LayoutInflater) cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inf.inflate(R.layout.layout_slajder,container,false);

        ImageView sl = (ImageView) view.findViewById(R.id.imageSlajder);
        if(za.getIzabraniGrad().equals("Kraljevo")) {
            sl.setImageResource(sSlikeK[position]);
        }else if(za.getIzabraniGrad().equals("Priboj")) {
            sl.setImageResource(sSlikeP[position]);
        }else if(za.getIzabraniGrad().equals("Loznica")) {
            sl.setImageResource(sSlikeL[position]);
        }else if(za.getIzabraniGrad().equals("Zlatibor")) {
            sl.setImageResource(sSlikeZ[position]);
        }else if(za.getIzabraniGrad().equals("Vrnjačka Banja")) {
            sl.setImageResource(sSlikeVB[position]);
        }else if(za.getIzabraniGrad().equals("Užice")) {
            sl.setImageResource(sSlikeUzice[position]);
        }else if(za.getIzabraniGrad().equals("Požega")) {
            sl.setImageResource(sSlikePozega[position]);
        }else if(za.getIzabraniGrad().equals("Lučani")) {
            sl.setImageResource(sSlikeLucani[position]);
        }else if(za.getIzabraniGrad().equals("Kragujevac")) {
            sl.setImageResource(sSlikeKragujevac[position]);
        }
        else{
            sl.setImageResource(sSlike[position]);
        }
        sl.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
