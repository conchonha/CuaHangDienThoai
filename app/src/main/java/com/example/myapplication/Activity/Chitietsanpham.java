package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.Banner_Adapter;
import com.example.myapplication.Fragment.Fragment_Danhgiasanpham;
import com.example.myapplication.Model.Sanpham;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIServices;
import com.example.myapplication.Service.DataService;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chitietsanpham extends AppCompatActivity {
    private Toolbar toolbarchitiet;
    private ViewPager viewpagerchitietsanpham;
    private Sanpham sanpham;
    private String[]arrayhinh;
    private TextView txttensanpham1,txtgiasanpham1,txtngaydang1,txttrinhtrang,txtmota1;
    private Button btnthemvaogiohang;
    private int idsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        anhxa();
        actionbar();
        Intent intent=getIntent();
        sanpham= (Sanpham) intent.getSerializableExtra("sanpham");
        arrayhinh=sanpham.getMoTaSP().split("@");
        viewpagerbanner();
        themvaogiohang();
        addFragment(sanpham.getID()+"");
    }
    private void addFragment(String masp) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment_Danhgiasanpham fragment=new Fragment_Danhgiasanpham();
        Bundle bundle=new Bundle();
        bundle.putString("masp",masp);
        fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.linner12,fragment);
        fragmentTransaction.commit();
    }

    private void themvaogiohang() {
        btnthemvaogiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DangNhap.sharedPreferences.getString("username","").equals("")){
                startActivity(new Intent(getApplicationContext(),DangNhap.class));
            }else{
                    Log.d("AAA","idsp: "+sanpham.getID());
                    Log.d("AAA","iduser: "+DangNhap.sharedPreferences.getInt("iduser",0));
                DataService dataService= APIServices.getService();
                Call<String>calback=dataService.themvaogiohang(sanpham.getID()+"",DangNhap.sharedPreferences.getInt("iduser",0)+"");
                calback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("AAA","Themvaogiohang"+response.toString());
                        if(response.isSuccessful()){
                            Toast.makeText(Chitietsanpham.this, "Them Thanh cong", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),GioHang.class));
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
        btnthemvaogiohang=findViewById(R.id.btnthemvaogiohang);
        txttensanpham1=findViewById(R.id.txttensanpham1);
        txtgiasanpham1=findViewById(R.id.txtgiasanpham1);

        txttrinhtrang=findViewById(R.id.txttrinhtrang);
        txtmota1=findViewById(R.id.txtmota1);
    }

    private void viewpagerbanner() {
        viewpagerchitietsanpham=findViewById(R.id.viewpagerchitietsanpham);
        String array[] = new String[arrayhinh.length];
        array[0]=sanpham.getHinhAnhSP();
        for (int i=1;i<arrayhinh.length;i++){
            array[i]=arrayhinh[i];
        }

        Banner_Adapter adapter=new Banner_Adapter(Chitietsanpham.this,array);
        viewpagerchitietsanpham=findViewById(R.id.viewpagerchitietsanpham);
        viewpagerchitietsanpham.setAdapter(adapter);
        txtmota1.setText(arrayhinh[0]);
        txttensanpham1.setText(sanpham.getTenSP());
        txttrinhtrang.setText("Trình Trạng: Còn Hàng");


        Calendar calendar=Calendar.getInstance();
        DecimalFormat simpleDateFormat=new DecimalFormat("###,###,###");
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        if(sanpham.getGiamGia()>0 && !sanpham.getNgayGiamGia().equals("")){
            Date ngaykhuyenmai= null;
            Date ngayhientai=null;
            try {
                ngaykhuyenmai = format.parse(sanpham.getNgayGiamGia()+"");
                ngayhientai=format.parse(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(ngaykhuyenmai.compareTo(ngayhientai)>0){
                float giagiam=(float) (100-sanpham.getGiamGia())/100;
                float giaspsaukhuyenmai=(float)giagiam*sanpham.getGiaSP();
                txtgiasanpham1.setText("Giá"+simpleDateFormat.format((int)giaspsaukhuyenmai)+"Đ");
            }
        }else{

            txtgiasanpham1.setText("Giá"+simpleDateFormat.format(sanpham.getGiaSP())+"Đ");

        }

    }

    private void actionbar() {
        toolbarchitiet=findViewById(R.id.toolbarchitiet);
        setSupportActionBar(toolbarchitiet);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarchitiet.setNavigationIcon(R.drawable.back);
        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
