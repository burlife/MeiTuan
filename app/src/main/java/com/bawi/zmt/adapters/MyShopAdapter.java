package com.bawi.zmt.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawi.zmt.R;
import com.bawi.zmt.bean.Sousuo;
import com.bumptech.glide.Glide;

import java.util.List;

public class MyShopAdapter extends RecyclerView.Adapter<MyShopAdapter.TextView1> {
    private Context context;
    private List<Sousuo.DataBean> list;
    public MyShopAdapter(Context context, List<Sousuo.DataBean> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public TextView1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shop_layout, null);
        return new TextView1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextView1 holder, final int position) {
        Glide.with(context).load(list.get(position).getPic_url()).into(holder.pic_url);
        holder.name.setText(list.get(position).getName());
        holder.delivery_time_tip.setText(list.get(position).getDelivery_time_tip());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onItemClick(
                            list.get(position).getId()
                    );
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TextView1 extends RecyclerView.ViewHolder {

        private final ImageView pic_url;
        private final TextView name;
        private final TextView delivery_time_tip;

        public TextView1(View itemView) {
            super(itemView);
            pic_url = itemView.findViewById(R.id.pic_url);
            name = itemView.findViewById(R.id.name1);
            delivery_time_tip = itemView.findViewById(R.id.delivery_time_tip);
        }
    }

    private ItemViewClickListener clickListener;

    public void setClickListener(ItemViewClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ItemViewClickListener {
        void onItemClick(String id);
    }
       
}
