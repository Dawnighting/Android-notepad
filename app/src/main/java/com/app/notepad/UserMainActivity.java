package com.app.notepad;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.app.notepad.base.BaseActivity;
import com.app.notepad.fragment.HomeFragment;
import com.app.notepad.fragment.UserInfoFragment;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class UserMainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar mBottomNavigationBar;
    private HomeFragment mHomeFragment;
    private UserInfoFragment mUserInfoFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_main;
    }

    @Override
    protected void initView() {


        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        mBottomNavigationBar.setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor("#E89BBE") //选中颜色
                .setInActiveColor("#515151") //未选中颜色
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setBarBackgroundColor("#ffffff");//导航栏背景色


        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_user_home, "首页").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_user_home_normal)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_user_order, "购物车").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_user_order_normal)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_user_mine, "我的").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_user_mine_normal)))
                .setFirstSelectedPosition(0)
                .initialise();

        selectedFragment(0);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    private void selectedFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        if (position == 0) {
            if (mHomeFragment == null) {
                mHomeFragment = new HomeFragment();
                fragmentTransaction.add(R.id.content, mHomeFragment);
            } else {
                fragmentTransaction.show(mHomeFragment);
            }
        } else if (position == 1) {
            if (mUserInfoFragment == null) {
                mUserInfoFragment = new UserInfoFragment();
                fragmentTransaction.add(R.id.content, mUserInfoFragment);
            } else {
                fragmentTransaction.show(mUserInfoFragment);

            }
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction beginTransaction) {
        if (null != mHomeFragment) {
            beginTransaction.hide(mHomeFragment);
        }


        if (null != mUserInfoFragment) {
            beginTransaction.hide(mUserInfoFragment);
        }

    }

    @Override
    public void onTabSelected(int position) {
        selectedFragment(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}