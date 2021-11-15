package com.example.hometown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hometown.adapter.ApartAdapter;
import com.example.hometown.adapter.FavoritAdapter;
import com.example.hometown.item.ApartItem;
import com.example.hometown.item.FavoritItem;

import java.util.ArrayList;

public class FavoritActivity extends AppCompatActivity {

    MyDBHelper myHelper; //인스턴스 선언
    SQLiteDatabase sqlDB;
    ListView listview ;
    ArrayList<FavoritItem> list;
    FavoritAdapter adapter;
    TextView zero_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favorit);


        zero_text = findViewById(R.id.zero_text);

        listview = (ListView) findViewById(R.id.favorit_list);
        list = new ArrayList<>();
        adapter = new FavoritAdapter(this,R.layout.list_item2,list);
        listview.setAdapter(adapter);

        myHelper=new MyDBHelper(this);
        sqlDB=myHelper.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM FavoritInfo WHERE uId = '"+ UserDTO.id +"';",null);
        favorit(cursor);

        if (list.size() == 0 ){ //리스트의 사이즈가 0이면 (찜목록 데이터가 없음면) 없다고 텍스트 표시
            zero_text.setVisibility(View.VISIBLE);
        } else {
            zero_text.setVisibility(View.GONE);
        }

        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(FavoritActivity.this,DetailActivity.class);
                intent.putExtra("id",list.get(position).getPid());
                intent.putExtra("addr",list.get(position).getAddr() +" "+ list.get(position).getAddr2());
                intent.putExtra("year",list.get(position).getYear());
                intent.putExtra("weight",list.get(position).getWeight());
                intent.putExtra("floor",list.get(position).getFloor());
                intent.putExtra("type",list.get(position).getType());
                intent.putExtra("price",list.get(position).getPrice());
                intent.putExtra("year2",list.get(position).getYear2());
                intent.putExtra("name",list.get(position).getName());
                startActivity(intent);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                sqlDB=myHelper.getWritableDatabase();//쓰기 전용으로 DB열기
                sqlDB.execSQL("DELETE FROM FavoritInfo WHERE uId = '"+ UserDTO.id +"' AND pId = '"+ list.get(position).getPid() +"'");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),list.get(position).getName()+"를 찜목록에서 삭제했습니다",Toast.LENGTH_SHORT).show();
                list.remove(position);
                adapter.notifyDataSetChanged();

                return true;
            }
        });
    }

    public void favorit(Cursor cursor){
        list.clear();
        while (cursor.moveToNext()){
            String uid = cursor.getString(0);
            String pid = cursor.getString(1);
            String addr = cursor.getString(2);
            String addr2 = cursor.getString(3);
            String year = cursor.getString(4);
            String weight = cursor.getString(5);
            String floor = cursor.getString(6);
            String type = cursor.getString(7);
            String price = cursor.getString(8);
            String year2 = cursor.getString(9);
            String name = cursor.getString(10);


            list.add(new FavoritItem(uid,pid,addr,addr2,year,weight,floor,type,price,year2,name));
        }
        adapter.notifyDataSetChanged();
        listview.setSelection(0);

    }
}