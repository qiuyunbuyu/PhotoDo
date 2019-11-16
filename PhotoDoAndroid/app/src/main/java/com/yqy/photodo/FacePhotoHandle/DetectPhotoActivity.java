package com.yqy.photodo.FacePhotoHandle;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
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
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yqy.photodo.R;
import com.yqy.photodo.adapter.DetectInfoListAdapter;
import com.yqy.photodo.mode.DetectPhotoResult;
import com.yqy.photodo.util.BitmapUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DetectPhotoActivity extends AppCompatActivity implements View.OnClickListener {
    //照片获取属性
    private static final int ALBUM_OK = 0x0011;
    private static final int CUT_OK = 0x0013;
    private static final int CAMERA_REQUEST = 0x0012;
    //获取文件
    private File tempFile;
    //拍照或选择的图片
    private ImageView detectPhotoImage;
    //选择图片文字
    private TextView choosePhotoTv;
    //人脸信息列表
    private ListView mNewsLv;
    //图片信息
    private TextView jsontesttext;
    private Handler mHandler = new Handler();
    //
    private DetectPhotoResult detectPhotoResult;
    private DetectPhotoResult.FacesBean facesBean;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_photo);
        mContext = getApplicationContext();
        initView();
        choosePhotoTv.setOnClickListener(this);
        tempFile = new File(Environment.getExternalStorageDirectory(),"temp.png");
    }
    private void initView() {
        detectPhotoImage = findViewById(R.id.detectphoto_img);
        jsontesttext = findViewById(R.id.jsontesttext);
        choosePhotoTv = findViewById(R.id.choose_photo_tv);
        mNewsLv = findViewById(R.id.detect_information_lv);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_photo_tv:
                showDialog();
                break;
        }
    }
    private void showDialog() {

        // 4.因为有头部  ， 那么需要给他设置一些参数    ，用style 来配置
        // 1.利用Dialog
        final Dialog dialog = new Dialog(this,R.style.dialog);

        // 3.利用View.inflate 获取dialog需要显示的布局
        View dialogView = View.inflate(this,R.layout.photo_choose_dialog,null);

        // 2.设置dialog的显示布局  Java里面的思想要什么你就给什么
        dialog.setContentView(dialogView);

        // 5.从底部弹出 这是一个动画   控制动画我们都是用View
        // 5.1 获取弹出框的View
        Window window = dialog.getWindow();
        // 5.2 设置动画  resouceId 又是一个style
        window.setWindowAnimations(R.style.main_menu_animstyle);

        // 5.3 固定在底部
        window.setGravity(Gravity.BOTTOM);

        // 5.4宽度全屏
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //dialog.show();
        // 6.处理点击事件
        Button cancelBt = (Button) dialogView.findViewById(R.id.user_cancel);
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 关闭dialog
                dialog.dismiss();
            }
        });
        //dialog.show();
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
//        dialog.show();
        Button photoCamreBt = (Button) dialogView.findViewById(R.id.photo_camre);
        photoCamreBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 拍照
                Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                // MediaStore.EXTRA_OUTPUT 调用系统拍完之后的照片，就会放到这个tempFile文件
                getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(tempFile));
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
                   detectPhotoImage.setImageBitmap(bitmap);
                    upLoadImage(bitmaptoString(bitmap));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            }

            if(requestCode == CAMERA_REQUEST){// 拍照成功  我们照片 给他设置了一个临时文件
//                clipImage(Uri.fromFile(tempFile));
                Uri uri = Uri.fromFile(tempFile);
                try {
                    Bitmap bitmap = BitmapUtil.getBitmapFormUri(this,uri);
                    detectPhotoImage.setImageBitmap(bitmap);
                    upLoadImage(bitmaptoString(bitmap));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(requestCode == CUT_OK){
                // 获取裁剪的图片数据
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap bitmap = extras.getParcelable("data");
                    detectPhotoImage.setImageBitmap(bitmap);
                    // 提交到服务器  上传提交到服务器  File
                    // 需要把裁剪完后的图片传到服务器  Bitmap  -- > File
                    // 1.把bitmap保存到文件  tempFile
                    saveBitmapToFile(bitmap);

                    // 2.把图片文件file上传到服务器
                    //upLoadImage();
                }
            }
        }
    //发送图片调用face++接口测试
    private void upLoadImage(String base64) {
        OkHttpClient okHttpClient = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("api_key", "UJ88Oydp2oESMqzkqVHWyTZIoltVu5EE");
        builder.addFormDataPart("api_secret", "yLLmOlE9fqtXm0Rzi12Kb9HLWAmPGeDj");
//        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), tempFile);
//        builder.addFormDataPart("image_file", tempFile.getName(), fileBody);

       builder.addFormDataPart("image_base64", base64);
        builder.addFormDataPart("return_attributes", "gender,age");

        Request request = new Request.Builder().url("https://api-cn.faceplusplus.com/facepp/v3/detect")
                .post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e("TAG", result);
                //showDetectData(result);
                Gson gson = new Gson();
                detectPhotoResult = gson.fromJson(result, DetectPhotoResult.class);
                showDetectData(detectPhotoResult);
                facesBean = detectPhotoResult.getFaces().get(0);
                if(facesBean != null){
                    uploadtostorge(facesBean);
                }
            }
        });
    }

    private void uploadtostorge(DetectPhotoResult.FacesBean detectPhotoResult) {
        OkHttpClient okHttpClient = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("api_key", "UJ88Oydp2oESMqzkqVHWyTZIoltVu5EE");
        builder.addFormDataPart("api_secret", "yLLmOlE9fqtXm0Rzi12Kb9HLWAmPGeDj");
        builder.addFormDataPart("faceset_token","c462c351d320cc8ab42c2f01fad66229");
        builder.addFormDataPart("face_tokens",facesBean.getFace_token());
        Request request = new Request.Builder().url("https://api-cn.faceplusplus.com/facepp/v3/faceset/addface")
                .post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e("TAG", result);
            }
        });
    }

    //显示数据
    private void showDetectData(final DetectPhotoResult detectPhotoResult) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //设置总人数
                //Log.e("TAG", String.valueOf(detectPhotoResult.getFaces().size()));
                jsontesttext.setText("人数："+String.valueOf(detectPhotoResult.getFaces().size())+"人");
                mNewsLv.setAdapter(new DetectInfoListAdapter(mContext,detectPhotoResult.getFaces()));

            }
        });
    }
    //
//    保存文件
    private void saveBitmapToFile(Bitmap bitmap) {
        try {
            OutputStream os = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 调用系统的裁剪方法
     */
    private void clipImage(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        // 数据 uri 代表裁剪哪一张
        intent.setDataAndType(uri, "image/*");
        // 传递数据
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例，这里设置的是正方形（长宽比为1:1）
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        // 你待会裁剪完之后需要获取数据   startActivityForResult
        startActivityForResult(intent, CUT_OK);
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
