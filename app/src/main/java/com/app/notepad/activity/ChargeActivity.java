package com.app.notepad.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.app.notepad.R;
import com.app.notepad.base.BaseActivity;
import com.app.notepad.dao.ContentDao;

/**
 * 记账
 */
public class ChargeActivity extends BaseActivity {
    private EditText title;
    private EditText time;
    private EditText detail;
    private ContentDao mContentDao;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_charge;
    }

    @Override
    protected void initView() {
        title = findViewById(R.id.title);
        time = findViewById(R.id.time);
        detail = findViewById(R.id.detail);


    }

    @Override
    protected void setListener() {
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleStr = title.getText().toString().trim();
                String timeStr = time.getText().toString().trim();
                String detailStr = detail.getText().toString().trim();
                if (TextUtils.isEmpty(titleStr)) {
                    showToast("请输入标题");
                } else if (TextUtils.isEmpty(timeStr)) {
                    showToast("请输入消费金额");
                } else if (TextUtils.isEmpty(detailStr)) {
                    showToast("请输入详情");
                } else {
                    if (mContentDao == null) {
                        mContentDao = new ContentDao(ChargeActivity.this);
                    }

                    int row = mContentDao.add(titleStr, timeStr, detailStr, 1);
                    if (row > 0) {
                        showToast("保存成功");
                        setResult(200);
                        finish();
                    } else {
                        showToast("保存失败");
                    }
                }
            }
        });

    }

    @Override
    protected void initData() {

    }
}