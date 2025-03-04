package com.app.notepad.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


/**
 *  创建数据     SQLite
 */
public class UserDbHelper extends SQLiteOpenHelper {
    public static UserDbHelper sUserDbHelper;
    private static final String DB_NAME = "user_info.db";
    private static final int VERSION = 1;

    public UserDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public synchronized static UserDbHelper getInstance(Context context) {
        if (null == sUserDbHelper) {
            sUserDbHelper = new UserDbHelper(context, DB_NAME, null, VERSION);
        }
        return sUserDbHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user_table(_id integer primary key autoincrement, " +
                "username text," +       //  名字
                "password text" +       //密码
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
