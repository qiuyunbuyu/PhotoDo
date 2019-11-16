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

import com.yqy.photodo.DocumentHandle.BankCardActivity;
import com.yqy.photodo.DocumentHandle.DetectOrcCardActivity;
import com.yqy.photodo.FacePhotoHandle.DetectPhotoActivity;
import com.yqy.photodo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StorgeFragment extends Fragment implements View.OnClickListener {
    private View mRootView;
    private Context mContext;
    private LinearLayout detectOrcCardLinerLayout;
    private LinearLayout bankCardLinerLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_storge,null);
        mContext = getActivity();
        return mRootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        detectOrcCardLinerLayout = mRootView.findViewById(R.id.document_orccard);
        detectOrcCardLinerLayout.setOnClickListener(this);
        bankCardLinerLayout = mRootView.findViewById(R.id.bankcard_lv);
        bankCardLinerLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.document_orccard:
                Intent intent = new Intent(mContext, DetectOrcCardActivity.class);
                startActivity(intent);
                break;
            case R.id.bankcard_lv:
                Intent intent2 = new Intent(mContext, BankCardActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
