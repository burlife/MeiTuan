package com.bawi.zmt.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawi.zmt.R;
import com.bawi.zmt.actiivty.LoginActivity;

/**
 * Created by 1 on 2018/7/12.
 */

public class Myfragment extends Fragment{
    private TextView textView;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.my_fragment,null);
         initViews();
        return view;
    }

    private void initViews() {
        textView=view.findViewById(R.id.t_tiao);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent=new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}
