package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.Activity.DangNhap;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIServices;
import com.example.myapplication.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogfragmentUpdatetk extends DialogFragment {
    private View view;
    private EditText edtussername,edtpass,edtsdt,edtemail,edtdiachi;
    private Button btnupdate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialog_fragment_update,container,false);
        anhxa();
        init();
        return view;
    }

    private void init() {
        final int iduser=DangNhap.sharedPreferences.getInt("iduser",0);
        edtussername.setText(DangNhap.sharedPreferences.getString("username",""));
        edtdiachi.setText(DangNhap.sharedPreferences.getString("diachi",""));
        edtemail.setText(DangNhap.sharedPreferences.getString("email",""));
        edtpass.setText(DangNhap.sharedPreferences.getString("password",""));
        edtsdt.setText(DangNhap.sharedPreferences.getString("sodienthoai",""));
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtussername.getText().toString().equals("") || edtdiachi.getText().toString().equals("") ||
                        edtemail.getText().toString().equals("") || edtpass.getText().toString().equals("") ||
                        edtsdt.getText().toString().equals("") ){
                    Toast.makeText(getActivity(), "Không được để trống dữ liệu", Toast.LENGTH_SHORT).show();
                }else{
                    btnupdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DataService dataService= APIServices.getService();
                            Call<String>call=dataService.Updatetaikhoan(iduser+"",edtussername.getText().toString(),edtpass.getText().toString(),
                                    edtdiachi.getText().toString(),edtemail.getText().toString(),edtsdt.getText().toString());
                            call.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    Log.d("AAA","Update taikhoan: "+response.toString());
                                    if(response.isSuccessful()){
                                        Log.d("AAA",response.body());
                                        Toast.makeText(getActivity(), "Update Thanh cong", Toast.LENGTH_SHORT).show();
                                        DangNhap.editor.putString("username", edtussername.getText().toString());
                                        DangNhap.editor.putString("password", edtpass.getText().toString());
                                        DangNhap.editor.putString("email", edtemail.getText().toString());
                                        DangNhap.editor.putString("sodienthoai",edtsdt.getText().toString());
                                        DangNhap.editor.putString("diachi",edtdiachi.getText().toString());
                                        DangNhap.editor.commit();
                                        Fragment fragment= getFragmentManager().findFragmentByTag("updatethongtintk");
                                        if(fragment!=null){
                                            DialogFragment dialogFragmen= (DialogFragment) fragment;
                                            dialogFragmen.dismiss();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void anhxa() {
        btnupdate=view.findViewById(R.id.btnupdate);
        edtussername=view.findViewById(R.id.edtussername);
        edtpass=view.findViewById(R.id.edtpass);
        edtsdt=view.findViewById(R.id.edtsdt);
        edtemail=view.findViewById(R.id.edtemail);
        edtdiachi=view.findViewById(R.id.edtdiachi);
    }
}
