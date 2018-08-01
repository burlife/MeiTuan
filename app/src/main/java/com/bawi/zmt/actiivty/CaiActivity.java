package com.bawi.zmt.actiivty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bawi.zmt.R;
import com.bawi.zmt.adapters.RecyAdapter;
import com.bawi.zmt.bean.NewsBean;
import com.bawi.zmt.presenter.HomePresenter;
import com.bawi.zmt.view.IaddressView;
import com.bawi.zmt.view.IhomeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.List;

/**
 * Created by 1 on 2018/7/24.
 */

public class CaiActivity extends AppCompatActivity implements IhomeView{
    private TextView title;
    private ImageView back;
    private RecyclerView recycle;
    private HomePresenter presenter;
    private int page=0;
    private PullToRefreshScrollView pull;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cai_layout);
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
        recycle = findViewById(R.id.recycle);
        pull = findViewById(R.id.pull);
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        Intent intent = getIntent();
        String proName = intent.getStringExtra("proName");
        back.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText(proName);
        recycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
       presenter = new HomePresenter(this);
        presenter.getDatas(page);
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                page=0;
                presenter.getDatas(page);
                pull.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                if (page<5){
                    page++;
                    presenter.getDatas(page);
                    pull.onRefreshComplete();
                }
            }
        });
    }

    @Override
    public void callBackSuccess(final NewsBean newsBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //获取数据
                List<NewsBean.DataBean> list = newsBean.getData();
                //创建适配器
                RecyAdapter adapter = new RecyAdapter(CaiActivity.this,list);

                adapter.setCallBackListener(new RecyAdapter.CallBackListener() {
                    @Override
                    public void callBack(String id) {
                        Intent intent=new Intent(CaiActivity.this,XiangQingActivity.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });

                recycle.setAdapter(adapter);
                pull.onRefreshComplete();
            }
        });
    }

    @Override
    public void callBackFailure(int code) {

    }
}
