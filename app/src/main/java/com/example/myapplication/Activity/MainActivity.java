package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.Banner_Adapter;
import com.example.myapplication.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbarMain;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private Handler handler;
    private Runnable runnable;
    private RoundedImageView RoundedImageViewavartar;
    private ImageView imgsearch;
    private EditText edttimkim;
    private int CurrentItem;
    private TextView txthuawe,txtxiaomi,txtrealme,txtsony,txtvivo,txtdangxuat,txttennguoidung,txtgmail,txttrangchu,txtgiohang,
            txttaikhoan,txtdonhang,txtthongtin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
        onclicknavigationbar();
        timkim();
    }

    private void timkim() {
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edttimkim.getText().toString().equals("")){
                    Intent intent=new Intent(getApplicationContext(),TimKiem.class);
                    intent.putExtra("timkim",edttimkim.getText().toString());
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "không được để trống dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onclicknavigationbar() {
        txtgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GioHang.class));

            }
        });
        txttrangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        if(!DangNhap.sharedPreferences.getString("username","").equals("")){
            txttennguoidung.setText(DangNhap.sharedPreferences.getString("username",""));
            txtgmail.setText(DangNhap.sharedPreferences.getString("email",""));
            Picasso.with(getApplicationContext()).load(DangNhap.sharedPreferences.getString("hinhanh","")).into(RoundedImageViewavartar);
        }
        if(!DangNhap.sharedPreferences.getString("username","").equals("")){
            txttennguoidung.setText("Đăng Xuất");
        }else{
            txttennguoidung.setText("Đăng Nhập");
        }
        txttaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TaiKhoan.class));
            }
        });
        txtdangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap.editor.remove("username");
                DangNhap.editor.remove("password");
                DangNhap.editor.remove("iduser");
                DangNhap.editor.remove("email");
                DangNhap.editor.remove("sodienthoai");
                DangNhap.editor.remove("diachi");
                DangNhap.editor.commit();
                startActivity(new Intent(getApplicationContext(),DangNhap.class));
            }
        });
        txtdonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DonHang.class));
            }
        });
    }

    private void init() {
        actionbar();
        setviewpager();
    }

    private void actionbar() {
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarMain.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void anhxa() {
        txtthongtin=findViewById(R.id.txtthongtinnnn);
        edttimkim=findViewById(R.id.edttimkim);
        imgsearch=findViewById(R.id.imgsearch);
        txtdonhang=findViewById(R.id.txtdonhang);
        txttaikhoan=findViewById(R.id.txttaikhoan);
        txtgiohang=findViewById(R.id.txtgiohang);
        txttrangchu=findViewById(R.id.txttrangchu);
        RoundedImageViewavartar=findViewById(R.id.RoundedImageViewavartar);
        txtgmail=findViewById(R.id.txtgmail);
        txttennguoidung=findViewById(R.id.txttennguoidung);
        txtdangxuat=findViewById(R.id.txtdangxuat);

        txthuawe=findViewById(R.id.txthuawei);
        txtrealme=findViewById(R.id.txtrealmi);
        txtsony=findViewById(R.id.txtsony1);
        txtxiaomi=findViewById(R.id.txtxiaomi1);
        txtvivo=findViewById(R.id.txtvivo1);
        viewPager=findViewById(R.id.viewpagermain);
        toolbarMain=findViewById(R.id.toolbarMain);
        drawerLayout=findViewById(R.id.drawerlayout);
        txthuawe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SanPham.class);
                intent.putExtra("id",4);
                startActivity(intent);
            }
        });
        txtrealme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SanPham.class);
                intent.putExtra("id",6);
                startActivity(intent);
            }
        });

        txtsony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SanPham.class);
                intent.putExtra("id",8);
                startActivity(intent);
            }
        });
        txtxiaomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SanPham.class);
                intent.putExtra("id",5);
                startActivity(intent);
            }
        });
        txtvivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SanPham.class);
                intent.putExtra("id",7);
                startActivity(intent);
            }
        });
        txtthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ThongTinCuaHang.class));
            }
        });
    }
    private void setviewpager(){
        String[]arrayhinh={"https://vechaidienthoai.com/wp-content/uploads/2018/02/banner-vechaidienthoai-linh-kien-dien-thoai.png",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyLVZIz5E8it00ZSsJAD0YJ53sEv9MD0kHzuALV5B9AraZ1g_R&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMjiKplojCychDHFAZ_RKCainjhBzPx8_VMgJ4y9ZaySfHeE9v&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRLksXfauk28kmt5FtiFQQ4BZ1LuXMvLx-E-NC0wHtMbdXMpzYhGg&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrFfez-G2suhVzAuKSdKJO0O67zItwoUeiUxBLp9scjKV3rEtEEQ&s"};
        final Banner_Adapter adapter=new Banner_Adapter(getApplicationContext(),arrayhinh);
        viewPager.setAdapter(adapter);
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                CurrentItem=viewPager.getCurrentItem();
                CurrentItem++;
                if(CurrentItem>=viewPager.getAdapter().getCount()){
                    CurrentItem=0;
                }
                viewPager.setCurrentItem(CurrentItem,true);
                handler.postDelayed(runnable,4500);
            }
        };
        handler.postDelayed(runnable,4500);
    }
}
