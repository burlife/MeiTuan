package com.bawi.zmt;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bawi.zmt.adapters.Fragment01;
import com.bawi.zmt.fragment.Fragment_home;
import com.bawi.zmt.fragment.Myfragment;
import com.bawi.zmt.fragment.OrderFragment;
import com.bawi.zmt.fragment.ShouYeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private RadioGroup radioGroup;
private ViewPager viewPager;
private List<Fragment>fs=new ArrayList<>();
private Fragment01 fragment01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragments();
    }

    private void initFragments() {
     Fragment f_sy=new Fragment_home();
     Fragment f_dd=new OrderFragment();
     Fragment f_my=new Myfragment();
     fs.add(f_sy);
     fs.add(f_dd);
     fs.add(f_my);

     fragment01=new Fragment01(getSupportFragmentManager(),fs);
     viewPager.setAdapter(fragment01);
     viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

         }

         @Override
         public void onPageSelected(int position) {
             switch (position){
                 case 0:
                      radioGroup.check(R.id.one);
                     break;
                 case  1:
                     radioGroup.check(R.id.two);
                     break;
                 case 2:
                    radioGroup.check(R.id.three);
                     break;
             }
         }

         @Override
         public void onPageScrollStateChanged(int state) {

         }
     });

    }

    private void initView() {
     viewPager=findViewById(R.id.main_vp);
     radioGroup=findViewById(R.id.rg_rg);
        radioGroup.check(R.id.one);
      radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup radioGroup, int checked) {
              switch (checked){
                  case R.id.one:
                    viewPager.setCurrentItem(0);
                      break;
                  case  R.id.two:
                      viewPager.setCurrentItem(1);
                      break;
                  case  R.id.three:
                      viewPager.setCurrentItem(2);

                      break;
              }
          }
      });
    }
}
