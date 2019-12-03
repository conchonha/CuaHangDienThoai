package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.HoaDon;
import com.example.myapplication.R;

import java.util.ArrayList;

public class Adapter_HoaDon extends RecyclerView.Adapter<Adapter_HoaDon.Viewhodler> {
    private ArrayList<HoaDon>arrayList;
    private int layout;
    private Context context;
    private View view;

    public Adapter_HoaDon(ArrayList<HoaDon> arrayList, int layout, Context context) {
        this.arrayList = arrayList;
        this.layout = layout;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,layout,null);
        return new Viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodler holder, int position) {
        HoaDon hoaDon=arrayList.get(position);
        holder.stt.setText(position+1+"");
        int solong=hoaDon.getSoluongsanpham();
        int gia=hoaDon.getGiasanpham();
        int dongia=gia/solong;
        holder.txtdongia.setText(dongia+" Đ");
        holder.txtsoluong.setText(solong+"");
        holder.txttensanpham1.setText(hoaDon.getTensanpham());
        holder.txtthanhtien.setText(gia+" Đ");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private TextView stt,txttensanpham1,txtsoluong,txtdongia,txtthanhtien;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            stt=itemView.findViewById(R.id.stt);
            txttensanpham1=itemView.findViewById(R.id.txttensanpham1);
            txtsoluong=itemView.findViewById(R.id.txtsoluong);
            txtdongia=itemView.findViewById(R.id.txtdongia);
            txtthanhtien=itemView.findViewById(R.id.txtthanhtien);
        }
    }
}
