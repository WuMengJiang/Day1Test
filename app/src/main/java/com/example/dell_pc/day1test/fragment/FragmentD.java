package com.example.dell_pc.day1test.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dell_pc.day1test.R;
import com.example.dell_pc.day1test.adapter.MyAdapter4;
import com.example.dell_pc.day1test.adapter.MyAdapter5;
import com.example.dell_pc.day1test.bean.Stu;
import com.example.dell_pc.day1test.utils.CallBack;
import com.example.dell_pc.day1test.utils.RetrofitUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentD extends Fragment {


    private RecyclerView rc;
    private Banner ban;
    private ArrayList<Stu.NewslistBean> list;
    private MyAdapter4 adapter4;
    private RecyclerView rc2;
    private ArrayList<Stu.NewslistBean> arrayList;
    private MyAdapter5 adapter5;

    public FragmentD() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_d, container, false);
        initView(inflate);
        initNews();
        return inflate;
    }

    private void initNews() {
        RetrofitUtils.getHttpUtils("http://api.tianapi.com/"). doGet("wxnew?key=52b7ec3471ac3bec6846577e79f20e4c&num=20&page=10", new CallBack<Stu>() {
            @Override
            public void onSuccess(Stu o) {
                arrayList.addAll(o.getNewslist());
                adapter5.notifyDataSetChanged();
                list.addAll(o.getNewslist());
                adapter4.notifyDataSetChanged();
            }

            @Override
            public void onError(String s) {

            }
        });
    }

    private void initView(View inflate) {
        ban = (Banner) inflate.findViewById(R.id.ban);
        rc = (RecyclerView) inflate.findViewById(R.id.rc);
        rc2 = (RecyclerView) inflate.findViewById(R.id.rc2);

        inio();
        initData();
        inioData();
    }

    private void inioData() {
        rc2.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<>();
        adapter5 = new MyAdapter5(arrayList, getContext());
        rc2.setAdapter(adapter5);

    }

    private void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rc.setLayoutManager(manager);
        list = new ArrayList<>();
        adapter4 = new MyAdapter4(list, getContext());
        rc.setAdapter(adapter4);

    }

    private void inio() {
        ArrayList<String> bann = new ArrayList<>();
        bann.add("http://03.imgmini.eastday.com/mobile/20171109/20171109180455_d41d8cd98f00b204e9800998ecf8427e_5_mwpm_03200403.jpg");
        bann.add("http://03.imgmini.eastday.com/mobile/20171109/20171109180455_d41d8cd98f00b204e9800998ecf8427e_3_mwpm_03200403.jpg");
        bann.add("http://01.imgmini.eastday.com/mobile/20171109/20171109180324_8550569f222233a015e1cd7d70d2f2d4_2_mwpm_03200403.jpg");
        ban.setImages(bann).setImageLoader(new Image()).start();
    }

    class Image extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
