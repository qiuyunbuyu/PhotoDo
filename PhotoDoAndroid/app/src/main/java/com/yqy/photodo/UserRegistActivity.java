package com.yqy.photodo;

import android.content.Intent;
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

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserRegistActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private CheckBox mCheckPasswordCb;
    private EditText mUserPhoneEt,mUserPasswordEt;
    private Button mUserRegistBt;
    private Handler mHandler = new Handler();
    private TextView userRegistTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_regist);
        mCheckPasswordCb = (CheckBox) findViewById(R.id.check_password_regist_cb);
        mUserPhoneEt = (EditText) findViewById(R.id.user_phone_regist_et);
        mUserPasswordEt = (EditText) findViewById(R.id.user_password_regist_et);
        mUserRegistBt = (Button) findViewById(R.id.user_regist_bt);
        //密码框处理
        mCheckPasswordCb.setOnCheckedChangeListener(this);
        // 处理点击提交数据
        mUserRegistBt.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
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
        switch (view.getId()) {
            case R.id.user_regist_bt:
                dealUserRegist();
                break;
        }
    }

    private void dealUserRegist() {
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
        myregist(userPhone,password);
    }

    private void myregist(String userPhone, String password) {
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("username",userPhone)
                .add("userpwd",password)
                .build();
        final Request request = new Request.Builder().url("http://112.124.4.48:8080/HelloWebStudy_war/addUser")
                .post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e("TAG", result);
               if(result.equals("ok")){
                   mHandler.post(new Runnable() {
                       @Override
                       public void run() {
                           gotologin();
                       }
                   });
               }
            }
        });
    }

    private void gotologin() {
        Toast.makeText(this,"注册成功，前去登录",Toast.LENGTH_LONG).show();
        finish();
    }
}
