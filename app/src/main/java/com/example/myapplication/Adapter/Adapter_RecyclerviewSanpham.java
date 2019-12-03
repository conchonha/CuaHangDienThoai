package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.Chitietsanpham;
import com.example.myapplication.Model.Sanpham;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.transform.Templates;

public class Adapter_RecyclerviewSanpham extends RecyclerView.Adapter <Adapter_RecyclerviewSanpham.Viewhdler>{
    private View view;
    private ArrayList<Sanpham>arrayList;
    private Context context;

    public Adapter_RecyclerviewSanpham(ArrayList<Sanpham> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewhdler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,R.layout.layoutsanpham,null);

        return new Viewhdler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhdler holder, int position) {
        Sanpham sanpham=arrayList.get(position);
        Calendar calendar=Calendar.getInstance();
        DecimalFormat simpleDateFormat=new DecimalFormat("###,###,###");
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        if(sanpham.getGiamGia()>0 && !sanpham.getNgayGiamGia().equals("")){
            Date ngaykhuyenmai= null;
            Date ngayhientai=null;
            try {
                ngaykhuyenmai = format.parse(sanpham.getNgayGiamGia()+"");
                ngayhientai=format.parse(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(ngaykhuyenmai.compareTo(ngayhientai)>0){
                Log.d("AAA","ngay"+ngayhientai);
                holder.txtsale.setText("-"+sanpham.getGiamGia()+"%");
                holder.txtgiagiam.setPaintFlags(holder.txtgiasaugiam.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG );
                holder.txtgiagiam.setText(simpleDateFormat.format(sanpham.getGiaSP())+"");
                float giagiam=(float) (100-sanpham.getGiamGia())/100;
                float giaspsaukhuyenmai=(float)giagiam*sanpham.getGiaSP();
                Log.d("AAA",giaspsaukhuyenmai+"");
                holder.txtgiasaugiam.setText(simpleDateFormat.format((int)giaspsaukhuyenmai)+"Đ");
            }
        }else{
            holder.txtgiagiam.setTextColor(Color.RED);
            holder.txtgiagiam.setText(simpleDateFormat.format(sanpham.getGiaSP())+"Đ");
            holder.txtgiasaugiam.setText("");
        }
        holder.txttensanpham.setText(sanpham.getTenSP());
        Picasso.with(context).load(sanpham.getHinhAnhSP()).into(holder.imageviewsanpham);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class  Viewhdler extends RecyclerView.ViewHolder{
        private ImageView imageviewsanpham;
        private TextView txtsale,txttensanpham,txtgiagiam,txtgiasaugiam;
        public Viewhdler(@NonNull View itemView) {
            super(itemView);
            imageviewsanpham=itemView.findViewById(R.id.imageviewsanpham);
            txtsale=itemView.findViewById(R.id.txtsale);
            txttensanpham=itemView.findViewById(R.id.txttensanpham);
            txtgiagiam=itemView.findViewById(R.id.txtgiagiam);
            txtgiasaugiam=itemView.findViewById(R.id.txtgiasaugiam);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, arrayList.get(getPosition()).getID()+"", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context, Chitietsanpham.class);
                    intent.putExtra("sanpham", arrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
