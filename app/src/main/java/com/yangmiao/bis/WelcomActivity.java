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

import com.yangmiao.bis.db.login.LoginContentProvider;

public class WelcomActivity extends Activity {

    private static final int FINISH = 1;

    private ImageView welcome_img;
    private ObjectAnimator objectAnimator;

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
                    mHandler.removeMessages(FINISH);
                    mHandler.sendEmptyMessage(FINISH);
                }
            });
            objectAnimator.start();
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
