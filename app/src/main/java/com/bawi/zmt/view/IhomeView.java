package com.bawi.zmt.view;


import com.bawi.zmt.bean.NewsBean;

/**
 * Created by 1 on 2018/7/16.
 */

public interface IhomeView {
    public  void callBackSuccess(NewsBean newsBean);
    public  void callBackFailure(int code);

}
