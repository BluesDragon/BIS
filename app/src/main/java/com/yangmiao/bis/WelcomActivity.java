package com.yangmiao.bis;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.yangmiao.bis.db.account.AccountContentProvider;
import com.yangmiao.bis.db.login.LoginContentProvider;
import com.yangmiao.bis.model.AccountInfo;
import com.yangmiao.bis.util.SpUtils;

public class WelcomActivity extends Activity {

    private static final String INIT_DATA = "init_data";
    private static final String INIT = "init";

    private static final int FINISH = 1;

    private ImageView welcome_img;
    private ObjectAnimator objectAnimator;
    private boolean isAnimFinish = false;
    private boolean isDataInitFinish = false;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case FINISH:
                    if(LoginContentProvider.isLogin(WelcomActivity.this)){
                        startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(WelcomActivity.this, LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome_img = (ImageView) findViewById(R.id.welcome_img);
        initData();
    }

    private void initData(){
        if (!SpUtils.getBoolean(this, INIT_DATA, INIT, false)) {
            SpUtils.putBoolean(this, INIT_DATA, INIT, true);
            initAccountData();
        }
        isDataInitFinish = true;
        doFinish();
    }

    private void initAccountData() {
        AccountContentProvider.insertAccount(this, new AccountInfo("杨淼", "女", "18600390104", "北京市", "中国银行",
                AccountInfo.AssetsType_OTHER, AccountInfo.ConsumerGrade_NORMAL, "110228198805235413", "null", "中国银行北京分行", 100));
        AccountContentProvider.insertAccount(this, new AccountInfo("杨三水", "女", "18600390104", "北京市", "中国银行",
                AccountInfo.AssetsType_OTHER, AccountInfo.ConsumerGrade_VIP, "110228198805235413", "null", "中国银行北京分行", 465));
        AccountContentProvider.insertAccount(this, new AccountInfo("yangmiao", "女", "18600390104", "北京市", "中国银行",
                AccountInfo.AssetsType_OTHER, AccountInfo.ConsumerGrade_SIHANG, "110228198805235413", "null", "中国银行北京分行", 13215));
        AccountContentProvider.insertAccount(this, new AccountInfo("大杨淼", "女", "18600390104", "北京市", "中国银行",
                AccountInfo.AssetsType_OTHER, AccountInfo.ConsumerGrade_CAIFU, "110228198805235413", "null", "中国银行北京分行", 132));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.removeMessages(FINISH);
        if(objectAnimator == null){
            objectAnimator = ObjectAnimator.ofFloat(welcome_img, "alpha", 0f, 1.0f).setDuration(2000);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    isAnimFinish = true;
                    doFinish();
                }
            });
            objectAnimator.start();
        }
    }

    private void doFinish(){
        if(isAnimFinish && isDataInitFinish){
            mHandler.removeMessages(FINISH);
            mHandler.sendEmptyMessage(FINISH);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeMessages(FINISH);
        if(objectAnimator != null){
            objectAnimator.cancel();
            objectAnimator = null;
        }
    }
}
