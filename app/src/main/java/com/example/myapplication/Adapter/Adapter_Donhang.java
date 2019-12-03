package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.HoaDon;
import com.example.myapplication.Model.DonDatHang;
import com.example.myapplication.R;

import java.util.ArrayList;

public class Adapter_Donhang extends RecyclerView.Adapter <Adapter_Donhang.Viewhdler>{
    private ArrayList<DonDatHang>arrayList;
    private Context context;
    private int layout;
    private View view;

    public Adapter_Donhang(ArrayList<DonDatHang> arrayList, Context context, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public Viewhdler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,layout,null);
        return new Viewhdler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhdler holder, int position) {
        DonDatHang donDatHang=arrayList.get(position);
        holder.trinhtrang.setText(donDatHang.getTrinhtrang());
        holder.ngaydat.setText(donDatHang.getNgaydat());
        holder.iddonhang.setText(donDatHang.getId()+"");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhdler extends RecyclerView.ViewHolder{
        private TextView iddonhang,ngaydat,trinhtrang;
        public Viewhdler(@NonNull View itemView) {
            super(itemView);
            iddonhang=itemView.findViewById(R.id.iddonhang);
            ngaydat=itemView.findViewById(R.id.ngaydat);
            trinhtrang=itemView.findViewById(R.id.trinhtrang);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context.getApplicationContext(), HoaDon.class);
                    intent.putExtra("iddonhang",arrayList.get(getPosition()).getId()+"");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
