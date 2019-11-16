package com.yqy.photodo.FacePhotoHandle;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yqy.photodo.R;
import com.yqy.photodo.mode.CompareResult;
import com.yqy.photodo.mode.FeatureResult;
import com.yqy.photodo.mode.IsInStorgeResult;
import com.yqy.photodo.util.BitmapUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CompareFaceActivity extends AppCompatActivity implements View.OnClickListener {
    //照片获取属性
    private static final int ALBUM_OK = 0x0011;
    private static final int CUT_OK = 0x0013;
    private static final int CAMERA_REQUEST = 0x0012;
    //获取文件
    private File tempFile;
    private TextView tofindPeople;
    private ImageView tofindImage;
    private Context mContext;
    private Handler mHandler = new Handler();
    private TextView ispeople;
    private TextView ispeoplenum;
    private IsInStorgeResult isInStorgeResult;
    private IsInStorgeResult.ResultsBean resultsBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_face);
        mContext = getApplicationContext();
        initView();
        tofindPeople.setOnClickListener(this);
        tempFile = new File(Environment.getExternalStorageDirectory(),"temp.png");
    }

    private void initView() {
        tofindPeople = findViewById(R.id.choose_find_tv);
        tofindImage = findViewById(R.id.tofind_img);
        ispeoplenum = findViewById(R.id.isonepeoplenum_tv);
        ispeople = findViewById(R.id.isonepeople_tv);
    }
    
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_find_tv:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this,R.style.dialog);
        View dialogView = View.inflate(this,R.layout.photo_choose_dialog,null);
        dialog.setContentView(dialogView);
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button cancelBt = (Button) dialogView.findViewById(R.id.user_cancel);
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button imageDepotBt = (Button) dialogView.findViewById(R.id.image_depot);
        imageDepotBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 选择照片   利用系统   隐示意图
                Intent albumIntent = new Intent(Intent.ACTION_PICK);
                // 匹配类型 只需要匹配一个就可以了
                albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                // 为什么不是直接开启  选完相片之后 startActivityForResult 需要获取图片信息
                startActivityForResult(albumIntent, ALBUM_OK);
                dialog.dismiss();
            }
        });
        Button photoCamreBt = (Button) dialogView.findViewById(R.id.photo_camre);
        photoCamreBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 拍照
                Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                // MediaStore.EXTRA_OUTPUT 调用系统拍完之后的照片，就会放到这个tempFile文件
                getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                startActivityForResult(getImageByCamera, CAMERA_REQUEST);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){// resultCode是相册应用Activity给我们 RESULT_OK -1
            if(requestCode == ALBUM_OK){
                // 相片选择成功之后  数据在data里面
                Uri uri = data.getData(); // 其实就是一个路径  可以通过uri去获取文件路径  ， BitmapFactory解析Bitmap
                try {
                    Bitmap bitmap = BitmapUtil.getBitmapFormUri(this,uri);
                    tofindImage.setImageBitmap(bitmap);
                    upLoadImage(bitmaptoString(bitmap));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        if(requestCode == CAMERA_REQUEST){// 拍照成功  我们照片 给他设置了一个临时文件
            Uri uri = Uri.fromFile(tempFile);
            try {
                Bitmap bitmap = BitmapUtil.getBitmapFormUri(this,uri);
                tofindImage.setImageBitmap(bitmap);
                upLoadImage(bitmaptoString(bitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == CUT_OK){
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap bitmap = extras.getParcelable("data");
                tofindImage.setImageBitmap(bitmap);
                saveBitmapToFile(bitmap);
            }
        }
    }

    private void upLoadImage(String base64) {
        OkHttpClient okHttpClient = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("api_key", "UJ88Oydp2oESMqzkqVHWyTZIoltVu5EE");
        builder.addFormDataPart("api_secret", "yLLmOlE9fqtXm0Rzi12Kb9HLWAmPGeDj");
        builder.addFormDataPart("image_base64", base64);
        builder.addFormDataPart("faceset_token","c462c351d320cc8ab42c2f01fad66229");
        Request request = new Request.Builder().url("https://api-cn.faceplusplus.com/facepp/v3/search")
                .post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e("TAG", result);
                Gson gson = new Gson();
                isInStorgeResult = gson.fromJson(result, IsInStorgeResult.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showDetectData(isInStorgeResult);
                    }
                });

            }
        });
    }

    private void showDetectData(IsInStorgeResult isInStorgeResult) {
        if(isInStorgeResult.getResults()!=null){
            resultsBean = isInStorgeResult.getResults().get(0);
            ispeoplenum.setText("相似度："+resultsBean.getConfidence());
            if(resultsBean.getConfidence()>75){
                ispeople.setText("存在人脸库中");
            }else{
                ispeople.setText("不在人脸库中");
            }
        }else{
            ispeoplenum.setText("相似度：");
            ispeople.setText("不在人脸库中");
        }
    }

    private void saveBitmapToFile(Bitmap bitmap) {
        try {
            OutputStream os = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String bitmaptoString(Bitmap bitmap) {
        // 将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }
}
