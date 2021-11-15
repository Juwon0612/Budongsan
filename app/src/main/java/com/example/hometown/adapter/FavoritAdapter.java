package com.example.hometown.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hometown.DatabaseAccess;
import com.example.hometown.MyDBHelper;
import com.example.hometown.R;
import com.example.hometown.UserDTO;
import com.example.hometown.item.ApartItem;
import com.example.hometown.item.FavoritItem;

import java.util.ArrayList;

public class FavoritAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<FavoritItem> favoritList;
    MyDBHelper myHelper;
    SQLiteDatabase sqlDB;

    public FavoritAdapter(Context context, int layout, ArrayList<FavoritItem> favoritList) {
        this.context = context;
        this.layout = layout;
        this.favoritList = favoritList;
        myHelper=new MyDBHelper(context);
    }

    @Override
    public int getCount() {
        return favoritList.size();
    }

    @Override
    public Object getItem(int i) {
        return favoritList.get(i);
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
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        FavoritItem favoritItem = favoritList.get(i);

        holder.apart_addr.setText("위치 : "+favoritItem.getAddr()+" "+favoritItem.getAddr2());
        holder.apart_weight2.setText(favoritItem.getWeight()+"㎡");
        holder.apart_floor.setText(favoritItem.getFloor()+"층");
        holder.apart_type.setText(favoritItem.getType());
        holder.apart_price.setText("실거래가 : "+favoritItem.getPrice()+"원");
        if (favoritItem.getYear2().equals("0")){
            holder.apart_year2.setText("");
        } else {
            holder.apart_year2.setText(favoritItem.getYear2()+"년");
        }
        holder.apart_name.setText(favoritItem.getName());

        return row;
    }




}
