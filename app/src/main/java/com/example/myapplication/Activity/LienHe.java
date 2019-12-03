package com.example.myapplication.Activity;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

public class LienHe extends AppCompatActivity {
    private Toolbar toolbarlienhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        toolbarlienhe=findViewById(R.id.toolbarlienhe);
        setSupportActionBar(toolbarlienhe);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarlienhe.setNavigationIcon(R.drawable.back);
        toolbarlienhe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
