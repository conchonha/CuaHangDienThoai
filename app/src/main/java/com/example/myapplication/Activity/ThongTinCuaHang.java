package com.example.myapplication.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

public class ThongTinCuaHang extends AppCompatActivity {
    private ImageView imggioithieu,imgvitri,imglienhe,imgfanbage;
    private Toolbar toolbargioithieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_cua_hang);
        anhxa();
    }

    private void anhxa() {
        toolbargioithieu=findViewById(R.id.toolbargioithieu);
        setSupportActionBar(toolbargioithieu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbargioithieu.setNavigationIcon(R.drawable.back);
        toolbargioithieu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgfanbage=findViewById(R.id.imgfanbage);
        imglienhe=findViewById(R.id.imglienhe);
        imggioithieu=findViewById(R.id.imggioithieu);
        imggioithieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GioiThieuCuaHang.class));
            }
        });
        imgvitri=findViewById(R.id.imgvitri);
        imgvitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Vvitri.class));
            }
        });
        imglienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LienHe.class));
            }
        });
        imgfanbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),fanbage.class));
            }
        });
    }
}
