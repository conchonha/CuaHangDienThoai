package com.example.myapplication.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.Adapter_HoaDon;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIServices;
import com.example.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoaDon extends AppCompatActivity {
    private TextView txtmadonhang,txttentaikhoan,txtemailhoadon,txtsodienthoai,txtngaymua,txttrinhtranghoadon,
            txtdiachihoadon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        init();
        getintent();
        actionbar();
    }

    private void actionbar() {
        Toolbar toolbar=findViewById(R.id.toolbarHoadon);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getintent() {
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("iddonhang")){
                String iddonhang=intent.getStringExtra("iddonhang");
                DataService dataService= APIServices.getService();
                Call<List<com.example.myapplication.Model.HoaDon>>call=dataService.getAlldatadonhang(iddonhang);
                call.enqueue(new Callback<List<com.example.myapplication.Model.HoaDon>>() {
                    @Override
                    public void onResponse(Call<List<com.example.myapplication.Model.HoaDon>> call, Response<List<com.example.myapplication.Model.HoaDon>> response) {
                        Log.d("AAA","HoaDon: "+response.toString());
                        if(response.isSuccessful()){
                            ArrayList<com.example.myapplication.Model.HoaDon>arrayList= (ArrayList<com.example.myapplication.Model.HoaDon>) response.body();
                            com.example.myapplication.Model.HoaDon hoaDon=arrayList.get(0);
                            txtmadonhang.setText(hoaDon.getIddondathang()+"");
                            txttentaikhoan.setText(hoaDon.getTentaikhoan());
                            txtemailhoadon.setText(hoaDon.getEmail());
                            txtsodienthoai.setText(hoaDon.getSodienthoai());
                            txtngaymua.setText(hoaDon.getNgaydat());
                            txttrinhtranghoadon.setText(hoaDon.getTrinhtrang());
                            txtdiachihoadon.setText(hoaDon.getDiachi());
                            setrecyclerview(arrayList);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<com.example.myapplication.Model.HoaDon>> call, Throwable t) {

                    }
                });
            }
        }
    }

    private void setrecyclerview(ArrayList<com.example.myapplication.Model.HoaDon> arrayList) {
        RecyclerView recyclerView=findViewById(R.id.recyclerviewhoadon);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerView.setHasFixedSize(true);
        Adapter_HoaDon adapter_hoaDon=new Adapter_HoaDon(arrayList,R.layout.layout_hoadon,getApplicationContext());
        recyclerView.setAdapter(adapter_hoaDon);
        adapter_hoaDon.notifyDataSetChanged();
    }

    private void init() {
        txtmadonhang=findViewById(R.id.txtmadonhang);
        txttentaikhoan=findViewById(R.id.txttentaikhoan);
        txtemailhoadon=findViewById(R.id.txtemailhoadon);
        txtsodienthoai=findViewById(R.id.txtsodienthoai);
        txtngaymua=findViewById(R.id.txtngaymua);
        txttrinhtranghoadon=findViewById(R.id.txttrinhtranghoadon);
        txtdiachihoadon=findViewById(R.id.txtdiachihoadon);
    }
}
