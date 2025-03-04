package com.app.notepad.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.app.notepad.MainActivity;
import com.app.notepad.R;
import com.app.notepad.UserMainActivity;
import com.app.notepad.base.BaseActivity;
import com.app.notepad.dao.UserDao;
import com.app.notepad.entity.UserInfo;

/**
 * 登录页面实现
 */
public class LoginActivity extends BaseActivity {
    private EditText username;
    private EditText password;
    private UserDao mUserDao;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
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
                        mUserDao = new UserDao(LoginActivity.this);
                    }
                    UserInfo userInfo = mUserDao.queyOne(name);
                    if (null != userInfo) {
                        if (userInfo.getUsername().equals(name) && userInfo.getPassword().equals(pwd)) {
                            showToast("登录成功");
//                            startActivity(new Intent(LoginActivity.this, UserMainActivity.class));
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            showToast("用户名或密码错误");
                        }
                    } else {
                        showToast("该账号暂未注册，请先注册");
                    }


                }
            }
        });

    }

    @Override
    protected void initData() {

    }
}