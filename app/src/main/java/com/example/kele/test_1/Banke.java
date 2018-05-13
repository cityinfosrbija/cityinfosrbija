package com.example.kele.test_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mzbcacak.kele.cityinfosrbija.R;

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
        banke.add("alfa");
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
}
