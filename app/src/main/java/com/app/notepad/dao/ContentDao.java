package com.app.notepad.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.notepad.db.ContentDbHelper;
import com.app.notepad.db.UserDbHelper;
import com.app.notepad.entity.ContentInfo;
import com.app.notepad.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 对数据库做增删改查
 */
public class ContentDao {

    private ContentDbHelper mHelper;

    public ContentDao(Context context) {
        mHelper = ContentDbHelper.getInstance(context);
    }


    /**
     * 添加
     * <p>
     * type   1======记账
     * 2======打卡
     * 3======便签
     */
    public int add(String title, String time, String detail, int type) {

        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("time", time);
        values.put("detail", detail);
        values.put("type", type);
        String nullColumnHack = "values(null,?,?,?,?)";
        //执行
        int insert = (int) db.insert("content_table", nullColumnHack, values);
        db.close();
        return insert;
    }


    /**
     * 查询数据
     */
    public List<ContentInfo> queryAll() {
        List<ContentInfo> infoList = new ArrayList<>();

        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql = "select _id,title,time,detail,type from content_table";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String detail = cursor.getString(cursor.getColumnIndex("detail"));
            int type = cursor.getInt(cursor.getColumnIndex("type"));
            infoList.add(new ContentInfo(_id, title, time, detail, type));
        }
        cursor.close();
        db.close();
        return infoList;
    }


    /**
     * 查询数据
     */
    public List<ContentInfo> queryAll(int selType) {
        List<ContentInfo> infoList = new ArrayList<>();

        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql = "select _id,title,time,detail,type from content_table where type=?";
        String[] selectionArgs = {selType + ""};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String detail = cursor.getString(cursor.getColumnIndex("detail"));
            int type = cursor.getInt(cursor.getColumnIndex("type"));
            infoList.add(new ContentInfo(_id, title, time, detail, type));
        }
        cursor.close();
        db.close();
        return infoList;
    }

    public int delete(int _id) {
        // 获取数据
        SQLiteDatabase db = mHelper.getWritableDatabase();
        // 执行SQL
        int delete = db.delete("content_table", " _id=?", new String[]{_id + ""});
        // 关闭数据库连接
        db.close();
        return delete;
    }
}
