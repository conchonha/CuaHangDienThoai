package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Taikhoan;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIServices;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Quanlytaikhoan extends RecyclerView.Adapter<Adapter_Quanlytaikhoan.Viewhlder>{
    private Context context;
    private int layout;
    private ArrayList<Taikhoan>arrayList;
    private View view;

    public Adapter_Quanlytaikhoan(Context context, int layout, ArrayList<Taikhoan> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewhlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,layout,null);
        return new Viewhlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhlder holder, int position) {
        Taikhoan taikhoan=arrayList.get(position);
        holder.txtdiachiadmin.setText(taikhoan.getDiaChi());
        holder.txtemailadmin.setText(taikhoan.getEmail());
        holder.txtmakhachhangadmin.setText(taikhoan.getIdUser()+"");
        holder.txtpasswordadmin.setText(taikhoan.getPassword());
        holder.txtsodienthoaiadmin.setText(taikhoan.getSoDienThoai());
        holder.txttentaikhoanadmin.setText(taikhoan.getUsername());
        if(taikhoan.getHinhanh().endsWith("jpg")){
            Picasso.with(context).load(APIServices.urlhinh+taikhoan.getHinhanh()).into(holder.imghinhanhtaikhoanadmin);
        }else{
            Picasso.with(context).load(taikhoan.getHinhanh()).into(holder.imghinhanhtaikhoanadmin);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhlder extends RecyclerView.ViewHolder{
        private TextView txtmakhachhangadmin,txttentaikhoanadmin,txtpasswordadmin,txtsodienthoaiadmin,txtemailadmin,txtdiachiadmin;
        private ImageView imghinhanhtaikhoanadmin;
        public Viewhlder(@NonNull View itemView) {
            super(itemView);
            txtmakhachhangadmin=itemView.findViewById(R.id.txtmakhachhangadmin);
            imghinhanhtaikhoanadmin=itemView.findViewById(R.id.imghinhanhtaikhoanadmin);
            txttentaikhoanadmin=itemView.findViewById(R.id.txttentaikhoanadmin);
            txtpasswordadmin=itemView.findViewById(R.id.txtpasswordadmin);
            txtsodienthoaiadmin=itemView.findViewById(R.id.txtsodienthoaiadmin);
            txtemailadmin=itemView.findViewById(R.id.txtemailadmin);
            txtdiachiadmin=itemView.findViewById(R.id.txtdiachiadmin);
        }
    }
}
