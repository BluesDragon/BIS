package com.yangmiao.bis;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.yangmiao.bis.adapter.MyAdapter;
import com.yangmiao.bis.fragment.BaseFragment;
import com.yangmiao.bis.fragment.HomeFragment;
import com.yangmiao.bis.fragment.IntegralFragment;
import com.yangmiao.bis.fragment.SearchFragment;
import com.yangmiao.bis.fragment.SettingFragment;
import com.yangmiao.bis.model.FragmentItemInfo;
import com.yangmiao.bis.springindicator.SpringIndicator;
import com.yangmiao.bis.springindicator.viewpager.ScrollerViewPager;
import com.yangmiao.bis.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends CommonBaseActivity {

    private ScrollerViewPager viewPager;
    private MyAdapter myAdapter;

    private List<BaseFragment> fragmentList = new ArrayList<>();
    private List<FragmentItemInfo> dataList = new ArrayList<>();

    private TextView main_title_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initData();
        initView();
    }

    private void initView() {
        main_title_text = (TextView) findViewById(R.id.main_title_text);
        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        initViewPager();
        setListener();

        viewPager.setAdapter(myAdapter);
        viewPager.fixScrollSpeed();
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        springIndicator.setViewPager(viewPager);

        int defaultIndex = 0;
        fragmentList.get(defaultIndex).onSelected();
    }

    private void setListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                fragmentList.get(position).onSelected();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initViewPager() {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new SearchFragment());
        fragmentList.add(new IntegralFragment());
        fragmentList.add(new SettingFragment());

        myAdapter = new MyAdapter(getSupportFragmentManager(), fragmentList, dataList);


    }

    private void initData() {
        dataList.add(new FragmentItemInfo("首页", "首页"));
        dataList.add(new FragmentItemInfo("查询", "查询"));
        dataList.add(new FragmentItemInfo("积分", "积分"));
        dataList.add(new FragmentItemInfo("设置", "设置"));
    }

    private void log(String log) {
        LogUtil.d("MainActivity-->" + log);
    }
}
