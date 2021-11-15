package com.example.hometown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    TextView id,addr,year,weight,floor,type,price,year2,name;
    String id_text, addr_text , year_text , weight_text ,floor_text, type_text, price_text , year2_text, name_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent = getIntent();
        id_text = intent.getStringExtra("id");
        addr_text = intent.getStringExtra("addr");
        year_text = intent.getStringExtra("year");
        weight_text = intent.getStringExtra("weight");
        floor_text = intent.getStringExtra("floor");
        type_text = intent.getStringExtra("type");
        price_text = intent.getStringExtra("price");
        year2_text = intent.getStringExtra("year2");
        name_text = intent.getStringExtra("name");

        id = findViewById(R.id.apart_id);
        id.setText("거래아이디 : "+id_text);

        addr = findViewById(R.id.apart_addr);
        addr.setText("위치 : " +addr_text);

        year = findViewById(R.id.apart_year);
        year.setText("신고년도 : "+year_text+"년");

        weight = findViewById(R.id.apart_weight);
        weight.setText("면적 : "+weight_text+"㎡");

        floor = findViewById(R.id.apart_floor);
        floor.setText("층 : "+floor_text+"층");

        type = findViewById(R.id.apart_type);
        type.setText("타입 : "+type_text);

        price = findViewById(R.id.apart_price);
        price.setText("실거래가 : "+price_text+"원");

        year2 = findViewById(R.id.apart_year2);
        year2.setText("건축년도 : "+year2_text+"년");

        name = findViewById(R.id.apart_name);
        name.setText(name_text);

    }
}