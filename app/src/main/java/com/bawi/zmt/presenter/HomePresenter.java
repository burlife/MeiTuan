package com.bawi.zmt.presenter;


import android.util.Log;

import com.bawi.zmt.bean.AddressBean;
import com.bawi.zmt.bean.DianCan;
import com.bawi.zmt.bean.NewsBean;
import com.bawi.zmt.bean.Sousuo;
import com.bawi.zmt.bean.Xqing;
import com.bawi.zmt.model.IaddressModel;
import com.bawi.zmt.model.IdianCanModel;
import com.bawi.zmt.model.ImodelHome;
import com.bawi.zmt.model.IsousuoModel;
import com.bawi.zmt.model.ModelIHome;
import com.bawi.zmt.model.XqModel;
import com.bawi.zmt.view.IaddressView;
import com.bawi.zmt.view.IdianCanView;
import com.bawi.zmt.view.IhomeView;
import com.bawi.zmt.view.IsousuoView;
import com.bawi.zmt.view.XqView;

/**
 * Created by 1 on 2018/7/17.
 */

public class HomePresenter implements IhomePresenter{
    private static final String TAG =HomePresenter.class.getSimpleName();
    private IhomeView ihomeView;
    private ModelIHome modelIHome;
    private IaddressView iaddressView;
    private IdianCanView idianCanView;
    private IsousuoView isousuoView;
    private XqView xqView;
    public HomePresenter(IdianCanView idianCanView) {
        this.idianCanView = idianCanView;
        this.modelIHome=new ModelIHome();
    }
    public HomePresenter(XqView xqView) {
        this.modelIHome=new ModelIHome();
        this.xqView = xqView;
    }

    public HomePresenter(IsousuoView isousuoView) {
        this.modelIHome=new ModelIHome();
        this.isousuoView = isousuoView;
    }

    public HomePresenter(IhomeView ihomeView){
        this.ihomeView=ihomeView;
        this.iaddressView=iaddressView;
        this.modelIHome=new ModelIHome();
    }
    public HomePresenter(IaddressView iaddressView){
        this.iaddressView=iaddressView;
        this.modelIHome=new ModelIHome();
    }
    public  void getDatas(int page){
        modelIHome.getData(new ImodelHome() {
            @Override
            public void callBackSuccess(NewsBean newsBean) {
                Log.i(TAG,"成功"+newsBean);
                ihomeView.callBackSuccess(newsBean);
            }

            @Override
            public void callBackFailure(int code) {

                ihomeView.callBackFailure(code);
            }
        },page);
    }
    @Override
    public void ondestroys() {
          if (ihomeView!=null){
              ihomeView=null;
          }
    }
    public void getAddress(){
        modelIHome.getAddress(new IaddressModel() {
            @Override
            public void getAddress(AddressBean addressBean) {
                 iaddressView.getAddress(addressBean);
            }

        });
    }
    public void diancans(String id){
        modelIHome.diancan(new IdianCanModel() {
            @Override
            public void successDiancan(DianCan dianCan, String id) {
                idianCanView.Diancans(dianCan,id);
            }
        },id);
    }
    public void getSousuos(String s){
        modelIHome.getSousuo(new IsousuoModel() {
            @Override
            public void successSou(Sousuo sousuo, String s) {
                isousuoView.callBackSuccessSou(sousuo,s);
            }
        },s);
    }
    public void getXqs(String id){
        modelIHome.getXq(new XqModel() {
            @Override
            public void successXq(Xqing xqing, String id) {
                xqView.success(xqing,id);
            }
        },id);
    }
}
