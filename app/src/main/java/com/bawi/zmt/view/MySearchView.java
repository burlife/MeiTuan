package com.bawi.zmt.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bawi.zmt.R;


/**
 * Created by 1 on 2018/7/16.
 */

public class MySearchView extends LinearLayout{

    public MySearchView(Context context) {

        this(context,null);
    }

    public MySearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, R.layout.my_search_view,this);
    }



}
