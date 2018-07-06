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
    private int[] sSlikeArandjelovac={R.drawable.arandjelovac1, R.drawable.arandjelovac2, R.drawable.arandjelovac3, R.drawable.arandjelovac1};
    private int[] sSlikeBelaCrkva={R.drawable.belacrkva1, R.drawable.belacrkva2, R.drawable.belacrkva3, R.drawable.belacrkva1};
    private int[] sSlikeBor={R.drawable.bor1, R.drawable.bor2,R.drawable.bor1, R.drawable.bor2};
    private int[] sSlikeBujanovac={R.drawable.bujanovac1,R.drawable.bujanovac1,R.drawable.bujanovac1,R.drawable.bujanovac1};
    private int[] sSlikeDimitrovgrad={R.drawable.dimitrovgrad1,R.drawable.dimitrovgrad1,R.drawable.dimitrovgrad1,R.drawable.dimitrovgrad1};
    private int[] sSlikeGornjiMilanovac={R.drawable.gornjimilanovac1, R.drawable.gornjimilanovac2, R.drawable.gornjimilanovac3,R.drawable.gornjimilanovac1};
    private int[] sSlikeJagodina={R.drawable.jagodina1, R.drawable.jagodina2, R.drawable.jagodina3,R.drawable.jagodina1};
    private int[] sSlikeKrusevac={R.drawable.krusevac1, R.drawable.krusevac2,R.drawable.krusevac1, R.drawable.krusevac2};
    private int[] sSlikeLeskovac={R.drawable.leskovac1, R.drawable.leskovac2,R.drawable.leskovac1, R.drawable.leskovac2};
    private int[] sSlikeMladenovac={R.drawable.mladenovac1, R.drawable.mladenovac2,R.drawable.mladenovac1, R.drawable.mladenovac2};
    private int[] sSlikeNovaVaros={R.drawable.novavaros1, R.drawable.novavaros2,R.drawable.novavaros1, R.drawable.novavaros2};
    private int[] sSlikeNoviPazar={R.drawable.novipazar1, R.drawable.novipazar2, R.drawable.novipazar3, R.drawable.novipazar1};
    private int[] sSlikePancevo={R.drawable.pancevo1, R.drawable.pancevo2, R.drawable.pancevo3, R.drawable.pancevo1};
    private int[] sSlikeParacin={R.drawable.paracin1, R.drawable.paracin2, R.drawable.paracin3, R.drawable.paracin1};
    private int[] sSlikePirot={R.drawable.pirot1, R.drawable.pirot2, R.drawable.pirot1, R.drawable.pirot2};
    private int[] sSlikeSabac={R.drawable.sabac1, R.drawable.sabac2, R.drawable.sabac3, R.drawable.sabac1};
    private int[] sSlikeSmederevo={R.drawable.smederevo1, R.drawable.smederevo2, R.drawable.smederevo3, R.drawable.smederevo1};
    private int[] sSlikeSombor={R.drawable.sombor1, R.drawable.sombor2, R.drawable.sombor3, R.drawable.sombor1};
    private int[] sSlikeSubotica={R.drawable.subotica1, R.drawable.subotica2, R.drawable.subotica3, R.drawable.subotica1};
    private int[] sSlikeTopola={R.drawable.topola1,R.drawable.topola1,R.drawable.topola1,R.drawable.topola1};
    private int[] sSlikeVranje={R.drawable.vranje1,R.drawable.vranje2, R.drawable.vranje3, R.drawable.vranje1};
    private int[] sSlikeVrsac={R.drawable.vrsac1, R.drawable.vrsac2, R.drawable.vrsac3, R.drawable.vrsac1};
    private int[] sSlikeZajecar={R.drawable.zajecar1, R.drawable.zajecar2, R.drawable.zajecar1, R.drawable.zajecar2 };
    private int[] sSlikeZrenjanin={R.drawable.zrenjanin1, R.drawable.zrenjanin2, R.drawable.zrenjanin3, R.drawable.zrenjanin1};
    private int[] sSlikeCrnaTrava={R.drawable.crnatrava1, R.drawable.crnatrava1, R.drawable.crnatrava1, R.drawable.crnatrava1};
    private int[] sSlikeTrgoviste={R.drawable.trgoviste1, R.drawable.trgoviste1, R.drawable.trgoviste1, R.drawable.trgoviste1};

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
        }else if(za.getIzabraniGrad().equals("Jagodina")) {
            sl.setImageResource(sSlikeJagodina[position]);
        }else if(za.getIzabraniGrad().equals("Arandjelovac")) {
            sl.setImageResource(sSlikeArandjelovac[position]);
        }else if(za.getIzabraniGrad().equals("Vranje")) {
            sl.setImageResource(sSlikeVranje[position]);
        }else if(za.getIzabraniGrad().equals("Bela Crkva")) {
            sl.setImageResource(sSlikeBelaCrkva[position]);
        }else if(za.getIzabraniGrad().equals("Bujanovac")) {
            sl.setImageResource(sSlikeBujanovac[position]);
        }else if(za.getIzabraniGrad().equals("Crna Trava")) {
            sl.setImageResource(sSlikeCrnaTrava[position]);
        }else if(za.getIzabraniGrad().equals("Dimitrovgrad")) {
            sl.setImageResource(sSlikeDimitrovgrad[position]);
        }else if(za.getIzabraniGrad().equals("Gornji Milanovac")) {
            sl.setImageResource(sSlikeGornjiMilanovac[position]);
        }else if(za.getIzabraniGrad().equals("Leskovac")) {
            sl.setImageResource(sSlikeLeskovac[position]);
        }else if(za.getIzabraniGrad().equals("Mladenovac")) {
            sl.setImageResource(sSlikeMladenovac[position]);
        }else if(za.getIzabraniGrad().equals("Pancevo")) {
            sl.setImageResource(sSlikePancevo[position]);
        }else if(za.getIzabraniGrad().equals("Paracin")) {
            sl.setImageResource(sSlikeParacin[position]);
        }else if(za.getIzabraniGrad().equals("Pirot")) {
            sl.setImageResource(sSlikePirot[position]);
        }else if(za.getIzabraniGrad().equals("Trgoviste")) {
            sl.setImageResource(sSlikeTrgoviste[position]);
        }else if(za.getIzabraniGrad().equals("Zajecar")) {
            sl.setImageResource(sSlikeZajecar[position]);
        }else if(za.getIzabraniGrad().equals("Sabac")) {
            sl.setImageResource(sSlikeSabac[position]);
        }else if(za.getIzabraniGrad().equals("Subotica")) {
            sl.setImageResource(sSlikeSubotica[position]);
        }else if(za.getIzabraniGrad().equals("Zrenjanin")) {
            sl.setImageResource(sSlikeZrenjanin[position]);
        }else if(za.getIzabraniGrad().equals("Krusevac")) {
            sl.setImageResource(sSlikeKrusevac[position]);
        }else if(za.getIzabraniGrad().equals("Nova Varos")) {
            sl.setImageResource(sSlikeNovaVaros[position]);
        }else if(za.getIzabraniGrad().equals("Novi Pazar")) {
            sl.setImageResource(sSlikeNoviPazar[position]);
        }else if(za.getIzabraniGrad().equals("Vrsac")) {
            sl.setImageResource(sSlikeVrsac[position]);
        }else if(za.getIzabraniGrad().equals("Bor")) {
            sl.setImageResource(sSlikeBor[position]);
        }else if(za.getIzabraniGrad().equals("Smederevo")) {
            sl.setImageResource(sSlikeSmederevo[position]);
        }else if(za.getIzabraniGrad().equals("Topola")) {
            sl.setImageResource(sSlikeTopola[position]);
        }else if(za.getIzabraniGrad().equals("Sombor")) {
            sl.setImageResource(sSlikeSombor[position]);
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
