package com.yangmiao.bis;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.yangmiao.bis.adapter.MyAdapter;
import com.yangmiao.bis.fragment.HomeFragment;
import com.yangmiao.bis.fragment.IntegralFragment;
import com.yangmiao.bis.fragment.SearchFragment;
import com.yangmiao.bis.fragment.SettingFragment;
import com.yangmiao.bis.model.HomePageInfo;
import com.yangmiao.bis.springindicator.SpringIndicator;
import com.yangmiao.bis.springindicator.viewpager.ScrollerViewPager;
import com.yangmiao.bis.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ScrollerViewPager viewPager;
    private MyAdapter myAdapter;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<HomePageInfo> dataList = new ArrayList<>();

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
        if (main_title_text != null) {
            main_title_text.setText(dataList.get(0).title);
        }
    }

    private void setListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (main_title_text != null) {
                    main_title_text.setText(dataList.get(position).title);
                }
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
        dataList.add(new HomePageInfo("首页", "首页"));
        dataList.add(new HomePageInfo("查询", "查询"));
        dataList.add(new HomePageInfo("积分", "积分"));
        dataList.add(new HomePageInfo("设置", "设置"));
    }

    private void log(String log) {
        LogUtil.d("MainActivity-->" + log);
    }
}
