package com.bawi.zmt.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawi.zmt.R;
import com.bawi.zmt.bean.CanGuanBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 1 on 2018/7/21.
 */

public class ListAdapter extends BaseAdapter{
    private Context context;
    private List<CanGuanBean.DataBean>list;

    public ListAdapter(Context context, List<CanGuanBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.item_layout,null);
            holder=new ViewHolder();
            holder.t01=convertView.findViewById(R.id.m_name);
            holder. t02=convertView.findViewById(R.id.m_xiaoshou);
            holder.t03=convertView.findViewById(R.id.m_fenzhong);
            holder.t04=convertView.findViewById(R.id.m_qianmi);
            holder.t05=convertView.findViewById(R.id.m_qi);
            holder.t06=convertView.findViewById(R.id.m_pei);
            holder.t07=convertView.findViewById(R.id.m_renjun);
            holder.imageView=convertView.findViewById(R.id.m_image);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.t01.setText(list.get(position).getName());
        holder.t02.setText(list.get(position).getMonth_sales_tip());
        holder.t03.setText(list.get(position).getDelivery_time_tip());
        holder.t04.setText(list.get(position).getDistance());
        holder.t05.setText(list.get(position).getMin_price_tip());
        holder.t06.setText(list.get(position).getShipping_fee_tip());
        holder.t07.setText(list.get(position).getAverage_price_tip());
        String pic=list.get(position).getPic_url();
        Glide.with(context).load(pic).into(holder.imageView);

        return convertView;
    }
      class ViewHolder{
          private TextView t01;
          private TextView t02;
          private TextView t03;
          private TextView t04;
          private TextView t05;
          private TextView t06;
          private TextView t07;
          private ImageView imageView;
      }
}
