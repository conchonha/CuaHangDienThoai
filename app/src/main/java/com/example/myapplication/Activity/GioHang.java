package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Adapter.Adapter_Giohang;
import com.example.myapplication.Fragment.Dialog_FragmentThanhtoan;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIServices;
import com.example.myapplication.Service.DataService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHang extends AppCompatActivity {
    private RecyclerView recyclerviewgiohang;
    private Toolbar toolbargiohang;
    private TextView txtgiatong;
    private Button btnthanhtoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhxa();
        getdatagiohang();
        btnthanhtoan();
    }

    private void btnthanhtoan() {
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                Dialog_FragmentThanhtoan dialog_fragmentThanhtoan=new Dialog_FragmentThanhtoan();
                dialog_fragmentThanhtoan.show(fragmentManager,"thanhtoan");
            }
        });
    }

    private void anhxa() {
        btnthanhtoan=findViewById(R.id.btnthanhtoan);
        txtgiatong=findViewById(R.id.txtgiatong);
        toolbargiohang=findViewById(R.id.toolbargiohang);
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbargiohang.setNavigationIcon(R.drawable.back);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerviewgiohang=findViewById(R.id.recyclerviewgiohang);
        recyclerviewgiohang.setHasFixedSize(true);
        recyclerviewgiohang.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
    }

    private void getdatagiohang() {
        DataService dataService= APIServices.getService();
        Call<List<com.example.myapplication.Model.GioHang>>callback=dataService.getdatagiohang(DangNhap.sharedPreferences
        .getInt("iduser",0)+"");
        callback.enqueue(new Callback<List<com.example.myapplication.Model.GioHang>>() {
            @Override
            public void onResponse(Call<List<com.example.myapplication.Model.GioHang>> call, Response<List<com.example.myapplication.Model.GioHang>> response) {
                Log.d("AAA","getdata giohang: "+response.toString());
                if(response.isSuccessful()){
                    ArrayList<com.example.myapplication.Model.GioHang>arrayList= (ArrayList<com.example.myapplication.Model.GioHang>) response.body();
                    Adapter_Giohang adapter=new Adapter_Giohang(arrayList,GioHang.this);
                    recyclerviewgiohang.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    int tong=0;
                    for (int i=0;i<arrayList.size();i++){
                        tong+=arrayList.get(i).getGia();
                    }
                    DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                    txtgiatong.setText(decimalFormat.format(tong)+" Đ");
                }
            }

            @Override
            public void onFailure(Call<List<com.example.myapplication.Model.GioHang>> call, Throwable t) {

            }
        });
    }
    public void Deletegiohang(String id){
        DataService dataService=APIServices.getService();
        Call<String>call=dataService.deletegiohang(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("AAA","deletegiohang: "+response.toString());
                if(response.isSuccessful()){
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    public void tang(String id){
        DataService dataService=APIServices.getService();
        Call<String>call=dataService.tang(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("AAA","tanggiohang: "+response.toString());
                if(response.isSuccessful()){
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    public void giam(String id){
        DataService dataService=APIServices.getService();
        Call<String>call=dataService.giam(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("AAA","tanggiohang: "+response.toString());
                if(response.isSuccessful()){
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
