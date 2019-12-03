package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

public class Banner_Adapter extends PagerAdapter {
    private View view;
    private Context context;

    private String arrayhinh[];

    public Banner_Adapter(Context context, String[] arrayhinh) {
        this.context = context;
        this.arrayhinh = arrayhinh;
    }

    @Override
    public int getCount() {
        return arrayhinh.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        view=View.inflate(context, R.layout.bannerlayout,null);
        ImageView imageView=view.findViewById(R.id.imageviewbanner);
        Picasso.with(context).load(arrayhinh[position]).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      //  super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
