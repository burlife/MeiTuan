package com.bawi.zmt.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawi.zmt.R;
import com.bawi.zmt.actiivty.CaiActivity;
import com.bawi.zmt.bean.ProductListBean;

import java.util.List;

/**
 * Created by 1 on 2018/7/17.
 */

public class MyGridViewPager extends BaseAdapter{
    private List<ProductListBean> listData;
    private LayoutInflater inflater;
    private Context context;
    private int mIndex;//页数下标，表示第几页，从0开始
    private int mPagerSize;//每页显示的最大数量

   /* public MyGridViewPager(List<ProductListBean> listData, LayoutInflater inflater, Context context, int mIndex, int mPagerSize) {
        this.listData = listData;
        this.inflater = inflater;
        this.context = context;
        this.mIndex = mIndex;
        this.mPagerSize = mPagerSize;
    }*/

    public MyGridViewPager(FragmentActivity activity, List<ProductListBean> listDatas, int i, int mPageSize) {
        this.listData = listDatas;
        this.context = activity;
        this.mIndex = i;
        this.mPagerSize = mPageSize;
    }

    /**
     * 先判断数据集的大小是否足够显示满本页？listData.size() > (mIndex + 1)*mPagerSize
     * 如果满足，则此页就显示最大数量mPagerSize的个数
     * 如果不够显示每页的最大数量，那么剩下几个就显示几个 (listData.size() - mIndex*mPagerSize)
     */
    @Override
    public int getCount() {
        return listData.size() > (mIndex + 1)*mPagerSize ? mPagerSize : (listData.size() - mIndex*mPagerSize);
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position + mIndex * mPagerSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPagerSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_layout,parent,false);
            holder = new ViewHolder();
            holder.proName = (TextView) convertView.findViewById(R.id.proName);
            holder.imgUrl = (ImageView) convertView.findViewById(R.id.imgUrl);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //重新确定position（因为拿到的是总的数据源，数据源是分页加载到每页的GridView上的，为了确保能正确的点对不同页上的item）
        final int pos = position + mIndex*mPagerSize;//假设mPagerSize=8，假如点击的是第二页（即mIndex=1）上的第二个位置item(position=1),那么这个item的实际位置就是pos=9
        ProductListBean bean = listData.get(pos);
        holder.proName.setText(bean.getProName());
        holder.imgUrl.setImageResource(bean.getImgUrl());
        //添加item监听
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String proName = listData.get(pos).getProName();
                Toast.makeText(context,"你点击了 "+proName,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, CaiActivity.class);
                intent.putExtra("proName",proName);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        private TextView proName;
        private ImageView imgUrl;
    }
}
