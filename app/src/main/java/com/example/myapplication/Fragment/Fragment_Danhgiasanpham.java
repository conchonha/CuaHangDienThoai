package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Activity.Chitietsanpham;
import com.example.myapplication.Activity.DangNhap;
import com.example.myapplication.Adapter.Comment_Adapter;
import com.example.myapplication.Model.Danhgia;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIServices;
import com.example.myapplication.Service.DataService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Danhgiasanpham extends Fragment {
    private View view;
    private Chitietsanpham chitietsanpham;
    private ArrayList<Danhgia>arrayDanhgia=new ArrayList<>();
    private RatingBar ratingdanhgiacuabanla;
    private EditText edtcommentt;
    private Button btnguicomment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view=inflater.inflate(R.layout.fragment_danhgiasanpham,container,false);
          danhgiasanpham();
          Bundle bundle=getArguments();
          if(bundle!=null) {
              final String msp = bundle.getString("masp");
              Log.d("AAA", "Mã san pham fragment danhgia"+msp);
              if(!msp.equals("0")){
                  getdatadanhgia(msp);
                  btnguicomment.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          danhgiacuaban(msp);
                      }
                  });
              }
          }

     return view;
    }
    private void danhgiacuaban(final String msp){
                if(DangNhap.sharedPreferences.getString("username","").equals("") &&
                        DangNhap.sharedPreferences.getInt("iduser",0)==0){
                    Intent intent=new Intent(getActivity(),DangNhap.class);
                    intent.putExtra("phandanhgia",msp);
                    startActivity(intent);
                    getActivity().finish();
                }else{
                    String commentt="ok";
                     commentt=edtcommentt.getText().toString();
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                    Calendar calendar=Calendar.getInstance();
                    String ngay=simpleDateFormat.format(calendar.getTime());
                   // Toast.makeText(getActivity(), MainActivity.sharedPreferences.getString("username",comment), Toast.LENGTH_SHORT).show();
                    int namsao=0;int bonsao=0;int basao=0;int haisao=0;int motsao=0;
                    if(ratingdanhgiacuabanla.getRating()>0){
                        if(ratingdanhgiacuabanla.getRating()==5){
                            namsao=1;
                        }
                        if(ratingdanhgiacuabanla.getRating()==4){
                            bonsao=1;
                        }
                        if(ratingdanhgiacuabanla.getRating()==3){
                            basao=1;
                        }
                        if(ratingdanhgiacuabanla.getRating()==2){
                            haisao=1;
                        }
                        if(ratingdanhgiacuabanla.getRating()==1){
                            motsao=1;
                        }
                        Log.d("AAA","da co tai khoan: "+DangNhap.sharedPreferences.getString("username",""));
                        Log.d("AAA","ngay"+ngay);
                        Log.d("AAA","5sao"+namsao);
                        Log.d("AAA","4sao"+bonsao);
                        Log.d("AAA","3sao"+basao);
                        Log.d("AAA","2sao"+haisao);
                        Log.d("AAA","1sao"+motsao);
                        Log.d("AAA","msp"+msp);
                        Log.d("AAA","comment"+commentt);
                        String username=DangNhap.sharedPreferences.getString("username","");
                        Log.d("AAA","username"+username);
                        postbai(username,msp,ngay,namsao,bonsao,basao,haisao,motsao,commentt);


                    }
                }

    }

    private void postbai(String username, String msp, String ngay, int namsao, int bonsao, int basao, int haisao, int motsao, String comment) {
        DataService dataService= APIServices.getService();
        Call<String>callback=dataService.postDanhgia(DangNhap.sharedPreferences.getString("username",""),
                msp,ngay,namsao,bonsao,basao,haisao,motsao,comment);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("AAA","Danhgiacuaban"+response.toString());
                if(response.isSuccessful()){
                    String mess=response.body();
                    Log.d("AAA","bien mess"+mess);
                    if(mess.equals("Faid")){
                        Toast.makeText(getActivity(), "Không Thành công - bạn đã đánh giá rùi", Toast.LENGTH_SHORT).show();
                    }
                    if(mess.equals("sussces")){
                        Toast.makeText(getActivity(), "Đánh Giá Của Bạn Đã Được Gửi Đi", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                        startActivity(getActivity().getIntent());

                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void danhgiasanpham(){
        btnguicomment=view.findViewById(R.id.btnguicomment);
        ratingdanhgiacuabanla=view.findViewById(R.id.ratingdanhgiacuabanla);
        edtcommentt=view.findViewById(R.id.edtcommentt);

    }

    private void getdatadanhgia(final String msp) {
        if(!msp.equals("")){
            DataService dataService= APIServices.getService();
            Call<List<Danhgia>>callback=dataService.getdataDanhgia(msp+"");
            callback.enqueue(new Callback<List<Danhgia>>() {
                @Override
                public void onResponse(Call<List<Danhgia>> call, Response<List<Danhgia>> response) {
                    Log.d("AAA","getdataDanhgia"+response.toString());
                    if(response!=null){
                        int namsao=0;int bonsao=0;int basao=0;int haisao=0;int motsao=0;
                        arrayDanhgia= (ArrayList<Danhgia>) response.body();
                       if(arrayDanhgia!=null){
                           for (int i=0; i < arrayDanhgia.size(); i++){
                               namsao+=arrayDanhgia.get(i).get5sao();
                               bonsao+=arrayDanhgia.get(i).get4sao();
                               basao+=arrayDanhgia.get(i).get3sao();
                               haisao+=arrayDanhgia.get(i).get2sao();
                               motsao+=arrayDanhgia.get(i).get1sao();
                           }
                           ListView listView=view.findViewById(R.id.listviewcomment);
                           Comment_Adapter comment_adapter=new Comment_Adapter(getActivity(),R.layout.layout_dongcomment,arrayDanhgia);
                           comment_adapter.notifyDataSetChanged();
                           listView.setAdapter(comment_adapter);
                           setListViewHeightBasedOnChildren(listView);

                       }
                    }
                }

                @Override
                public void onFailure(Call<List<Danhgia>> call, Throwable t) {
                    Log.d("AAA","Ero getdata danhgia san pham"+t.toString());
                }
            });
        }
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
