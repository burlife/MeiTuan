package com.bawi.zmt.model;

import android.util.Log;

import com.bawi.zmt.bean.CanGuanBean;
import com.bawi.zmt.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 1 on 2018/7/20.
 */

public class ModelTHome {
    private static final String TAG = "ModelTHome===";
public static  String url03="http://39.108.3.12:3000/v1/search/restaurant?keyword=";
    public  void getData(final  TmodeLHome ModelListener,int page){

        OkHttpUtils.doget(url03, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG,"失败");
                ModelListener.callBackFailure(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                String string = response.body().string();
                Log.i(TAG,"成功"+string);
                CanGuanBean canGuanBean=gson.fromJson(string,CanGuanBean.class);
                ModelListener.callBackSuccess(canGuanBean);
                Log.i(TAG, "ssssssssssssssssssss: "+canGuanBean.getData().get(0).getName());
            }
        });
}
}
