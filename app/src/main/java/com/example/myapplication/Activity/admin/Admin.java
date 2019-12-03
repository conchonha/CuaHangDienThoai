package com.example.myapplication.Activity.admin;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Activity.DangNhap;
import com.example.myapplication.R;

public class Admin extends AppCompatActivity {
    private ImageView imgquanlytaikhoan,imgdangxuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        imgquanlytaikhoan=findViewById(R.id.imgquanlytaikhoan);
        imgquanlytaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),QuanLytaikhoan.class));
            }
        });
        imgdangxuat=findViewById(R.id.imgdangxuat);
        imgdangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap.editor.remove("admin");
                DangNhap.editor.commit();
                startActivity(new Intent(getApplicationContext(), DangNhap.class));
            }
        });
    }
}
