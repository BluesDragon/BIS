package com.yangmiao.bis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.yangmiao.bis.util.DeviceUtil;

/**
 * 公共的Base页面，功能：<br/>
 * 1. 适配沉浸式风格。<br/>
 */
public class CommonBaseActivity extends AppCompatActivity {

    /**
     * 状态栏的view
     */
    protected View mStatusBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (canFullScreen()) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (canFullScreen()) {
            ViewGroup layout = getParentLayout();
            // 这里用这种方式，避免布局中有merge标签，出现无法inflate的情况。
            LayoutInflater.from(this).inflate(layoutResID, layout, true);
            super.setContentView(layout);
        } else {
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void setContentView(View view) {
        if (canFullScreen()) {
            ViewGroup layout = getParentLayout();
            layout.addView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            super.setContentView(layout);
        } else {
            super.setContentView(view);
        }
    }

    private ViewGroup getParentLayout() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        mStatusBar = new View(this);
        setStatusBarColor(getResources().getColor(R.color.color_home_bg));
        layout.addView(mStatusBar, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DeviceUtil.getStatusBarHeight(this)));
        return layout;
    }

    /**
     * 是否可以开启沉浸式风格
     *
     * @return
     */
    protected boolean canFullScreen() {
        return DeviceUtil.canFullScreen();
    }

    /**
     * 设置状态栏颜色
     *
     * @param color 颜色值
     */
    protected void setStatusBarColor(int color) {
        if (mStatusBar != null) {
            mStatusBar.setBackgroundColor(color);
        }
    }

    /**
     * 获取状态栏的view
     *
     * @return
     */
    protected View getStatusBar() {
        return mStatusBar;
    }

}
