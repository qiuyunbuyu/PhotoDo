package com.yqy.photodo.ui;

import android.widget.ListView;

/**
 * Created by yqy on 2019/11/12.
 */

public class ImplantListView extends ListView {
    public ImplantListView(android.content.Context context,
                           android.util.AttributeSet attrs) {
        super(context, attrs);
    }
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {// 测量方法   计算
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
