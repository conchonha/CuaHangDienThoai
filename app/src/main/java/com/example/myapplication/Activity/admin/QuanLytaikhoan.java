package com.example.myapplication.Activity.admin;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.Adapter_Quanlytaikhoan;
import com.example.myapplication.Model.Taikhoan;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIServices;
import com.example.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLytaikhoan extends AppCompatActivity {
    private Toolbar toolbarquanlytaikhoan;
    private RecyclerView recyclerviewquanlytaikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_lytaikhoan);
        toolbarquanlytaikhoan=findViewById(R.id.toolbarquanlytaikhoan);
        setSupportActionBar(toolbarquanlytaikhoan);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarquanlytaikhoan.setNavigationIcon(R.drawable.back);
        toolbarquanlytaikhoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getdatataikhoan();
    }

    private void getdatataikhoan() {
        DataService dataService= APIServices.getService();
        Call<List<Taikhoan>>callback=dataService.getdataalltaikhoan();
        callback.enqueue(new Callback<List<Taikhoan>>() {
            @Override
            public void onResponse(Call<List<Taikhoan>> call, Response<List<Taikhoan>> response) {
                Log.d("AAA","getdata all taikhoan: "+response.toString());
                if(response.isSuccessful()){
                    ArrayList<Taikhoan>arrayList= (ArrayList<Taikhoan>) response.body();
                    setrecyclerview(arrayList);
                }
            }

            @Override
            public void onFailure(Call<List<Taikhoan>> call, Throwable t) {

            }
        });
    }

    private void setrecyclerview(ArrayList<Taikhoan>arrayList){
        recyclerviewquanlytaikhoan=findViewById(R.id.recyclerviewquanlytaikhoan);
        recyclerviewquanlytaikhoan.setHasFixedSize(true);
        recyclerviewquanlytaikhoan.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        Adapter_Quanlytaikhoan adpter=new Adapter_Quanlytaikhoan(getApplicationContext(),R.layout.layout_quanlytaikhoan,arrayList);
        recyclerviewquanlytaikhoan.setAdapter(adpter);
        adpter.notifyDataSetChanged();
    }
}
