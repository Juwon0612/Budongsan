package com.example.hometown;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context){
        super(context,"LoginDB",null,1);
 //LoginDB 생성
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //회원 테이블, 찜목록 테이블 생성
        sqLiteDatabase.execSQL("CREATE TABLE JoinInfo(uId TEXT PRIMARY KEY,uPassword TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE FavoritInfo(uId TEXT , pId TEXT ,addr TEXT , addr2 TEXT, year TEXT, weight TEXT, floor TEXT ,type TEXT ,price TEXT , year2 TEXT , name TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS JoinInfo");
        onCreate(sqLiteDatabase);

    }
}