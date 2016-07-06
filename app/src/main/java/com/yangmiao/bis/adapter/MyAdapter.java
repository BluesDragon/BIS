package com.yangmiao.bis.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yangmiao.bis.fragment.BaseFragment;
import com.yangmiao.bis.model.HomePageInfo;

import java.util.List;

public class MyAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragmentList;
    private List<HomePageInfo> dataList;

    public MyAdapter(FragmentManager fm, List<BaseFragment> fragmentList, List<HomePageInfo> dataList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        if (fragmentList != null) {
            return fragmentList.size();
        }
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragmentList != null) {
            return fragmentList.get(position);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (dataList != null && dataList.size() > position) {
            return dataList.get(position).title;
        }
        return super.getPageTitle(position);
    }
}
