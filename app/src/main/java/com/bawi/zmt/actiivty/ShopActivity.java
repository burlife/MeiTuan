package com.bawi.zmt.actiivty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.bawi.zmt.R;
import com.bawi.zmt.adapters.MyShopAdapter;
import com.bawi.zmt.bean.Sousuo;
import com.bawi.zmt.presenter.HomePresenter;
import com.bawi.zmt.view.IsousuoView;
import java.util.List;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener ,IsousuoView {

    private ImageView back2;
    private EditText shop;
    private Button sousuo;
    private RecyclerView rclv;
    private HomePresenter presenter;
    private String s=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);
        initView();
        back2.setOnClickListener(this);
        sousuo.setOnClickListener(this);
        presenter = new HomePresenter(this);
    }

    private void initView() {
        back2 = findViewById(R.id.back2);
        shop = findViewById(R.id.shop);
        sousuo = findViewById(R.id.sousuo1);
        rclv = findViewById(R.id.recycle_view);
        rclv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sousuo1:
                s = shop.getText().toString();
                if (s.isEmpty()){
                    Toast.makeText(ShopActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
                    rclv.setVisibility(View.GONE);
                }else{
                    Toast.makeText(ShopActivity.this,s,Toast.LENGTH_SHORT).show();
                    presenter.getSousuos(s);
                }
                break;
            case R.id.back2:
                finish();
                break;
        }
    }

    @Override
    public void callBackSuccessSou(final Sousuo sousuo,String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<Sousuo.DataBean> list = sousuo.getData();
                //设置适配器
                MyShopAdapter adapter = new MyShopAdapter(ShopActivity.this, list);
                adapter.setClickListener(new MyShopAdapter.ItemViewClickListener() {
                    @Override
                    public void onItemClick(String id) {
                        Intent intent = new Intent(ShopActivity.this, XiangQingActivity.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });
                rclv.setAdapter(adapter);
                rclv.setVisibility(View.VISIBLE);
            }
        });
    }
}
