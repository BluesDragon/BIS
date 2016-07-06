package com.yangmiao.bis.util;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.yangmiao.bis.R;


public class ViewScaleInjector {

    private static OnTouchListener sTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Context cxt = v.getContext().getApplicationContext();
            Animation anim = null;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    anim = AnimationUtils.loadAnimation(cxt, R.anim.view_scale_down);
                    anim.setFillAfter(true);
                    v.startAnimation(anim);
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    anim = AnimationUtils.loadAnimation(cxt, R.anim.view_scale_up);
                    anim.setFillAfter(true);
                    v.startAnimation(anim);
                    break;
            }
            return false;
        }

    };

    private static OnTouchListener sReverseTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Context cxt = v.getContext().getApplicationContext();
            Animation anim = null;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    anim = AnimationUtils.loadAnimation(cxt, R.anim.view_scale_up);
                    anim.setFillAfter(true);
                    v.startAnimation(anim);
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    anim = AnimationUtils.loadAnimation(cxt, R.anim.view_scale_down);
                    anim.setFillAfter(true);
                    v.startAnimation(anim);
                    break;
            }
            return false;
        }

    };


    public static void injectClickToBeSmallerIntoView(View view) {
        if (view != null)
            view.setOnTouchListener(sTouchListener);
    }


    public static void injectClickToBeBiggerIntoView(View view) {
        if (view != null)
            view.setOnTouchListener(sReverseTouchListener);
    }
}
