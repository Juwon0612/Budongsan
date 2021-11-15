package com.example.hometown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hometown.adapter.ApartAdapter;
import com.example.hometown.item.ApartItem;

import java.util.ArrayList;

public class OpisActivity extends AppCompatActivity {

    ListView listview ;
    ArrayList<ApartItem> list;
    ApartAdapter adapter;
    EditText search_text , search_text2;
    Button search_btn , reset_btn;
    String type = "오피스텔";
    String addr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opis);

        Intent intent = getIntent();
        addr = intent.getStringExtra("addr");
        listview = (ListView) findViewById(R.id.opis_list);
        list = new ArrayList<>();
        adapter = new ApartAdapter(this,R.layout.list_item,list);
        listview.setAdapter(adapter);


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        Cursor cursor = databaseAccess.getData("SELECT * FROM seoul3 WHERE type = '"+type+"' AND addr = '"+addr+"'");
        apart(cursor);
        databaseAccess.close();

        search_text = findViewById(R.id.search_text);
        search_text2 = findViewById(R.id.search_text2);
        search_btn = findViewById(R.id.search_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = search_text.getText().toString();
                String search2 = search_text2.getText().toString();
                if (search.equals("") && search2.equals("")){
                    Toast.makeText(getApplicationContext(),"동 또는 오피스텔 이름을 입력해주세요",Toast.LENGTH_SHORT).show();
                }
                if (!search.equals("") && search2.equals("")){
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();
                    Cursor cursor = databaseAccess.getData("SELECT * FROM seoul3 WHERE (type = '"+type+"' AND addr = '"+addr+"') AND addr2 LIKE '%" +search+"%'");
                    apart(cursor);
                    databaseAccess.close();
                    if (list.size() == 0){
                        Toast.makeText(getApplicationContext(),"매물이 없습니다",Toast.LENGTH_SHORT).show();
                    }
                } else if (search.equals("") && !search2.equals("")){
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();
                    Cursor cursor = databaseAccess.getData("SELECT * FROM seoul3 WHERE (type = '"+type+"' AND addr = '"+addr+"') AND name LIKE '%" +search2+"%'");
                    apart(cursor);
                    databaseAccess.close();
                    if (list.size() == 0){
                        Toast.makeText(getApplicationContext(),"매물이 없습니다",Toast.LENGTH_SHORT).show();
                    }
                } else if (!search.equals("") && !search2.equals("")){
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();
                    Cursor cursor = databaseAccess.getData("SELECT * FROM seoul3 WHERE (type = '"+type+"' AND addr = '"+addr+"') AND name LIKE '%" +search2+"%' AND addr2 LIKE '%" +search+"%'");
                    apart(cursor);
                    databaseAccess.close();
                    if (list.size() == 0){
                        Toast.makeText(getApplicationContext(),"매물이 없습니다",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        reset_btn = findViewById(R.id.reset_btn);
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_text.setText("");
                search_text2.setText("");
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();
                Cursor cursor = databaseAccess.getData("SELECT * FROM seoul3 WHERE type = '"+type+"' AND addr = '"+addr+"'");
                apart(cursor);
                databaseAccess.close();
            }
        });

        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(OpisActivity.this,DetailActivity.class);
                intent.putExtra("id",list.get(position).getId());
                intent.putExtra("addr",list.get(position).getAddr() +" "+ list.get(position).getAddr2());
                intent.putExtra("year",list.get(position).getYear());
                intent.putExtra("weight",list.get(position).getWeight2());
                intent.putExtra("floor",list.get(position).getFloor());
                intent.putExtra("type",list.get(position).getType());
                intent.putExtra("price",list.get(position).getPrice());
                intent.putExtra("year2",list.get(position).getYear2());
                intent.putExtra("name",list.get(position).getName());
                startActivity(intent);
            }
        });
    }

    public void apart(Cursor cursor){
        list.clear();
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String addr = cursor.getString(1);
            String addr2 = cursor.getString(2);
            String year = cursor.getString(3);
            String weight = cursor.getString(4);
            String weight2 = cursor.getString(5);
            String floor = cursor.getString(6);
            String type = cursor.getString(7);
            String price = cursor.getString(8);
            String year2 = cursor.getString(9);
            String name = cursor.getString(10);


            list.add(new ApartItem(id,addr,addr2,year,weight,weight2,floor,type,price,year2,name));
        }
        adapter.notifyDataSetChanged();
        listview.setSelection(0);

    }
}