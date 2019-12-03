package com.example.myapplication.Activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.Adapter_Donhang;
import com.example.myapplication.Model.DonDatHang;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIServices;
import com.example.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonHang extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Toolbar toolbardonhang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        getdatadondathang();
        anhxa();
    }

    private void anhxa() {
        toolbardonhang=findViewById(R.id.toolbardonhang);
        setSupportActionBar(toolbardonhang);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbardonhang.setNavigationIcon(R.drawable.back);
        toolbardonhang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getdatadondathang() {
        DataService dataService= APIServices.getService();
        Call<List<DonDatHang>>call=dataService.getdatadonhang(DangNhap.sharedPreferences.getInt("iduser",0)+"");
        call.enqueue(new Callback<List<DonDatHang>>() {
            @Override
            public void onResponse(Call<List<DonDatHang>> call, Response<List<DonDatHang>> response) {
                Log.d("AAA","getdatadonhang: "+response.toString());
                if(response.isSuccessful()){
                    ArrayList<DonDatHang>arrayList= (ArrayList<DonDatHang>) response.body();
                    setRecyclerview(arrayList);
                    Log.d("AAA",DangNhap.sharedPreferences.getInt("iduser",0)+"");
                }
            }

            @Override
            public void onFailure(Call<List<DonDatHang>> call, Throwable t) {

            }
        });
    }

    private void setRecyclerview(ArrayList<DonDatHang>arrayList) {
        recyclerView=findViewById(R.id.recyclerviewdonhang);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        Adapter_Donhang adapter_donhang=new Adapter_Donhang(arrayList,getApplicationContext(),R.layout.layoutdonhang);
        recyclerView.setAdapter(adapter_donhang);
        adapter_donhang.notifyDataSetChanged();
    }
}
