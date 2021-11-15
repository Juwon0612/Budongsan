package com.example.hometown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button apart_btn , villa_btn , opis_btn, map_btn ,mypage_btn ,favorit_btn,setting_btn;
    private DrawerLayout drawerLayout;
    private View drawerView;
    Spinner search_spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String addr ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>(); //리스트에 서울 지역을담음
        arrayList.add("종로구");
        arrayList.add("중구");
        arrayList.add("용산구");
        arrayList.add("성동구");
        arrayList.add("광진구");
        arrayList.add("동대문구");
        arrayList.add("중랑구");
        arrayList.add("성북구");
        arrayList.add("강북구");
        arrayList.add("도봉구");
        arrayList.add("노원구");
        arrayList.add("은평구");
        arrayList.add("서대문구");
        arrayList.add("마포구");
        arrayList.add("양천구");
        arrayList.add("강서구");
        arrayList.add("구로구");
        arrayList.add("금천구");
        arrayList.add("영등포구");
        arrayList.add("동작구");
        arrayList.add("관악구");
        arrayList.add("서초구");
        arrayList.add("강남구");
        arrayList.add("송파구");
        arrayList.add("강동구");


        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList); //어댑터에 리스트를 담음

        search_spinner = findViewById(R.id.search_spinner);
        search_spinner.setAdapter(arrayAdapter); //스피너에 어댑트를 담아서 표시
        search_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                addr = arrayList.get(i); //스피너에서 선택하면 String으로 주소를 받음
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        Button btn_open = (Button)findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);  //메뉴버튼을 누르면 드로어가 열림
            }
        });


        mypage_btn = findViewById(R.id.mypage_btn);
        mypage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MypageActivity.class);
                startActivity(intent); //드로어의 마이페이지 버튼을 누르면 마이페이지 이동
            }
        });

        favorit_btn = findViewById(R.id.favorit_btn);
        favorit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FavoritActivity.class);
                startActivity(intent); //드로어의 찜목록 버튼을 누르면 찜목록 이동
    }
});

        setting_btn = findViewById(R.id.setting_btn);
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent); //드로어의 설정 버튼을 누르면 설정 이동
            }
        });
        villa_btn = findViewById(R.id.villa_btn);
        villa_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,VillaActivity.class);
                intent.putExtra("addr",addr);
                Log.d("test",addr);
                startActivity(intent); //메인의 빌라 버튼을 누르면 스퍼너에서 받은 주소값을 가지고 빌라페이지 이동
            }
        });
        opis_btn = findViewById(R.id.opis_btn);
        opis_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OpisActivity.class);
                intent.putExtra("addr",addr);
                Log.d("test",addr);
                startActivity(intent); //메인의 오피스텔 버튼을 누르면 스퍼너에서 받은 주소값을 가지고 오피스텔페이지 이동
            }
        });
        map_btn = findViewById(R.id.map_btn);
        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent); //메인의 지도 버튼을 누르면 지도페이지 이동
            }
        });
        apart_btn = findViewById(R.id.apart_btn);
        apart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ApartActivity.class);
                intent.putExtra("addr",addr);
                Log.d("test",addr);
                startActivity(intent); //메인의 아파트 버튼을 스퍼너에서 받은 주소값을 가지고 아파트페이지 이동
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() { //드로어뷰 터치로 열고 닫음
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };
    @Override
    public void onBackPressed() { //뒤로가기 버튼에서 드로어가 열려있으면 닫고 뒤로가기
        drawerLayout.closeDrawer(drawerView);
        super.onBackPressed();
    }
}