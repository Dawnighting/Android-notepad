package com.app.notepad.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.notepad.R;
import com.app.notepad.entity.ContentInfo;
import com.github.czy1121.view.CornerLabelView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {
    private List<ContentInfo> mInfoList = new ArrayList<>();
    private LayoutInflater mLayoutInflater;


    public void setList(List<ContentInfo> list) {
        this.mInfoList = list;
        notifyDataSetChanged();
    }


    public ListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mInfoList.size();
    }

    @Override
    public ContentInfo getItem(int position) {
        return mInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.list_item, null);
        TextView title = view.findViewById(R.id.title);
        TextView time = view.findViewById(R.id.time);
        TextView detail = view.findViewById(R.id.detail);
        CornerLabelView labelView = view.findViewById(R.id.labelView);

        //
        ContentInfo contentInfo = mInfoList.get(position);


        if (contentInfo.getType() == 1) {
            labelView.setText1("记账");
            labelView.setFillColor(Color.parseColor("#EE7942"));
            title.setText("用途：" + contentInfo.getTitle());
            time.setText("消费金额：￥" + contentInfo.getTime());
            detail.setText("详情：" + contentInfo.getDetail());

        } else if (contentInfo.getType() == 2) {
            labelView.setText1("打卡");
            labelView.setFillColor(Color.parseColor("#9C27B0"));
            title.setText("地点：" + contentInfo.getTitle());
            time.setText("打卡时间：" + contentInfo.getTime());
            detail.setText("详情：" + contentInfo.getDetail());
        } else {
            labelView.setText1("便签");
            labelView.setFillColor(Color.parseColor("#A0522D"));
            title.setText("标题：" + contentInfo.getTitle());
            time.setText("时间：" + contentInfo.getTime());
            detail.setText("详情：" + contentInfo.getDetail());

        }

        return view;
    }
}
