package com.bawi.zmt.actiivty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.bawi.zmt.R;
import com.bawi.zmt.adapters.ListAdapter;
import com.bawi.zmt.bean.CanGuanBean;
import com.bawi.zmt.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by 1 on 2018/7/18.
 */

public class SearchActivity extends AppCompatActivity{
private ListView listView;
private ListAdapter listAdapter;
public static String url="http://39.108.3.12:3000/v1/search/restaurant?keyword=%E5%BF%85%E8%83%9C%E5%AE%A2";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.search_layout);
       initDatas();
    }

    private void initDatas() {
    }

}
