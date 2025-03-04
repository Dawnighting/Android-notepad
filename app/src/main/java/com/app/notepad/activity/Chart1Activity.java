package com.app.notepad.activity;

import android.util.Log;
import android.widget.TextView;

import com.app.notepad.R;
import com.app.notepad.base.BaseActivity;
import com.app.notepad.dao.ContentDao;
import com.app.notepad.entity.ContentInfo;

import java.util.List;

public class Chart1Activity extends BaseActivity {

    private ContentDao mContentDao;
    private TextView chart1;
    private TextView chart2;
    private TextView chart3;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_chart1;
    }

    @Override
    protected void initView() {

        chart1 = findViewById(R.id.chart1);
        chart2 = findViewById(R.id.chart2);
        chart3 = findViewById(R.id.chart3);


    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        if (mContentDao == null) {
            mContentDao = new ContentDao(Chart1Activity.this);
        }
        List<ContentInfo> contentInfos = mContentDao.queryAll(1);


        int num = 0;
        for (int i = 0; i < contentInfos.size(); i++) {
            String time = contentInfos.get(i).getTime();
            int anInt = Integer.parseInt(time);
            num = num + anInt;
            Log.d("============", "initData: " + anInt);
        }

        Log.d("============", "initData: " + Double.valueOf(num));


//        chart1.setText("(￥:"+Double.valueOf(num)+")\n"+mContentDao.queryAll(1).size() + "次");


        chart1.setText("￥:" + Double.valueOf(num) + "\n（" + contentInfos.size() + "笔）");

        chart2.setText(mContentDao.queryAll(2).size() + "次");
        chart3.setText(mContentDao.queryAll(3).size() + "次");

    }
}