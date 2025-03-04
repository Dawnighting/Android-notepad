package com.app.notepad.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.app.notepad.R;
import com.app.notepad.base.BaseActivity;
import com.app.notepad.dao.UserDao;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseActivity {
    private EditText username;
    private EditText password;
    private UserDao mUserDao;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

    }

    @Override
    protected void setListener() {


        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    showToast("请输入用户名");
                } else if (TextUtils.isEmpty(pwd)) {
                    showToast("请输入密码");
                } else {
                    if (mUserDao == null) {
                        mUserDao = new UserDao(RegisterActivity.this);
                    }
                    int row = mUserDao.register(name, pwd);
                    if (row > 0) {
                        showToast("注册成功");
                        finish();
                    } else {
                        showToast("注册失败");
                    }
                }
            }
        });

    }

    @Override
    protected void initData() {

    }
}