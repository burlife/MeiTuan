package com.bawi.zmt.model;


import com.bawi.zmt.bean.NewsBean;

/**
 * Created by 1 on 2018/7/17.
 */

public interface ImodelHome {
    //成功
    public  void callBackSuccess(NewsBean newsBean);
    //失败
    public  void callBackFailure(int code);


}
