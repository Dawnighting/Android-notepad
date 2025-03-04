package com.app.notepad.entity;


public class ContentInfo {
    private int _id;
    private String title;
    private String time;
    private String detail;
    private int type;    //数据类型


    public ContentInfo(int _id, String title, String time, String detail, int type) {
        this._id = _id;
        this.title = title;
        this.time = time;
        this.detail = detail;
        this.type = type;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
