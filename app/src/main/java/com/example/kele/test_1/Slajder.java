package com.example.kele.test_1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Slajder extends PagerAdapter{

    private int[] sSlike={R.drawable.cacak1, R.drawable.cacak2, R.drawable.cacak3, R.drawable.cacak4};
    private Context cnt;
    private LayoutInflater inf;

    public Slajder(Context cnt){
        this.cnt=cnt;
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
        sl.setImageResource(sSlike[position]);
        sl.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
