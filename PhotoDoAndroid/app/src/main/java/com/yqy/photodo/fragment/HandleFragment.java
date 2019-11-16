package com.yqy.photodo.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yqy.photodo.FacePhotoHandle.CompareFaceActivity;
import com.yqy.photodo.FacePhotoHandle.DetectPhotoActivity;
import com.yqy.photodo.FacePhotoHandle.FaceFeatureActivity;
import com.yqy.photodo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HandleFragment extends Fragment implements View.OnClickListener {
    private View mRootView;
    private Context mContext;
    private LinearLayout detectPhotoLinerLayout;
    private LinearLayout detectFaceFeatureLayout;
    private LinearLayout detectCompareLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_handle,null);
        mContext = getActivity();
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        detectPhotoLinerLayout = mRootView.findViewById(R.id.detect_view);
        detectFaceFeatureLayout = mRootView.findViewById(R.id.feature_view);
        detectCompareLayout = mRootView.findViewById(R.id.compare_view);
        detectPhotoLinerLayout.setOnClickListener(this);
        detectFaceFeatureLayout.setOnClickListener(this);
        detectCompareLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.detect_view:
                Intent intent = new Intent(mContext, DetectPhotoActivity.class);
                startActivity(intent);
                break;
            case R.id.compare_view:
                Intent intent2 = new Intent(mContext, CompareFaceActivity.class);
                startActivity(intent2);
                break;
            case R.id.feature_view:
                Intent intent3 = new Intent(mContext, FaceFeatureActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
