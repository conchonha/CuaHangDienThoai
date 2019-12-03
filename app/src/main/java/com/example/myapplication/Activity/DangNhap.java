package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Activity.admin.Admin;
import com.example.myapplication.Model.Taikhoan;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIServices;
import com.example.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhap extends AppCompatActivity {
    private EditText edttendangnhap,edtpassword;
    private Button btndangnhap;
    private ImageView cloud1,star;
    private TextView txtdangky;
    Animation animCloud,animStar;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        anhxxa();
        if(!sharedPreferences.getString("khachhang","").equals("")){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        if(!sharedPreferences.getString("admin","").equals("")){
            startActivity(new Intent(getApplicationContext(),Admin.class));
        }
    }

    private void anhxxa() {
        txtdangky=findViewById(R.id.txtdangky);
        txtdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DangKy.class));
            }
        });
        sharedPreferences=getSharedPreferences("datalogin",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        cloud1      = findViewById(R.id.cloud1);
        star        = findViewById(R.id.star);
        animCloud   = AnimationUtils.loadAnimation(this,R.anim.animcloud);
        animStar    = AnimationUtils.loadAnimation(this,R.anim.animstar);
        cloud1.startAnimation(animCloud);
        star.startAnimation(animStar);
        edttendangnhap=findViewById(R.id.edttendangnhap);
        edtpassword=findViewById(R.id.edtpassword);
        btndangnhap=findViewById(R.id.btndangnhap);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtpassword.getText().toString().equals("") || edttendangnhap.getText().toString().equals("")){
                    Toast.makeText(DangNhap.this, "Không để trống dữ liệu", Toast.LENGTH_SHORT).show();
                }else{
                    DataService dataService= APIServices.getService();
                    Call<List<Taikhoan>>callback=dataService.dangnhap(edttendangnhap.getText().toString(),edtpassword.getText().toString());
                    callback.enqueue(new Callback<List<Taikhoan>>() {
                        @Override
                        public void onResponse(Call<List<Taikhoan>> call, Response<List<Taikhoan>> response) {
                            Log.d("AAA","Dangnhap: "+response.toString());
                            if(response.isSuccessful()){

                                ArrayList<Taikhoan>arrayList= (ArrayList<Taikhoan>) response.body();
                                if(arrayList.size()>0){
                                    editor.remove("admin");
                                    editor.putString("khachhang","khachhang");
                                    editor.putString("username", edttendangnhap.getText().toString());
                                    editor.putString("password", edtpassword.getText().toString());
                                    editor.putInt("iduser", arrayList.get(0).getIdUser());
                                    editor.putString("email", arrayList.get(0).getEmail());
                                    editor.putString("sodienthoai", arrayList.get(0).getSoDienThoai());
                                    editor.putString("diachi",arrayList.get(0).getDiaChi());
                                    if(arrayList.get(0).getHinhanh().endsWith("jpg")){
                                        editor.putString("hinhanh",APIServices.urlhinh+arrayList.get(0).getHinhanh());
                                    }else{
                                        editor.putString("hinhanh",arrayList.get(0).getHinhanh());
                                    }
                                    editor.commit();
                                    Toast.makeText(DangNhap.this, "Đăng Nhập thành công", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    finish();
                                }else{
                                    if(edttendangnhap.getText().toString().equals("admin") && edtpassword.getText().toString().equals("123456")){
                                        editor.putString("admin","admin");
                                        editor.remove("khachhang");
                                        editor.commit();
                                        startActivity(new Intent(getApplicationContext(), Admin.class));
                                    }
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<List<Taikhoan>> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}
