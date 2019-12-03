package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.Activity.DangNhap;
import com.example.myapplication.Activity.HoaDon;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIServices;
import com.example.myapplication.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dialog_FragmentThanhtoan extends DialogFragment {
    private View view;
    private TextView txttenkhachang,txtemail,txtdiachi,txtsdt;
    private Button btnthanhtoandialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialog_fragment_thanhtoan,container,false);
        anhxa();
        btnthanhtoandialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtdiachi.getText().toString().equals("") || txtemail.getText().toString().equals("") ||
                txtsdt.getText().toString().equals("") || txttenkhachang.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Không để trống dữ liệu", Toast.LENGTH_SHORT).show();
                }else if(!txtemail.getText().toString().endsWith("@gmail.com")){
                    Toast.makeText(getActivity(), "Sai email", Toast.LENGTH_SHORT).show();
                }else if(txtsdt.getText().toString().length()!=10){
                    Toast.makeText(getActivity(), "Sai sdt", Toast.LENGTH_SHORT).show();
                }else{
                    DataService dataService= APIServices.getService();
                    Call<String>call=dataService.postgiohang(DangNhap.sharedPreferences.getInt("iduser",0)+"",
                            txttenkhachang.getText().toString(),
                            txtemail.getText().toString(),
                            txtdiachi.getText().toString(),
                            txtsdt.getText().toString()
                            );
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("AAA","loi: "+response.toString());
                            if(response.isSuccessful()){
                                String id="";
                                 id=response.body();
                                Log.d("AAA","iddonhang: "+response.toString());
                                Log.d("AAA","iddonhang: "+id);
                                Toast.makeText(getActivity(), "Thanh Toán Thành Công", Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                                startActivity(getActivity().getIntent());
                                Intent intent=new Intent(getActivity(),HoaDon.class);
                                intent.putExtra("iddonhang",id);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });

                }
            }
        });
        return view;
    }

    private void anhxa() {
        btnthanhtoandialog=view.findViewById(R.id.btnthanhtoandialog);
        txttenkhachang=view.findViewById(R.id.txttenkhachang);
        txtemail=view.findViewById(R.id.txtemail);
        txtdiachi=view.findViewById(R.id.txtdiachi);
        txtsdt=view.findViewById(R.id.txtsdt);
        txttenkhachang=view.findViewById(R.id.txttenkhachang);
        if(DangNhap.sharedPreferences.getString("username","").equals("")){
            startActivity(new Intent(getContext(),DangNhap.class));
        }else{
            txttenkhachang.setText(DangNhap.sharedPreferences.getString("username",""));
            txtdiachi.setText(DangNhap.sharedPreferences.getString("diachi",""));
            txtemail.setText(DangNhap.sharedPreferences.getString("email",""));
            txtsdt.setText(DangNhap.sharedPreferences.getString("sodienthoai",""));

        }
    }
}
