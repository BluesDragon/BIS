package com.yangmiao.bis.fragment;

import android.support.v4.app.Fragment;

import com.yangmiao.bis.util.LogUtil;

public abstract class BaseFragment extends Fragment {

    public abstract void onSelected();

    protected void log(String log) {
        LogUtil.d(getClass().getSimpleName() + "-->" + log);
    }
}
