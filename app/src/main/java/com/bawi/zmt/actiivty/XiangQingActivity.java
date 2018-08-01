package com.bawi.zmt.actiivty;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bawi.zmt.R;
import com.bawi.zmt.bean.Xqing;
import com.bawi.zmt.fragment.DianCaiFragment;
import com.bawi.zmt.fragment.PingjiaFragment;
import com.bawi.zmt.fragment.ShangjiaFragment;
import com.bawi.zmt.presenter.HomePresenter;
import com.bawi.zmt.view.XqView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class XiangQingActivity extends AppCompatActivity implements XqView{

    private ImageView back;
    private TextView title;
    private TextView chang;
    private ImageView img;
    private TextView bulletin;
    private ViewFlipper vf;
    private HomePresenter presenter;
    private TabLayout tab;
    private DianCaiFragment dianCaiFragment;
    private PingjiaFragment pingjiaFragment;
    private ShangjiaFragment shangjiaFragment;
    private ArrayList<Fragment> list;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiang_layout);
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        presenter = new HomePresenter(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        presenter.getXqs(id);
        Toast.makeText(this,""+id,Toast.LENGTH_LONG).show();
        pmd();

        dianCaiFragment = new DianCaiFragment();
        pingjiaFragment = new PingjiaFragment();
        shangjiaFragment = new ShangjiaFragment();
        list = new ArrayList<>();
        list.add(dianCaiFragment);
        list.add(pingjiaFragment);
        list.add(shangjiaFragment);
        for (Fragment fragment:list){
            getSupportFragmentManager().beginTransaction().add(R.id.xq_frame,fragment).hide(fragment).commit();
        }
        currentFragment = list.get(0);
        getSupportFragmentManager().beginTransaction().show(currentFragment).commit();
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.hide(currentFragment);
                    currentFragment =list.get(0);
                    transaction.show(currentFragment).commit();
                }
                if (tab.getPosition()==1){
                    FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                    transaction2.hide(currentFragment);
                    currentFragment= list.get(1);
                    transaction2.hide(currentFragment).commit();
                }
                if (tab.getPosition()==2){
                    FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                    transaction3.hide(currentFragment);
                    currentFragment = list.get(2);
                    transaction3.hide(currentFragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initView() {
        back = findViewById(R.id.back_xq);
        title = findViewById(R.id.title_xq);
        vf = findViewById(R.id.filpper);
        img = findViewById(R.id.img_xq);
        chang = findViewById(R.id.chang);
        bulletin = findViewById(R.id.bulletin);
        tab = findViewById(R.id.tab_layout);

    }

    private void pmd() {
        View view1 = View.inflate(this, R.layout.one, null);
        View view2 = View.inflate(this, R.layout.two, null);
        View view3 = View.inflate(this, R.layout.three, null);
        vf.addView(view1);
        vf.addView(view2);
        vf.addView(view3);
    }


    @Override
    public void success(final Xqing xqing, String id) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Xqing.DataBean list = xqing.getData();
                title.setText(list.getName());
                chang.setText(list.getMin_price_tip()+" |"+list.getShipping_fee_tip()+" |"+list.getDelivery_time_tip());
                bulletin.setText(list.getBulletin());
                Glide.with(XiangQingActivity.this).load(list.getPic_url()).into(img);
            }
        });
    }

    }




