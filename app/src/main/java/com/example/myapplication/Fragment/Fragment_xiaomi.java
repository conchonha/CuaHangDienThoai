package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.SanPham;
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

public class Fragment_xiaomi extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    Adapter_RecyclerviewSanpham adapter_recyclerviewSanpham;
    private ArrayList<Sanpham>arrayList;
    private TextView txtxemthemxiaomi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_xiaomi,container,false);
        anhxa();
        getdatasanpham();
        onclickxemthem();
        return view;
    }

    private void onclickxemthem() {
        txtxemthemxiaomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SanPham.class);
                intent.putExtra("id",5);
                startActivity(intent);
            }
        });
    }

    private void getdatasanpham() {
        DataService dataService= APIServices.getService();
        Call<List<Sanpham>>callback=dataService.getdataxiaomi();
        callback.enqueue(new Callback<List<Sanpham>>() {
            @Override
            public void onResponse(Call<List<Sanpham>> call, Response<List<Sanpham>> response) {
                Log.d("AAA","getdata huawei"+response.toString());
                if(response.isSuccessful()){
                    arrayList= (ArrayList<Sanpham>) response.body();
                    adapter_recyclerviewSanpham=new Adapter_RecyclerviewSanpham(arrayList,getContext());
                    recyclerView.setAdapter(adapter_recyclerviewSanpham);
                    adapter_recyclerviewSanpham.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Sanpham>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        txtxemthemxiaomi=view.findViewById(R.id.txtxemthemxiaomi);
        arrayList=new ArrayList<>();
        recyclerView=view.findViewById(R.id.recyclerviewhuawei);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

    }
}
