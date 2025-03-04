package com.app.notepad.entity;

public class UserInfo {
    private int _id;
    private String username;
    private String password;

    public UserInfo(int _id, String username, String password) {
        this._id = _id;
        this.username = username;
        this.password = password;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
