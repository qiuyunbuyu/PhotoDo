package com.yqy.photodo.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yqy.photodo.FaceCenter.FaceStorgeActivity;
import com.yqy.photodo.R;
import com.yqy.photodo.UserLoginActivity;
import com.yqy.photodo.mode.UserLoginResult;

/**
 * A simple {@link Fragment} subclass.
 */
public class CenterFragment extends Fragment  implements View.OnClickListener {

    private View mRootView;
    private TextView mUserLoginTv,mUserExitLoginTv,mUserNameTv,mUserLocationTv;
    private LinearLayout mUserLoginedLl;
    private Context mContext;
    private ImageView mUserHeadIv;
    private TextView toFaceStorge;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_center,null);
        mContext = getActivity();
        return mRootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // findViewById
        mUserLoginTv = (TextView) mRootView.findViewById(R.id.user_login_tv);
        mUserExitLoginTv = (TextView) mRootView.findViewById(R.id.user_exit_login);
        mUserLoginedLl = (LinearLayout) mRootView.findViewById(R.id.user_logined_ll);
        mUserHeadIv = (ImageView) mRootView.findViewById(R.id.user_head_iv);
        mUserNameTv = (TextView) mRootView.findViewById(R.id.user_name_tv);
        mUserLocationTv = (TextView) mRootView.findViewById(R.id.user_location_tv);

        toFaceStorge = (TextView)mRootView.findViewById(R.id.to_facestorege);

        mUserLoginTv.setOnClickListener(this);
        mUserExitLoginTv.setOnClickListener(this);
        toFaceStorge.setOnClickListener(this);
    }
    @Override
    public void onResume() {// Main --> Login(true)  --> MainActivity onResume  --> CenterFragment OnResume()
        super.onResume();
        // 判断用户是否登录，如果登录了显示登录的中心头部，否则显示未登录的中心头部
        SharedPreferences sp = mContext.getSharedPreferences("info",Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("is_login",false);
        if(isLogin){
            mUserLoginedLl.setVisibility(View.VISIBLE);
            mUserLoginTv.setVisibility(View.GONE);
            // 设置用户信息

            String userInfoStr = mContext.getSharedPreferences("info",Context.MODE_PRIVATE).getString("user_info",null);
            mUserLocationTv.setText(userInfoStr);
//            Log.e("TAG",userInfoStr);
//            if(!TextUtils.isEmpty(userInfoStr)){
//                // 把用户信息JSON转为对象
//                Gson gson = new Gson();
//                UserLoginResult.DataBean userInfo = gson.fromJson(userInfoStr, UserLoginResult.DataBean.class);
//
//                // 设置图片
//                Glide.with(mContext).load(userInfo.getMember_info().getMember_avatar()).into(mUserHeadIv);
//
//                // 设置名称和地区
//                mUserNameTv.setText(userInfo.getMember_info().getMember_name());
//                mUserLocationTv.setText(userInfo.getMember_info().getMember_location_text());
//            }
        }else{
            // 没有登录
            mUserLoginTv.setVisibility(View.VISIBLE);
            mUserLoginedLl.setVisibility(View.GONE);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_login_tv:
                // 跳转到登录
                Intent intent = new Intent(mContext,UserLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.user_exit_login:
                // 把登录状态置为false
                SharedPreferences sp = mContext.getSharedPreferences("info",Context.MODE_PRIVATE);
                sp.edit().putBoolean("is_login",false).commit();
                Intent intent2 = new Intent(mContext,UserLoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.to_facestorege:
                Intent intent3 = new Intent(mContext,FaceStorgeActivity.class);
                startActivity(intent3);
        }
    }
}
