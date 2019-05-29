package com.example.dell_pc.day1test;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.dell_pc.day1test.fragment.CreationFragment;
import com.example.dell_pc.day1test.fragment.FragmentD;
import com.example.dell_pc.day1test.fragment.HomeFragment;
import com.example.dell_pc.day1test.fragment.MineFragment;
import com.example.dell_pc.day1test.fragment.PreferenceFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private FrameLayout fl;
    private HomeFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        fl = (FrameLayout) findViewById(R.id.fl);


        tab.addTab(tab.newTab().setIcon(R.drawable.tab_one).setText("首页"));
        tab.addTab(tab.newTab().setIcon(R.drawable.tab_two).setText("定制"));
        tab.addTab(tab.newTab().setIcon(R.drawable.tab_there).setText("当地玩乐"));
        tab.addTab(tab.newTab().setIcon(R.drawable.tab_four).setText("我的"));

        fragment = new HomeFragment();
        FragmentIntent(fragment);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position){
                    case 0:
                        FragmentIntent(fragment);
                        break;

                    case 1:
                        CreationFragment creationFragment = new CreationFragment();
                        FragmentIntent(creationFragment);
                        break;

                    case 2:
                        FragmentD fragmentD = new FragmentD();
                        FragmentIntent(fragmentD);
                        break;
                    case 3:
                        MineFragment mineFragment = new MineFragment();
                        FragmentIntent(mineFragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    private void FragmentIntent(Fragment fragment) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fl,fragment);
            transaction.commit();
        }
}
