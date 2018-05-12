package com.mzbcacak.kele.cityinfosrbija;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Banke extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GridLayoutManager glm;
    private BankeAdapter adapter;
    private List<String> banke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banke);

        recyclerView = (RecyclerView) findViewById(R.id.recView);
        banke = new ArrayList<String>();
        banke.add("addiko");
        banke.add("aik");
        banke.add("alpha");
        banke.add("erste");
        banke.add("eurobank");
        banke.add("halkbank");
        banke.add("intesa");
        banke.add("komercijalna");
        banke.add("marfin");
        banke.add("nlb");
        banke.add("opportunity");
        banke.add("otp");
        banke.add("piraeus");
        banke.add("procredit");
        banke.add("raiffeisen");
        banke.add("sber");
        banke.add("societe");
        banke.add("unicredit");
        banke.add("vojvodjanska");

        glm = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(glm);

        adapter = new BankeAdapter(this,banke);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        FooterFragment ff = (FooterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment21);
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
