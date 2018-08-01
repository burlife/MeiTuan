package com.bawi.zmt.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawi.zmt.R;
import com.bawi.zmt.adapters.DianCanAdapter;
import com.bawi.zmt.bean.DianCan;
import com.bawi.zmt.presenter.HomePresenter;
import com.bawi.zmt.view.IdianCanView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DianCaiFragment extends Fragment implements IdianCanView {


    private View view;
    private RecyclerView recycle_left;
    private RecyclerView recycle_right;
    private HomePresenter presenter;

    public DianCaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dian_cai, container, false);
        recycle_left = view.findViewById(R.id.recycle_left);
        recycle_right = view.findViewById(R.id.recycle_right);
        recycle_left.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recycle_right.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        String id = intent.getStringExtra("id");
        presenter = new HomePresenter(this);
        presenter.diancans(id);
    }

    @Override
    public void Diancans(final DianCan dianCan, String id) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<DianCan.DataBean> list = dianCan.getData();
                DianCanAdapter adapter = new DianCanAdapter(getActivity(), list);
                recycle_left.setAdapter(adapter);
            }
        });
    }
}
