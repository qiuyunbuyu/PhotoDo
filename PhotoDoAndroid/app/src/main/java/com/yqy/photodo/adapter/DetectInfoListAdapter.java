package com.yqy.photodo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yqy.photodo.R;
import com.yqy.photodo.mode.DetectPhotoResult;

import java.util.List;

/**
 * Created by yqy on 2019/11/12.
 */

public class DetectInfoListAdapter extends BaseAdapter {
    private List<DetectPhotoResult.FacesBean> mNews;
    private Context mContext;

    public DetectInfoListAdapter(Context context, List<DetectPhotoResult.FacesBean> news){
        this.mContext = context;
        this.mNews = news;
    }
    @Override
    public int getCount() {
        return mNews.size();
    }

    @Override
    public Object getItem(int position) {
        return mNews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView =  View.inflate(mContext, R.layout.item_detect_information_list,null);

            viewHolder = new ViewHolder();
            viewHolder.newsSex = (TextView) convertView.findViewById(R.id.detect_sex);
            viewHolder.newAge = (TextView) convertView.findViewById(R.id.detect_age);
            viewHolder.newNum = (TextView) convertView.findViewById(R.id.detect_num);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 设置条目数据
        final DetectPhotoResult.FacesBean news = mNews.get(position);
        String sex = news.getAttributes().getGender().getValue();
        if(sex.equals("Male")){
            sex = "男";
        }else{
            sex = "女";
        }
        viewHolder.newsSex.setText("性别："+sex);
        viewHolder.newAge.setText("年龄："+String.valueOf(news.getAttributes().getAge().getValue()));
        viewHolder.newNum.setText(String.valueOf(position+1)+"号人脸信息");
        return convertView;
    }

    public static class ViewHolder{
        public TextView newsSex;
        public TextView newAge;
        public TextView newNum;
    }
}
