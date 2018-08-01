package com.bawi.zmt.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawi.zmt.R;
import com.bawi.zmt.bean.DianCan;

import java.util.List;

public class DianCanAdapter extends RecyclerView.Adapter<DianCanAdapter.TextHolder> {
    private Context context;
    private List<DianCan.DataBean> list;
    public DianCanAdapter(Context context, List<DianCan.DataBean> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public TextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.diancan_layout, null);
        return new TextHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextHolder holder, int position) {
        holder.diancan_name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TextHolder extends RecyclerView.ViewHolder {

        private final TextView diancan_name;

        public TextHolder(View itemView) {
            super(itemView);
            diancan_name = itemView.findViewById(R.id.diancan_name);
        }
    }
}
