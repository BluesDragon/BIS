package com.yangmiao.bis;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.yangmiao.bis.adapter.MyAdapter;
import com.yangmiao.bis.fragment.HomeFragment;
import com.yangmiao.bis.fragment.IntegralFragment;
import com.yangmiao.bis.fragment.SearchFragment;
import com.yangmiao.bis.fragment.SettingFragment;
import com.yangmiao.bis.model.HomePageInfo;
import com.yangmiao.bis.springindicator.SpringIndicator;
import com.yangmiao.bis.springindicator.viewpager.ScrollerViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ScrollerViewPager viewPager;
    private MyAdapter myAdapter;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<HomePageInfo> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        initData();

        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);

        fragmentList.add(new HomeFragment());
        fragmentList.add(new SearchFragment());
        fragmentList.add(new IntegralFragment());
        fragmentList.add(new SettingFragment());

        myAdapter = new MyAdapter(getSupportFragmentManager(), fragmentList, dataList);

        viewPager.setAdapter(myAdapter);
        viewPager.fixScrollSpeed();

        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        springIndicator.setViewPager(viewPager);
    }

    private void initData(){
        dataList.add(new HomePageInfo("首页", "首页"));
        dataList.add(new HomePageInfo("查询", "查询"));
        dataList.add(new HomePageInfo("积分", "积分"));
        dataList.add(new HomePageInfo("设置", "设置"));
    }
}
