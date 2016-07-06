package com.yangmiao.bis.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangmiao.bis.LoginActivity;
import com.yangmiao.bis.R;
import com.yangmiao.bis.db.login.LoginContentProvider;
import com.yangmiao.bis.util.LogUtil;
import com.yangmiao.bis.util.ViewScaleInjector;

public class SettingFragment extends Fragment implements View.OnClickListener {

    private TextView setting_btn_logout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        log("onCreateView");
        View view = inflater.inflate(R.layout.fragment_setting, null);
        setting_btn_logout = (TextView) view.findViewById(R.id.setting_btn_logout);
        setting_btn_logout.setOnClickListener(this);
        ViewScaleInjector.injectClickToBeSmallerIntoView(setting_btn_logout);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_btn_logout:
                LoginContentProvider.logout(getActivity());
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }

    private void log(String log) {
        LogUtil.d("SettingFragment-->" + log);
    }

}
