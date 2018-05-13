package com.example.kele.test_1;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.mzbcacak.kele.cityinfosrbija.R;

import java.util.ArrayList;
import java.util.Arrays;

public class FooterFragment extends Fragment {
    public static ImageButton meni;
    public static ImageButton info;
    public static ImageButton pozivi;
    public static ImageButton mojaLista;
    public static ImageButton podesavanja;
    public static Toolbar footerToolbar;
    private String omiljenoLista[];
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private ArrayList<String> omiljenoAL;
    private ArrayList<Integer> ikn;
    private RecyclerView recyclerView;
    private GridLayoutManager glm;
    private MenuSledeciAdapter menuAdapter;
    int otvorena;
    Intent prikazi;
    private int[] ikoniceNiz= {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.k
    };

    public FooterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_footer,container,false);
        meni = (ImageButton) view.findViewById(R.id.imageMeni);
        info = (ImageButton) view.findViewById(R.id.imageInfo);
        pozivi = (ImageButton) view.findViewById(R.id.imagePozivi);
        mojaLista = (ImageButton) view.findViewById(R.id.imageProfil);
        footerToolbar = (Toolbar) view.findViewById(R.id.footerToolbar);
        podesavanja = (ImageButton) view.findViewById(R.id.imagePodesavanja);

        prikazi = new Intent(getActivity(),ListaObjekata.class);

        otvorena = 0;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        final int height = displaymetrics.heightPixels;
        final int width = displaymetrics.widthPixels;


        meni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(getActivity(),GlavniMeni.class);
                startActivity(m);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iInfo = new Intent(getActivity(),Info.class);
                startActivity(iInfo);
            }
        });

        pozivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent poziv = new Intent(getActivity(),Pozivi.class);
                startActivity(poziv);
            }
        });

        podesavanja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pod = new Intent(getActivity(),Podesavanja.class);
                startActivity(pod);
            }
        });
        mojaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_moja_lista, null);
                recyclerView = (RecyclerView) popupView.findViewById(R.id.recyViewFooter);

                SharedPreferences loginData = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String om = loginData.getString("omiljeno", "");
                IzaberiGrad zaIkonice = new IzaberiGrad();
                omiljenoAL= new ArrayList<String>(Arrays.asList(om.split(",")));
                ikn = new ArrayList<Integer>();
                int l = omiljenoAL.size();
                for(int i=0;i<l;i++){
                    ikn.add(zaIkonice.ikonice.get(omiljenoAL.get(i)));
                }

                glm = new GridLayoutManager(getActivity(),3);
                recyclerView.setLayoutManager(glm);

                menuAdapter = new MenuSledeciAdapter(getActivity(),omiljenoAL,ikn);
                recyclerView.setAdapter(menuAdapter);
                menuAdapter.notifyDataSetChanged();

                if(otvorena==0){
                    final PopupWindow popupWindow = new PopupWindow(popupView,width,(height-(2*footerToolbar.getHeight()+getStatusBarHeight())));
                    popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, footerToolbar.getHeight());
                    popupWindow.setFocusable(true);
                    otvorena = 1;
                    popupView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            getActivity().recreate();
                            otvorena = 0;
                        }
                    });
                }
            }
        });
        return view;
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    View.OnClickListener getOnClickDoSomething(final Button button)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                prikazi.putExtra("pretrazi",button.getText().toString());
                prikazi.putExtra("funkcija",1);
                startActivity(prikazi);
            }
        };
    }
    View.OnLongClickListener getOnLong(final Button button){
        return new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                SharedPreferences loginData = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = loginData.edit();
                omiljenoAL.remove(button.getText().toString());
                String om="";
                for(int i = 0;i<omiljenoAL.size();i++){
                    om = om + omiljenoAL.get(i)+",";
                }
                editor.putString("omiljeno", om);
                editor.apply();
                Toast.makeText(getActivity(),om+"\n"+omiljenoAL.size(),Toast.LENGTH_LONG).show();
                return true;
            }
        };
    }
}
