package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.GioHang;
import com.example.myapplication.R;
import com.example.myapplication.Service.DataService;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_Giohang extends RecyclerView.Adapter<Adapter_Giohang.Viewhdler> {
    private ArrayList<GioHang>arrayList;
    private com.example.myapplication.Activity.GioHang context;
    private View view;

    public Adapter_Giohang(ArrayList<GioHang> arrayList, com.example.myapplication.Activity.GioHang context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewhdler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,R.layout.layout_giohang,null);
        return new Viewhdler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhdler holder, int position) {
        final GioHang gioHang=arrayList.get(position);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtgiasanphamgiohang.setText(decimalFormat.format(gioHang.getGia())+"");
        holder.txtsoluonggiohang.setText(gioHang.getSoluong()+"");
        Picasso.with(context).load(gioHang.getHinhsp()).into(holder.imggiohang11);
        holder.imgbttonxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.Deletegiohang(gioHang.getIdgiohang()+"");
            }
        });
        holder.btntang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            context.tang(gioHang.getIdgiohang()+"");
            }
        });
        holder.btngiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.giam(gioHang.getIdgiohang()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhdler extends RecyclerView.ViewHolder{
        private ImageView imggiohang11,imgbttonxoa;
        private TextView txtgiasanphamgiohang,txtsoluonggiohang;
        private Button btngiam,btntang;
        public Viewhdler(@NonNull View itemView) {
            super(itemView);
            imggiohang11=itemView.findViewById(R.id.imggiohang11);
            imgbttonxoa=itemView.findViewById(R.id.imgbttonxoa);
            txtgiasanphamgiohang=itemView.findViewById(R.id.txtgiasanphamgiohang);
            txtsoluonggiohang=itemView.findViewById(R.id.txtsoluonggiohang);
            btngiam=itemView.findViewById(R.id.btngiam);
            btntang=itemView.findViewById(R.id.btntang);
        }
    }
}
