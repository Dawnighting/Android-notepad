package com.app.notepad.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.notepad.R;
import com.app.notepad.base.BaseActivity;
import com.app.notepad.dao.ContentDao;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LocationActivity extends BaseActivity {
    private EditText title;
    private TextView time;
    private EditText detail;
    private ContentDao mContentDao;

    private TimePickerView pvCustomTime;   //年月日


    @Override
    protected int getLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    protected void initView() {
        title = findViewById(R.id.title);
        time = findViewById(R.id.time);
        detail = findViewById(R.id.detail);

    }

    @Override
    protected void setListener() {
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvCustomTime.show();
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleStr = title.getText().toString().trim();
                String timeStr = time.getText().toString().trim();
                String detailStr = detail.getText().toString().trim();
                if (TextUtils.isEmpty(titleStr)) {
                    showToast("请输入标题");
                } else if (TextUtils.isEmpty(timeStr)) {
                    showToast("请选择日期");
                } else if (TextUtils.isEmpty(detailStr)) {
                    showToast("请输入详情");
                } else {
                    if (mContentDao == null) {
                        mContentDao = new ContentDao(LocationActivity.this);
                    }

                    int row = mContentDao.add(titleStr, timeStr, detailStr, 2);
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
        initCustomTimePicker();
    }

    public void initCustomTimePicker() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2020, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2060, 2, 28);

        pvCustomTime = new TimePickerBuilder(LocationActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                time.setText(getTime(date));
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

}