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

import com.test.greendao.Account;
import com.yangmiao.bis.db.AccountProvider;
import com.yangmiao.bis.db.UserProvider;
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
                    if (UserProvider.isLogin(WelcomActivity.this)) {
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

    private void initData() {
        if (!SpUtils.getBoolean(this, INIT_DATA, INIT, false)) {
            SpUtils.putBoolean(this, INIT_DATA, INIT, true);
            initAccountData();
        }
        isDataInitFinish = true;
        doFinish();
    }

    private void initAccountData() {
        AccountProvider.insertAccount(this, getAccount("杨淼", "女", "18600390104", "北京市", "中国银行",
                AccountProvider.AssetsType_OTHER, AccountProvider.ConsumerGrade_NORMAL, "110228198805235413", "null", "中国银行北京分行", 100));
        AccountProvider.insertAccount(this, getAccount("杨三水", "女", "18600390104", "北京市", "中国银行",
                AccountProvider.AssetsType_OTHER, AccountProvider.ConsumerGrade_VIP, "11022819880523541X", "null", "中国银行北京分行", 465));
        AccountProvider.insertAccount(this, getAccount("yangmiao", "女", "18600390104", "北京市", "中国银行",
                AccountProvider.AssetsType_OTHER, AccountProvider.ConsumerGrade_SIHANG, "110228198805235411", "null", "中国银行北京分行", 13215));
        AccountProvider.insertAccount(this, getAccount("大杨淼", "女", "18600390104", "北京市", "中国银行",
                AccountProvider.AssetsType_OTHER, AccountProvider.ConsumerGrade_CAIFU, "110228198805235411", "null", "中国银行北京分行", 132));
    }

    private Account getAccount(String name, String sex, String tel, String address, String company, int assetsType, int consumerGrade, String cardId, String flag, String attribution, int integral) {
        Account account = new Account();
        account.setName(name);
        account.setSex(sex);
        account.setTel(tel);
        account.setAddress(address);
        account.setCompany(company);
        account.setAssets_type(assetsType);
        account.setConsumer_grade(consumerGrade);
        account.setCard_id(cardId);
        account.setFlag(flag);
        account.setAttribution(attribution);
        account.setIntegral(integral);
        return account;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.removeMessages(FINISH);
        if (objectAnimator == null) {
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

    private void doFinish() {
        if (isAnimFinish && isDataInitFinish) {
            mHandler.removeMessages(FINISH);
            mHandler.sendEmptyMessage(FINISH);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeMessages(FINISH);
        if (objectAnimator != null) {
            objectAnimator.cancel();
            objectAnimator = null;
        }
    }
}
