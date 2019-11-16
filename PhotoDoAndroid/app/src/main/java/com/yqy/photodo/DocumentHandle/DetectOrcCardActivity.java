package com.yqy.photodo.DocumentHandle;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.TextView;

import com.google.gson.Gson;
import com.yqy.photodo.R;
import com.yqy.photodo.mode.CardResult;
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
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetectOrcCardActivity extends AppCompatActivity implements View.OnClickListener {
    //照片获取属性
    private static final int ALBUM_OK = 0x0011;
    private static final int CUT_OK = 0x0013;
    private static final int CAMERA_REQUEST = 0x0012;
    //获取文件
    private File tempFile;
    //拍照或选择的图片
    private ImageView detectCardImage;
    //选择图片文字按钮
    private TextView selectCardTv;
    //识别属性
    private TextView isCard;
    private TextView cardName;
    private TextView cardSex;
    private TextView cardBirth;
    private TextView cardNumber;
    private TextView cardMinzu;
    private TextView cardAddress;
    //
    private Context mContext;
    private CardResult cardResult;
    private Handler mHandler = new Handler();
    private CardResult.CardsBean cardsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_orc_card);
        mContext = getApplicationContext();
        initView();
        selectCardTv.setOnClickListener(this);
        tempFile = new File(Environment.getExternalStorageDirectory(),"temp.png");
    }

    private void initView() {
        detectCardImage = findViewById(R.id.detectcard_img);
        selectCardTv = findViewById(R.id.choose_card_tv);
        isCard = findViewById(R.id.iscard_tv);
        cardName = findViewById(R.id.card_name_tv);
        cardSex = findViewById(R.id.card_sex_tv);
        cardBirth = findViewById(R.id.card_birth_tv);
        cardNumber = findViewById(R.id.card_number_tv);
        cardMinzu = findViewById(R.id.card_minzu_tv);
        cardAddress = findViewById(R.id.card_addr_tv);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_card_tv:
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
                    detectCardImage.setImageBitmap(bitmap);
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
                detectCardImage.setImageBitmap(bitmap);
                upLoadImage(bitmaptoString(bitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == CUT_OK){
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap bitmap = extras.getParcelable("data");
                detectCardImage.setImageBitmap(bitmap);
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
        Request request = new Request.Builder().url("https://api-cn.faceplusplus.com/cardpp/v1/ocridcard")
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
                cardResult = gson.fromJson(result, CardResult.class);
                showDetectData(cardResult);
            }
        });
    }

    private void showDetectData(final CardResult cardResult) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(cardResult.getCards().size() > 0){
                    cardsBean =cardResult.getCards().get(0);
                    if(cardsBean.getType() == 1){
                        isCard.setText("是身份证");
                        cardName.setText("姓名："+cardsBean.getName());
                        cardSex.setText("性别：男");
                        cardBirth.setText("生日："+cardsBean.getBirthday());
                        cardNumber.setText("号码："+cardsBean.getId_card_number());
                        cardMinzu.setText("民族："+cardsBean.getRace());
                        cardAddress.setText("地址:"+cardsBean.getAddress());
                    }else{
                        isCard.setText("不是身份证");
                        cardName.setText("姓名：");
                        //String sex = cardsBean.getGender().equals("Male")?"男":"女";
                        cardSex.setText("性别：");
                        cardBirth.setText("生日：");
                        cardNumber.setText("号码：");
                        cardMinzu.setText("民族：");
                        cardAddress.setText("地址:");
                    }
                }else{
                   isCard.setText("不是身份证");
                    isCard.setText("不是身份证");
                    isCard.setText("是身份证");
                    cardName.setText("姓名：");
                    //String sex = cardsBean.getGender().equals("Male")?"男":"女";
                    cardSex.setText("性别：");
                    cardBirth.setText("生日：");
                    cardNumber.setText("号码：");
                    cardMinzu.setText("民族：");
                    cardAddress.setText("地址:");
                }
            }
        });
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
