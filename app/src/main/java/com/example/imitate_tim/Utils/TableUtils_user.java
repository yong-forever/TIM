package com.example.imitate_tim.Utils;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

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

    private void insert(String username,String password,String phone,String time){
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("phone",phone);
        contentValues.put("time",time);
        db.insert("user",null,contentValues);
    }
}
