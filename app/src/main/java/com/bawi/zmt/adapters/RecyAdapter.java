package com.bawi.zmt.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawi.zmt.R;
import com.bawi.zmt.actiivty.XiangQingActivity;
import com.bawi.zmt.bean.NewsBean;
import com.bawi.zmt.model.ModelIHome;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 1 on 2018/7/17.
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.TextHolder>{
    private Context mContext;
    private List<NewsBean.DataBean>list;
   //构造函数


    public RecyAdapter(Context mContext, List<NewsBean.DataBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    //创建视图
    @Override
    public TextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //获取子布局
        LayoutInflater inflater=LayoutInflater.from(mContext);
         View view=inflater.inflate(R.layout.item_layout,parent,false);
        return new TextHolder(mContext,view);
    }
   //绑定数据额和视图
    @Override
    public void onBindViewHolder(TextHolder holder, final int position) {
        String pic=list.get(position).getPic_url();
        String text1=list.get(position).getName();
        String text2=list.get(position).getMonth_sales_tip();
        String text3=list.get(position).getDelivery_time_tip();
        String text4=list.get(position).getDistance();
        String text5=list.get(position).getMin_price_tip();
        String text6=list.get(position).getShipping_fee_tip();
        String text7=list.get(position).getAverage_price_tip();
             holder.bindText(pic,text1,text2,text3,text4,text5,text6,text7);
             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     String id=list.get(position).get_id();
                    Intent intent=new Intent(mContext,XiangQingActivity.class);
                     intent.putExtra("id" ,id);
                    mContext.startActivity(intent);
                   /*callBackListener.callBack(

                             list.get(position).getId()
                     );
                     */

                 }
             });
    }
  //条目数量
    @Override
    public int getItemCount() {

        return list.size();
    }

    //内部类
    public static  class  TextHolder extends RecyclerView.ViewHolder {
        private TextView t01;
        private TextView t02;
        private TextView t03;
        private TextView t04;
        private TextView t05;
        private TextView t06;
        private TextView t07;
         private ImageView imageView;
        private Context context;


        public TextHolder(Context mcontext,View itemView) {
            super(itemView);
            this.context=mcontext;
              t01=itemView.findViewById(R.id.m_name);
            t02=itemView.findViewById(R.id.m_xiaoshou);
            t03=itemView.findViewById(R.id.m_fenzhong);
            t04=itemView.findViewById(R.id.m_qianmi);
            t05=itemView.findViewById(R.id.m_qi);
            t06=itemView.findViewById(R.id.m_pei);
            t07=itemView.findViewById(R.id.m_renjun);
         imageView=itemView.findViewById(R.id.m_image);



        }


       public void bindText(String pic, String text1, String text2, String text3, String text4, String text5, String text6, String text7) {
           t01.setText(text1);
           t02.setText(text2);
           t03.setText(text3);
           t04.setText(text4);
           t05.setText(text5);
           t07.setText(text7);
           t06.setText(text6);

           // Glide.with(context).load(image).into(imageView);
           Glide.with(context).load(pic).into(imageView);
        }

    }
    private CallBackListener callBackListener;

    public void setCallBackListener(CallBackListener callBackListener) {
        this.callBackListener = callBackListener;
    }

    public interface CallBackListener{
        void callBack(String id);
    }

}
