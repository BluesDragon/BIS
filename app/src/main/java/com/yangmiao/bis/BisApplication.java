package com.yangmiao.bis;

import android.app.Application;

import com.yangmiao.bis.db.DbManager;

public class BisApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DbManager.getInstance().init(getApplicationContext());
    }
}
