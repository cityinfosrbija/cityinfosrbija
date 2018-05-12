package com.mzbcacak.kele.cityinfosrbija;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class GlavniMeni extends AppCompatActivity {
    private static final String TAG = "proba";
    private RecyclerView recyclerView;
    private GridLayoutManager glm;
    private MenuAdapter menuAdapter;
    private List<String> vrste;
    private IzaberiGrad izg;
    private HeaderFragment hf;
    private static String grads = "Čačak";

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

    public GlavniMeni() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavni_meni);
        hf = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.meniHeader);

        izg = new IzaberiGrad(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyView);
        String[] izStr = getResources().getStringArray(R.array.glavi_ikonice);
        vrste = new ArrayList<String>();
        for(int i=0;i<(izStr.length);i++){
            vrste.add(izStr[i]);
        }
        glm = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(glm);

        menuAdapter = new MenuAdapter(this,vrste,ikoniceNiz);
        recyclerView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        FooterFragment ff = (FooterFragment) getSupportFragmentManager().findFragmentById(R.id.meniFooter);
        if(ff.otvorena==1){
            ff.popupWindow.dismiss();
            ff.otvorena = 0;
        }else if(ff.otvorenaP==1){
            ff.popupWindowP.dismiss();
            ff.otvorenaP = 0;
        }else {
            super.onBackPressed();
        }
    }
}
