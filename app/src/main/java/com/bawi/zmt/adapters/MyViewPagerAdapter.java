package com.bawi.zmt.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 1 on 2018/7/17.
 */

public class MyViewPagerAdapter extends PagerAdapter{
    private List<View>viewList;

    public MyViewPagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }
    /**
     *这个方法，是从ViewGroup中移出当前View
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
               container.removeView(viewList.get(position));
    }

    /**
     * 将当前View添加到ViewGroup容器中
     * 这个方法，return一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }
    /**
     *这个方法，是获取当前窗体界面数
     */
    @Override
    public int getCount() {
        return viewList !=null ?viewList.size():0;
    }
    /**
     *用于判断是否由对象生成界面
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
