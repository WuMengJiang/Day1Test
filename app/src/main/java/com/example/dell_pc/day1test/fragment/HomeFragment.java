package com.example.dell_pc.day1test.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.dell_pc.day1test.BannerHost;
import com.example.dell_pc.day1test.R;
import com.example.dell_pc.day1test.adapter.GradViewAdapter;
import com.example.dell_pc.day1test.adapter.RecAdapter;
import com.example.dell_pc.day1test.bean.BeanHost;
import com.example.dell_pc.day1test.bean.GridViewBeans;
import com.example.dell_pc.day1test.net.MyServer;
import com.example.dell_pc.day1test.utils.CallBack;
import com.example.dell_pc.day1test.utils.RetrofitUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private Banner bann;
    private RecyclerView rlv;
    private ArrayList<BannerHost> hosts = new ArrayList<>();
    private ArrayList<BeanHost.NewslistBean> beans;
    private RecAdapter recAdapter;
    private GridView gl;
    private ArrayList<GridViewBeans> list;
    private GradViewAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        initData();
        initUrlData();
        return inflate;

    }

    private void initUrlData() {
        RetrofitUtils.getHttpUtils("http://api.tianapi.com/").doGet("wxnew?key=52b7ec3471ac3bec6846577e79f20e4c&num=20&page=20", new CallBack<BeanHost>() {
            private String TAG;

            @Override
            public void onSuccess(BeanHost o) {
                beans.addAll(o.getNewslist());
                recAdapter.setBeans(beans);
            }

            @Override
            public void onError(String s) {
                Log.i(TAG, "onError: "+s);
            }
        });
    }

    private void initData() {
        hosts = new ArrayList<BannerHost>();
        hosts.add(new BannerHost("http://03.imgmini.eastday.com/mobile/20171109/20171109180455_d41d8cd98f00b204e9800998ecf8427e_5_mwpm_03200403.jpg"));
        hosts.add(new BannerHost("http://03.imgmini.eastday.com/mobile/20171109/20171109180455_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg"));
        hosts.add(new BannerHost("http://01.imgmini.eastday.com/mobile/20171109/20171109180324_8550569f222233a015e1cd7d70d2f2d4_2_mwpm_03200403.jpg"));
        bann.setImages(hosts).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                BannerHost b = (BannerHost) path;
                Glide.with(getContext()).load(b.getUrl()).into(imageView);
            }
        }).start();



        ArrayList<GridViewBeans> drawables = new ArrayList<>();
        drawables.add(new GridViewBeans(R.drawable.icon_main_free_travel, "自由行"));
        drawables.add(new GridViewBeans(R.drawable.icon_main_airticket, "机票"));
        drawables.add(new GridViewBeans(R.drawable.icon_main_visa, "签证"));
        drawables.add(new GridViewBeans(R.drawable.icon_main_destination_travel, "自由气象团"));
        drawables.add(new GridViewBeans(R.drawable.icon_main_half_free, "半自由行"));
        drawables.add(new GridViewBeans(R.drawable.icon_main_hotel, "酒店"));
        drawables.add(new GridViewBeans(R.drawable.icon_main_ticket, "门票"));
        drawables.add(new GridViewBeans(R.drawable.icon_main_other_product, "其他"));
        list.addAll(drawables);
        adapter.notifyDataSetChanged();

    }

    private void initView(View inflate) {
        bann = (Banner) inflate.findViewById(R.id.bann);
        rlv = (RecyclerView) inflate.findViewById(R.id.rlv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayout.HORIZONTAL);
        rlv.setLayoutManager(layoutManager);
        rlv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.HORIZONTAL));



        beans = new ArrayList<>();
        recAdapter = new RecAdapter(getContext());
        rlv.setAdapter(recAdapter);


        gl = (GridView) inflate.findViewById(R.id.gl);
        list = new ArrayList<>();
        adapter = new GradViewAdapter(list, getContext());
        gl.setAdapter(adapter);
    }
}
