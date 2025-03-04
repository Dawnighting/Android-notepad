package com.app.notepad.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.notepad.db.UserDbHelper;
import com.app.notepad.entity.UserInfo;

/**
 * 对数据库做增删改查
 */
public class UserDao {

    private UserDbHelper mHelper;

    public UserDao(Context context) {
        mHelper = UserDbHelper.getInstance(context);
    }


    /**
     * 注册，也就是对应添加
     */


    public int register(String username, String password) {

        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        String nullColumnHack = "values(null,?,?)";
        //执行
        int insert = (int) db.insert("user_table", nullColumnHack, values);
        db.close();
        return insert;
    }

    public UserInfo queyOne(String name) {
        UserInfo userInfo = null;
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql = "select _id,username,password from user_table where  username=?";
        String[] selectionArgs = {name};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String user_name = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            userInfo = new UserInfo(_id, user_name, password);
        }
        cursor.close();
        db.close();
        return userInfo;
    }
}
