package com.bawi.zmt.model;

import android.util.Log;

import com.bawi.zmt.bean.AddressBean;
import com.bawi.zmt.bean.DianCan;
import com.bawi.zmt.bean.NewsBean;
import com.bawi.zmt.bean.Sousuo;
import com.bawi.zmt.bean.Xqing;
import com.bawi.zmt.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
            /**
             * Created by 1 on 2018/7/17.
             */
            public class ModelIHome {
                private static final String TAG = "ModelIHome===";
                private String api="http://39.108.3.12:3000/v1/restaurants?offset=";
                private String api2="&limit=4&lng=116.29845&lat=39.95933";
                public  void getData(final  ImodelHome ModelListener,int page) {
                    OkHttpUtils.doget(api + page + api2, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i(TAG, "失败");

                            ModelListener.callBackFailure(1);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Gson gson = new Gson();
                            String string = response.body().string();
                            Log.i(TAG, "成功" + string);
                            NewsBean newsBean = gson.fromJson(string, NewsBean.class);
                            ModelListener.callBackSuccess(newsBean);
                            Log.i(TAG, "ssssssssssssssssssss: " + newsBean.getData().get(0).getName());
                        }
                    });
                }
                //获取地址
                public void getAddress(final IaddressModel iaddressModel){
                    OkHttpUtils.dogetx("http://39.108.3.12:3000/v1/location", new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //获取数据
                            String string = response.body().string();
                            //解析数据
                            Gson gson = new Gson();
                            AddressBean addres=gson.fromJson(string,AddressBean.class);
                            iaddressModel.getAddress(addres);
                        }
        });
    }
                public void getSousuo(final IsousuoModel isousuoModel, final String s){
                    OkHttpUtils.dogetx("http://39.108.3.12:3000/v1/search/restaurant?keyword="+s, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String string = response.body().string();
                            Gson gson=new Gson();
                            Sousuo sousuo = gson.fromJson(string, Sousuo.class);
                            isousuoModel.successSou(sousuo,s);
                        }
                    });
                }
                public void getXq(final XqModel xqModel, final String id){
                    OkHttpUtils.dogetx("http://39.108.3.12:3000/v1/restaurant/"+id, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String string = response.body().string();
                            Gson gson=new Gson();
                            Xqing xqing = gson.fromJson(string, Xqing.class);
                            xqModel.successXq(xqing,id);
                        }
                    });
                }
                public void diancan(final IdianCanModel idianCanModel, final String id){
                    OkHttpUtils.dogetx("http://39.108.3.12:3000/v1/food/" + id, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String string = response.body().string();
                            Gson gson=new Gson();
                            DianCan dianCan = gson.fromJson(string, DianCan.class);
                            idianCanModel.successDiancan(dianCan,id);
                        }
                    });
                }
}
