package com.example.imitate_tim.Utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 2020/2/6.
 * 类描述:
 */
public class TableUtils_user {
    private SQL sql;
    private SQLiteDatabase db;

    public TableUtils_user(SQL sql) {
        this.sql = sql;
        db=sql.getWritableDatabase();
    }

    public void insert(String username,String password,String phone,String time){
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("phone",phone);
        contentValues.put("time",time);
        db.insert("user",null,contentValues);
    }

    public void query(){
        Cursor c=db.rawQuery("SELECT * FROM user",null);
    }

    public List<UserInfo> getUserinfo_rawQuery(String sql){
        List<UserInfo> userInfoList=new ArrayList<>();
        Cursor c=db.rawQuery(sql,null);
        if(c.getCount()>0){
            while(c.moveToNext()){
                String username=c.getString(c.getColumnIndex("username"));
                String password=c.getString(c.getColumnIndex("password"));
                String phone=c.getString(c.getColumnIndex("phone"));
                String time=c.getString(c.getColumnIndex("time"));
                userInfoList.add(new UserInfo(username,password,phone,time));
            }
        }
        return userInfoList;
    }
}
