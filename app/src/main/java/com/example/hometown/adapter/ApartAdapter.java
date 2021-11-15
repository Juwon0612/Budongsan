package com.example.hometown.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hometown.MyDBHelper;
import com.example.hometown.R;
import com.example.hometown.UserDTO;
import com.example.hometown.item.ApartItem;

import java.util.ArrayList;

public class ApartAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<ApartItem> apartList;
    MyDBHelper myHelper; //인스턴스 선언
    SQLiteDatabase sqlDB;
    String pid;
    public ApartAdapter(Context context, int layout, ArrayList<ApartItem> apartList) {
        this.context = context;
        this.layout = layout;
        this.apartList = apartList;
        myHelper=new MyDBHelper(context);
    }

    @Override
    public int getCount() {
        return apartList.size();
    }

    @Override
    public Object getItem(int i) {
        return apartList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView apart_id , apart_addr, apart_addr2,apart_year,apart_weight,apart_weight2,apart_floor,apart_type,apart_price,apart_year2,apart_name;
        CheckBox favorit_check;

    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row ==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);


            holder.apart_addr = row.findViewById(R.id.apart_addr);
            holder.apart_weight2 = row.findViewById(R.id.apart_weight2);
            holder.apart_floor = row.findViewById(R.id.apart_floor);
            holder.apart_type = row.findViewById(R.id.apart_type);
            holder.apart_price = row.findViewById(R.id.apart_price);
            holder.apart_year2 = row.findViewById(R.id.apart_year2);
            holder.apart_name = row.findViewById(R.id.apart_name);
            holder.favorit_check = row.findViewById(R.id.favorit_check);
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        ApartItem apartItem = apartList.get(i);

        holder.apart_addr.setText("위치 : "+apartItem.getAddr()+" "+apartItem.getAddr2());
        holder.apart_weight2.setText(apartItem.getWeight2()+"㎡");
        holder.apart_floor.setText(apartItem.getFloor()+"층");
        holder.apart_type.setText(apartItem.getType());
        holder.apart_price.setText("실거래가 : "+apartItem.getPrice()+"원");
        if (apartItem.getYear2().equals("0")){
            holder.apart_year2.setText("");
        } else {
            holder.apart_year2.setText(apartItem.getYear2()+"년");
        }
        holder.apart_name.setText(apartItem.getName());

        sqlDB=myHelper.getReadableDatabase();
        Cursor cursor=sqlDB.rawQuery("SELECT pId FROM FavoritInfo WHERE uId = '"+ UserDTO.id +"' AND pId = '"+ apartItem.getId() +"';",null);
        pid(cursor);

        if (apartItem.getId().equals(pid)){
            holder.favorit_check.setChecked(true);
        } else {
            holder.favorit_check.setChecked(false);
        }

        holder.favorit_check.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApartItem apartItem = apartList.get(i);
                if (((CheckBox)v).isChecked()) {
                    sqlDB=myHelper.getWritableDatabase();//쓰기 전용으로 DB열기
                    sqlDB.execSQL("INSERT INTO FavoritInfo VALUES('"+ UserDTO.id +"','"+ apartItem.getId() +"','"+ apartItem.getAddr() +"','"+ apartItem.getAddr2() +"','"+ apartItem.getYear() +"','"+ apartItem.getWeight2() +"','"+ apartItem.getFloor() +"','"+ apartItem.getType() +"','"+ apartItem.getPrice() +"','"+ apartItem.getYear2() +"','"+ apartItem.getName() +"');");

                    sqlDB.close();
                    Toast.makeText(context,apartItem.getName()+" 찜목록에 추가했습니다",Toast.LENGTH_SHORT).show();
                } else {
                    sqlDB=myHelper.getWritableDatabase();//쓰기 전용으로 DB열기
                    sqlDB.execSQL("DELETE FROM FavoritInfo WHERE uId = '"+ UserDTO.id +"' AND pId = '"+ apartItem.getId() +"'");

                    sqlDB.close();
                    Toast.makeText(context,apartItem.getName()+" 찜목록에서 삭제했습니다",Toast.LENGTH_SHORT).show();
                }
            }
        }) ;
        return row;
    }

    public void pid(Cursor cursor){
        try{
            while (cursor.moveToNext()){
                pid = cursor.getString(0);
            }
        } catch (Exception e){
            Log.d("test1",e+"");
        }


    }


}
