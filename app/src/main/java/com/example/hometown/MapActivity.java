package com.example.hometown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MapActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.land.naver.com/map/37.5507645:127.0907864:14:1121500000/APT/A1:B1:B2"));
        startActivity(intent); //uri를 가지고 브라우저로 이동
        finish();
    }


}