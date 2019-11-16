package com.yqy.photodo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yqy.photodo.FacePhotoHandle.DetectPhotoActivity;
import com.yqy.photodo.mode.UserLoginResult;
import com.yqy.photodo.util.MD5Util;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserLoginActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private CheckBox mCheckPasswordCb;
    private EditText mUserPhoneEt,mUserPasswordEt;
    private Button mUserLoginBt;
    private Handler mHandler = new Handler();
    private TextView userRegistTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        mCheckPasswordCb = (CheckBox) findViewById(R.id.check_password_cb);
        mUserPhoneEt = (EditText) findViewById(R.id.user_phone_et);
        mUserPasswordEt = (EditText) findViewById(R.id.user_password_et);
        mUserLoginBt = (Button) findViewById(R.id.user_login_bt);
        userRegistTv = (TextView)findViewById(R.id.user_register_tv);
        //密码框处理
        mCheckPasswordCb.setOnCheckedChangeListener(this);
        // 处理点击提交数据
        mUserLoginBt.setOnClickListener(this);
        //注册
        userRegistTv.setOnClickListener(this);

    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        // compoundButton 代表的是当前  CheckBox    checked 代表当前是否选中
        if(checked){
            // 显示密码
            mUserPasswordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{
            // 隐藏密码
            mUserPasswordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        // 把光标移动到最后
        Editable etext = mUserPasswordEt.getText();
        Selection.setSelection(etext, etext.length());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_login_bt:
                dealUserLogin();
                break;
            case R.id.user_register_tv:
                Intent intent = new Intent(this, UserRegistActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void dealUserLogin() {
        // 1.本地验证
        String userPhone = mUserPhoneEt.getText().toString().trim();
        String password = mUserPasswordEt.getText().toString().trim();

        if(TextUtils.isEmpty(userPhone)){
            Toast.makeText(this,"请输入用户名",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_LONG).show();
            return;
        }

        mylogin(userPhone,password);
//        otherlogin(userPhone,password);
    }
    private void mylogin(String userPhone,String password){
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("username",userPhone)
                .add("userpwd",password)
                .build();
        Request request = new Request.Builder().url("http://112.124.4.48:8080/HelloWebStudy_war/checkUser")
                .post(formBody).build();

//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        Log.e("TAG", userPhone+","+password);
//        builder.addFormDataPart("username", userPhone);
//        builder.addFormDataPart("userpwd",password);
//        Request request = new Request.Builder().url("http://192.168.31.168:8080/HelloWebStudy/addUser")
//                .post(builder.build()).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.e("TAG", result);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        dealMyLoginResult(result);
                    }
                });
            }
        });

    }

    private void dealMyLoginResult(String result) {
        if(result.equals("no")){
            // 登录失败
            Toast.makeText(this,"登录失败，检查账号密码",Toast.LENGTH_LONG).show();
        }else{
            SharedPreferences sp =  getSharedPreferences("info",MODE_PRIVATE);
            sp.edit().putBoolean("is_login",true).commit();
            sp.edit().putString("user_info",result).commit();
            finish();
        }
    }

    private void otherlogin( String userPhone,String password){// 2.往后台提交数据
        // OKhttp
        // 1.创建一个OkhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        // 2.构建参数的body  MultipartBody.FORM 表单形式
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        // 2.2封装参数
        builder.addFormDataPart("appid", "1");
        builder.addFormDataPart("cell_phone",userPhone); //  添加多个参数
        builder.addFormDataPart("password", MD5Util.strToMd5(password));// MD5 AES

        // 3. 构建一个请求  post 提交里面是参数的builder   url()请求路径
        Request request = new Request.Builder().url("http://v2.ffu365.com/index.php?m=Api&c=Member&a=login")
                .post(builder.build()).build();

        // 4.发送一个请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "请求出错");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 成功  数据在response里面  获取后台给我们的JSON 字符串
                String result = response.body().string();
                Log.e("TAG", result);
                Gson gson = new Gson();
                final UserLoginResult loginResult = gson.fromJson(result, UserLoginResult.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        dealLoginResult(loginResult);
                    }
                });
            }
        });

    }
    // 3.处理返回的数据
    private void dealLoginResult(UserLoginResult loginResult) {
        // 首先判断有没有成功
        if(loginResult.getErrcode() == 1){
            // 成功处理
            // 1.需要保存登录状态   当前设置为已登录
            SharedPreferences sp =  getSharedPreferences("info",MODE_PRIVATE);
            sp.edit().putBoolean("is_login",true).commit();

            // 2.需要保存用户信息
            UserLoginResult.DataBean userData =  loginResult.getData();
            // SharedPreferences 怎么保存对象   把对象转为JSON String --> SharedPreferences
            Gson gson = new Gson();
            String uesrInfoStr =  gson.toJson(userData);
            // 保存的用户信息为Json格式的字符串
            //centerfragment可以处理信息
            sp.edit().putString("user_info",uesrInfoStr).commit();

            // 3.关掉这个页面
            finish();
        }else{
            // 登录失败
            Toast.makeText(this,loginResult.getErrmsg(),Toast.LENGTH_LONG).show();
        }
    }

}
