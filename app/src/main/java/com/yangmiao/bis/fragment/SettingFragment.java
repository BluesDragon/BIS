package com.yangmiao.bis.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangmiao.bis.LoginActivity;
import com.yangmiao.bis.R;
import com.yangmiao.bis.db.login.LoginContentProvider;
import com.yangmiao.bis.util.ViewScaleInjector;

public class SettingFragment extends BaseFragment implements View.OnClickListener {

    private TextView setting_btn_logout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
                LoginActivity.startLoginActivity(getActivity());
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onSelected() {

    }


}
