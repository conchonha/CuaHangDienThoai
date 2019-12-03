package com.example.myapplication.Activity;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

public class fanbage extends AppCompatActivity {
    private Toolbar toolbarfabage;
    private WebView webview;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fanbage);

        toolbarfabage=findViewById(R.id.toolbarfabage);
        setSupportActionBar(toolbarfabage);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarfabage.setNavigationIcon(R.drawable.back);
        toolbarfabage.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webview=findViewById(R.id.webview);
        webview.loadUrl("https://www.facebook.com/profile.php?id=100007219005457");
       webview.setWebViewClient(new WebViewClient());
    }
}
