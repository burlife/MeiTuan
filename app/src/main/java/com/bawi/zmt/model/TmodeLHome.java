package com.bawi.zmt.model;


import com.bawi.zmt.bean.CanGuanBean;

/**
 * Created by 1 on 2018/7/20.
 */

public interface TmodeLHome {
    //成功
    public  void callBackSuccess(CanGuanBean canGuanBean);
    //失败
    public  void callBackFailure(int code);
}
