package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.Adapter.Adapter_RecyclerviewSanpham;
import com.example.myapplication.Model.Sanpham;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIServices;
import com.example.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanPham extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        anhxa();
        Intent intent=getIntent();
        int id=intent.getIntExtra("id",4);
        final RecyclerView recyclerviewsanpham=findViewById(R.id.recyclerviewsanpham);
        recyclerviewsanpham.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerviewsanpham.setHasFixedSize(true);

        DataService dataService= APIServices.getService();
        Call<List<Sanpham>>callback=dataService.getdatasanpham(id+"");
        callback.enqueue(new Callback<List<Sanpham>>() {
            @Override
            public void onResponse(Call<List<Sanpham>> call, Response<List<Sanpham>> response) {
                Log.d("AAA","getdatasanpham"+response.toString());
                if(response.isSuccessful()){
                    ArrayList<Sanpham> arrayList = (ArrayList<Sanpham>) response.body();
                    final Adapter_RecyclerviewSanpham adapter=new Adapter_RecyclerviewSanpham(arrayList,SanPham.this);
                    recyclerviewsanpham.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Sanpham>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        Toolbar toolbarsanpham=findViewById(R.id.toolbarsanpham);
        setSupportActionBar(toolbarsanpham);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarsanpham.setNavigationIcon(R.drawable.back);
        toolbarsanpham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
