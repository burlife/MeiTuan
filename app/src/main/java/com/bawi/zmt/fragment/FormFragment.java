package com.bawi.zmt.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawi.zmt.R;
import com.bawi.zmt.actiivty.LoginActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;


import java.util.Map;

public class FormFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ImageView dlzc_dlzc;

    public FormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_form, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dlzc_dlzc = view.findViewById(R.id.dlzc_dlzc);
        dlzc_dlzc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(),LoginActivity.class));
    }
}