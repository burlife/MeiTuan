package com.bawi.zmt.actiivty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawi.zmt.MainActivity;
import com.bawi.zmt.R;
import com.bawi.zmt.bean.AddressBean;
import com.bawi.zmt.presenter.HomePresenter;
import com.bawi.zmt.view.IaddressView;

/**
 * Created by 1 on 2018/7/23.
 */

public class AddressActivity extends AppCompatActivity implements IaddressView,View.OnClickListener{
    private ImageView bac_image;
    private EditText editText;
    private Button button01,button02;
    private HomePresenter homePresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_layout);
        bac_image=findViewById(R.id.jian);
        editText=findViewById(R.id.ed_address);
        button01=findViewById(R.id.b_sou);
        button02=findViewById(R.id.b_address);
        bac_image.setOnClickListener(this);
        button01.setOnClickListener(this);
        button02.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.jian:
                finish();
               break;
            case R.id.b_sou:
                String s=editText.getText().toString();
                break;
            case R.id.b_address:
                homePresenter=new HomePresenter(this);
                homePresenter.getAddress();
                break;
        }
    }
    @Override
    public void getAddress(final AddressBean addressBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AddressBean.DataBean data = addressBean.getData();
                String address = data.getAddress();
                Toast.makeText(AddressActivity.this, address, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddressActivity.this, MainActivity.class);
                intent.putExtra("address", address);
                startActivity(intent);
            }
        });
    }
}
