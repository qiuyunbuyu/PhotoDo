package com.yqy.photodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.yqy.photodo.adapter.HomePageAdapter;
import com.yqy.photodo.fragment.CenterFragment;
import com.yqy.photodo.fragment.HandleFragment;
import com.yqy.photodo.fragment.StorgeFragment;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager mViewPager;
    private RadioButton mHandleButton;
    private RadioButton mStorgeButton;
    private RadioButton mCenterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mHandleButton = findViewById(R.id.handle_rb);
        mStorgeButton = findViewById(R.id.storge_rb);
        mCenterButton = findViewById(R.id.center_rb);

        mHandleButton.setOnClickListener(this);
        mStorgeButton.setOnClickListener(this);
        mCenterButton.setOnClickListener(this);

        mViewPager.setOnPageChangeListener(this);
    }
    private void initData() {
        ArrayList<Fragment> homeFragments = new ArrayList<>();
        homeFragments.add(new HandleFragment());
        homeFragments.add(new StorgeFragment());
        homeFragments.add(new CenterFragment());
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager(),homeFragments);
        mViewPager.setAdapter(homePageAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mHandleButton.setChecked(true);
                break;
            case 1:
                mStorgeButton.setChecked(true);
                break;
            case 2:
                mCenterButton.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.handle_rb:
                mViewPager.setCurrentItem(0,false);
                break;
            case R.id.storge_rb:
                // 先判断一下用户有没有登录
                SharedPreferences sp =  getSharedPreferences("info",MODE_PRIVATE);
                boolean isLogin = sp.getBoolean("is_login",false);

                if(isLogin){
                    // 把ViewPager切换到第二页
                    mViewPager.setCurrentItem(1,false);
                }else{
                    // 如果用户没有登录就跳转到登录界面
                    Intent intent = new Intent(this,UserLoginActivity.class);
                    startActivity(intent);

                    // 找到原来的位置，然后把原来的位置恢复
                    onPageSelected(mViewPager.getCurrentItem());
                }
                break;
//                mViewPager.setCurrentItem(1,false);
//                break;
            case R.id.center_rb:
                mViewPager.setCurrentItem(2,false);
                break;
        }
    }
}
