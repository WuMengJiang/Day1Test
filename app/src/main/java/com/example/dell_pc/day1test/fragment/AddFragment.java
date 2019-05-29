package com.example.dell_pc.day1test.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell_pc.day1test.R;
import com.example.dell_pc.day1test.adapter.RecAdapter2;
import com.example.dell_pc.day1test.bean.BeanUrl;
import com.example.dell_pc.day1test.net.MyServer;
import com.example.dell_pc.day1test.utils.ArrayCallBack;
import com.example.dell_pc.day1test.utils.CallBack;
import com.example.dell_pc.day1test.utils.RetrofitUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {


    private RecyclerView rlv;
    private ArrayList<BeanUrl.ResultBean> beans;
    private RecAdapter2 adapter2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_add, container, false);
        initView(inflate);
        return inflate;
    }


    private void initView(View inflate) {
        rlv = (RecyclerView) inflate.findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));

        RetrofitUtils.getHttpUtils("https://api.apiopen.top/").doGet("getJoke?page=1&count=20&type=video", new CallBack<BeanUrl>() {
            @Override
            public void onSuccess(BeanUrl o) {
                beans.addAll(o.getResult());
                adapter2.setBeans(beans);
            }


            @Override
            public void onError(String s) {

            }
        });
        beans = new ArrayList<>();
        adapter2 = new RecAdapter2(getContext());
        rlv.setAdapter(adapter2);

    }
}
