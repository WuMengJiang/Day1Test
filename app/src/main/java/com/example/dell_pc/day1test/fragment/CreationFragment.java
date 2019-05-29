package com.example.dell_pc.day1test.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell_pc.day1test.R;
import com.example.dell_pc.day1test.adapter.FragmentAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreationFragment extends Fragment {


    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_creation, container, false);
        initView(inflate);
        return inflate;

    }

    private void initView(View inflate) {
        tab = (TabLayout) inflate.findViewById(R.id.tab);
        vp = (ViewPager) inflate.findViewById(R.id.vp);


        fragments = new ArrayList<>();
        strings = new ArrayList<>();
        strings.add("欧美");
        strings.add("东南亚");
        strings.add("海盗");
        strings.add("日韩 ");
        strings.add("澳新其他");


        for (int i = 0; i < strings.size(); i++) {
            AddFragment addFragment = new AddFragment();
            fragments.add(addFragment);
        }

        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragments, strings);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }
}
