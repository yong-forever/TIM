package com.example.imitate_tim.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Create by 2020/2/6.
 * 类描述:
 */
public class SQL extends SQLiteOpenHelper {
    public SQL(@Nullable Context context, @Nullable String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(_id integer PRIMARY KEY AUTOINCREMENT,username varchar(20),password varchar(20),phone varchar(20),time varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
