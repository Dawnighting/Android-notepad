package com.app.notepad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.app.notepad.activity.ChargeActivity;
import com.app.notepad.activity.Chart1Activity;
import com.app.notepad.activity.LocationActivity;
import com.app.notepad.activity.StickyActivity;
import com.app.notepad.adapter.ListAdapter;
import com.app.notepad.base.BaseActivity;
import com.app.notepad.dao.ContentDao;
import com.app.notepad.entity.ContentInfo;

public class MainActivity extends BaseActivity {
    private String titles[] = {"记账", "打卡", "便签"};
    private int position = 0;

    private ListView listview;
    private ListAdapter mListAdapter;
    private ContentDao mContentDao;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        listview = findViewById(R.id.listview);
        mListAdapter = new ListAdapter(MainActivity.this);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ContentInfo contentInfo = mListAdapter.getItem(position);


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("确定要删除吗？");
                builder.setMessage("删除后的数据将无法恢复");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (mContentDao == null) {
                            mContentDao = new ContentDao(MainActivity.this);
                        }
                        int row = mContentDao.delete(contentInfo.get_id());
                        if (row > 0) {
                            if (null != mListAdapter) {
                                mListAdapter.setList(mContentDao.queryAll());
                            }

                            showToast("删除成功");
                        } else {
                            showToast("删除失败");
                        }
                    }
                });

                builder.show();


                return true;
            }
        });


    }

    @Override
    protected void setListener() {
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("新建分类标签");
                builder.setSingleChoiceItems(titles, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position = which;
                        showToast(titles[which]);
                    }
                });
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (position == 0) {
                            startActivityForResult(new Intent(MainActivity.this, ChargeActivity.class), 200);
                        } else if (position == 1) {
                            startActivityForResult(new Intent(MainActivity.this, LocationActivity.class), 200);
                        } else {
                            startActivityForResult(new Intent(MainActivity.this, StickyActivity.class), 200);
                        }
                        position = 0;

                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        findViewById(R.id.chart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Chart1Activity.class));
            }
        });

    }

    @Override
    protected void initData() {
        if (mContentDao == null) {
            mContentDao = new ContentDao(this);
        }
        listview.setAdapter(mListAdapter);
        mListAdapter.setList(mContentDao.queryAll());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200) {
            initData();

        }
    }
}