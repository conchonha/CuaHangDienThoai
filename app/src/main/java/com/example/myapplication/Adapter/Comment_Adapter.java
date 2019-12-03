package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.myapplication.Activity.DangNhap;
import com.example.myapplication.Model.Danhgia;
import com.example.myapplication.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Comment_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Danhgia>arrayList;
    private View view;

    public class Viewhodeler{
        private TextView txttenuser1,txtcommentnguoidung,txtngaythangdanhgia;
        private RatingBar ratingBar;
        private RoundedImageView img;
    }

    public Comment_Adapter(Context context, int layout, ArrayList<Danhgia> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewhodeler viewhodeler=null;
        if(view==null) {
            viewhodeler=new Viewhodeler();
            view = View.inflate(context, layout, null);
            viewhodeler.txttenuser1=view.findViewById(R.id.txttenuser1);
            viewhodeler.txtcommentnguoidung=view.findViewById(R.id.txtcommentnguoidung);
            viewhodeler.txtngaythangdanhgia=view.findViewById(R.id.txtngaythangdanhgia);
            viewhodeler.ratingBar=view.findViewById(R.id.ratingdanhgianguoidung);
            viewhodeler.img=view.findViewById(R.id.img);
            view.setTag(viewhodeler);
        }else{
            viewhodeler= (Viewhodeler) view.getTag();
        }
        viewhodeler.txttenuser1.setText(arrayList.get(i).getIdUser()+"");
        viewhodeler.txtcommentnguoidung.setText(arrayList.get(i).getComMent());
        if(!DangNhap.sharedPreferences.getString("hinhanh","").equals("")){
            Picasso.with(context).load(DangNhap.sharedPreferences.getString("hinhanh",""))
            .into(viewhodeler.img);
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        viewhodeler.txtngaythangdanhgia.setText(arrayList.get(i).getNgayDanhGia()+"");
            if(arrayList.get(i).get1sao()==1){
                viewhodeler.ratingBar.setRating(1);
            }else if(arrayList.get(i).get2sao()==1){
                viewhodeler.ratingBar.setRating(2);
            }else if(arrayList.get(i).get3sao()==1){
                viewhodeler.ratingBar.setRating(3);
            }else if(arrayList.get(i).get4sao()==1){
                viewhodeler.ratingBar.setRating(4);
            }else{
                viewhodeler.ratingBar.setRating(5);
            }

        return view;
    }

}
