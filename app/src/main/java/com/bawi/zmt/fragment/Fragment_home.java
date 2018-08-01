package com.bawi.zmt.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bawi.zmt.R;
import com.bawi.zmt.actiivty.AddressActivity;
import com.bawi.zmt.actiivty.SearchActivity;
import com.bawi.zmt.actiivty.ShopActivity;
import com.bawi.zmt.actiivty.XiangQingActivity;
import com.bawi.zmt.adapters.MyGridViewPager;
import com.bawi.zmt.adapters.MyViewPagerAdapter;
import com.bawi.zmt.adapters.RecyAdapter;
import com.bawi.zmt.bean.NewsBean;
import com.bawi.zmt.bean.ProductListBean;
import com.bawi.zmt.presenter.HomePresenter;
import com.bawi.zmt.view.IhomeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.*;

/**
 * Created by 1 on 2018/7/17.
 */

public class Fragment_home extends Fragment implements ViewPager.OnPageChangeListener ,IhomeView {
    private PullToRefreshScrollView pull;
    private RecyclerView recyclerView;
    private ViewPager viewpager;
    private int totalPage;//总的页数
    private int mPageSize = 8;//每页显示的最大数量
    private List<ProductListBean> listDatas;//总的数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private int currentPage;//当前页
    private HomePresenter homePresenter;
    private List<NewsBean.DataBean> data=new ArrayList<>();
    private RecyAdapter recyAdapter;
    private int page=0;
   private EditText editText;
    private static final int CODE_UPDATE_HOME_LIST = 1;
    private String[] proName = {"美食", "美团超市", "生鲜果蔬", "下午茶", "正餐优选", "汉堡披萨",
            "跑腿代购", "快餐简餐", "地方菜", "炸鸡", "免配送费"};
    private int[] image = new int[]{R.drawable.meishi, R.drawable.chaoshi, R.drawable.guoshu, R.drawable.xiwucha, R.drawable.zhengcan, R.drawable.hanbao,
            R.drawable.paotui, R.drawable.kuaican, R.drawable.difangcai, R.drawable.zhaji, R.drawable.mainfei};
    private View view;
    private int imageBackground;
    private static final String TAG = "Fragment_home===";
  private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_UPDATE_HOME_LIST:
                   // NewsBean bean = (NewsBean) msg.obj;
                   // data.addAll(bean.getData());
                    recyAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.shouye_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iniViews();
        //模拟数据源
        setDatas();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        //总的页数，取整（这里有三种类型：Math.ceil(3.5)=4:向上取整，只要有小数都+1  Math.floor(3.5)=3：向下取整  Math.round(3.5)=4:四舍五入）
        totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) inflater.inflate(R.layout.grid_layout, viewpager, false);
            gridView.setAdapter(new MyGridViewPager(getActivity(), listDatas, i, mPageSize));
            //添加item点击监听
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + currentPage*mPageSize;
                    Log.i("TAG","position的值为："+position + "-->pos的值为："+pos);
                    Toast.makeText(getActivity(),"你点击了 "+listDatas.get(pos).getProName(),Toast.LENGTH_SHORT).show();
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        viewpager.setAdapter(new MyViewPagerAdapter(viewPagerList));


        //设置ViewPager滑动监听
        viewpager.addOnPageChangeListener(this);
    }

    private void setDatas() {
        listDatas = new ArrayList<>();
        for (int i = 0; i < proName.length; i++) {
            listDatas.add(new ProductListBean(proName[i], image[i]));
        }
    }

    private void iniViews() {
        homePresenter = new HomePresenter(this);
        homePresenter.getDatas(page);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        recyclerView = view.findViewById(R.id.recy_view);
       pull=view.findViewById(R.id.pull_to_refresh_scroll);
       editText=view.findViewById(R.id.t_ed01);
       editText.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(getContext(), AddressActivity.class);
               startActivity(intent);
           }
       });

       editText=view.findViewById(R.id.t_ed02);
       //点击地址进行跳转
       editText.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(getActivity(), ShopActivity.class);
               startActivity(intent);
           }
       });
        Intent intent=getActivity().getIntent();
        String adrress=intent.getStringExtra("address");
        editText.setText(adrress);


       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyAdapter=new RecyAdapter(getContext(),data);
        recyclerView.setAdapter(recyAdapter);
       pull.setMode(PullToRefreshScrollView.Mode.BOTH);
       pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
           @Override
           public void onPullDownToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
               page=1;
                homePresenter.getDatas(page);
                pull.onRefreshComplete();

           }

           @Override
           public void onPullUpToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
               page++;
               homePresenter.getDatas(page);
               pull.onRefreshComplete();
           }
       });
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    @Override
    public void callBackSuccess(final NewsBean newsBean) {
       getActivity().runOnUiThread(new Runnable() {
           @Override
           public void run() {
               List<NewsBean.DataBean> list=newsBean.getData();
               Log.i(TAG,"--曹付奎"+newsBean.getData().toString());
               RecyAdapter recyAdapter=new RecyAdapter(getContext(),list);
               recyAdapter.setCallBackListener(new RecyAdapter.CallBackListener() {
                   @Override
                   public void callBack(String id) {
                       Intent intent=new Intent(getActivity(),XiangQingActivity.class);
                       intent.putExtra("id",id);
                       startActivity(intent);
                   }
               });
           }
       });
       data.addAll(newsBean.getData());
        mHandler.sendEmptyMessage(CODE_UPDATE_HOME_LIST);

      /* recyclerView.setAdapter(recyAdapter);
        pull.onRefreshComplete();*/
    }

    @Override
    public void callBackFailure(int code) {
    }

}
