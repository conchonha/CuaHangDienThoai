package com.example.myapplication.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.Service.APIServices;
import com.example.myapplication.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKy extends AppCompatActivity {
    private EditText text_UserName,text_PassWord,text_PhoneNumBer,text_Email,text_Adress;
    private Button btndangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        anhxa();
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text_UserName.getText().toString().equals("") || text_PassWord.getText().toString().equals("") ||
                        text_PhoneNumBer.getText().toString().equals("") || text_Email.getText().toString().equals("") ||
                        text_Adress.getText().toString().equals("")){
                    Toast.makeText(DangKy.this, "Không được để trống dữ liệu", Toast.LENGTH_SHORT).show();
                }else{
                    DataService dataService= APIServices.getService();
                    Call<String>callback=dataService.dangkytaikhoan(text_UserName.getText().toString(),
                            text_PassWord.getText().toString(),
                            text_PhoneNumBer.getText().toString(),
                            text_Email.getText().toString(),
                            text_Adress.getText().toString());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("AAA","Create Tai Khoan: "+response.toString());
                            if(response.isSuccessful()){
                                Toast.makeText(DangKy.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),DangNhap.class));
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    private void anhxa() {
        text_UserName=findViewById(R.id.text_UserName);
        text_PassWord=findViewById(R.id.text_PassWord);
        text_PhoneNumBer=findViewById(R.id.text_PhoneNumBer);
        text_Email=findViewById(R.id.text_Email);
        text_Adress=findViewById(R.id.text_Adress);
        btndangky=findViewById(R.id.btndangky);
    }
}
