package com.app.notepad.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


/**
 * 创建数据     SQLite
 */
public class ContentDbHelper extends SQLiteOpenHelper {
    public static ContentDbHelper sContentDbHelper;
    private static final String DB_NAME = "content_info.db";
    private static final int VERSION = 1;

    public ContentDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public synchronized static ContentDbHelper getInstance(Context context) {
        if (null == sContentDbHelper) {
            sContentDbHelper = new ContentDbHelper(context, DB_NAME, null, VERSION);
        }
        return sContentDbHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table content_table(_id integer primary key autoincrement, " +
                "title text," +       //  标题
                "time text," +       //时间
                "detail text," +       //内容、详情
                "type integer" +       //分类
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
