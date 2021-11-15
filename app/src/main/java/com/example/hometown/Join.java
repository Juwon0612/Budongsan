package com.example.hometown;



import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Join extends AppCompatActivity {
    EditText jId,jPw;
    Button btnRegistration, btnMain;
    MyDBHelper myHelper; //인스턴스 선언
    SQLiteDatabase sqlDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        jId=(EditText)findViewById(R.id.jId);
        jPw=(EditText)findViewById(R.id.jPw);
        btnRegistration=(Button)findViewById(R.id.jRegistration);
        btnMain=(Button)findViewById(R.id.jMainBtn);
        myHelper=new MyDBHelper(this);
//등록버튼을 누르면 1건의 레코드 정보가 저장d



        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                sqlDB=myHelper.getWritableDatabase();//쓰기 전용으로 DB열기
                sqlDB.execSQL("INSERT INTO JoinInfo VALUES('"+jId.getText().toString()+"','"+jPw.getText().toString()+"');");
                sqlDB.close();
//1건의 레코드가 추가됨을 표시
                Toast.makeText(getApplicationContext(),"가입됨",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}