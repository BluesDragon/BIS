package com.yangmiao.bis;

import android.app.Application;

import com.yangmiao.bis.db.account.AccountContentProvider;
import com.yangmiao.bis.model.AccountInfo;
import com.yangmiao.bis.util.SpUtils;

public class BisApplication extends Application {

    private static final String INIT_DATA = "init_data";
    private static final String INIT = "init";

    @Override
    public void onCreate() {
        super.onCreate();
        if (!SpUtils.getBoolean(this, INIT_DATA, INIT, false)) {
            SpUtils.putBoolean(this, INIT_DATA, INIT, true);
            initAccountData();
        }
    }

    private void initAccountData() {
        AccountContentProvider.insertAccount(this, new AccountInfo("叶国龙1", "男", "18600390104", "北京市", "中国银行",
                AccountInfo.AssetsType_OTHER, AccountInfo.ConsumerGrade_NORMAL, "110228198805235413", "null", "中国银行北京分行", 0));
        AccountContentProvider.insertAccount(this, new AccountInfo("叶国龙2", "男", "18600390104", "北京市", "中国银行",
                AccountInfo.AssetsType_OTHER, AccountInfo.ConsumerGrade_NORMAL, "110228198805235413", "null", "中国银行北京分行", 0));
        AccountContentProvider.insertAccount(this, new AccountInfo("叶国龙3", "男", "18600390104", "北京市", "中国银行",
                AccountInfo.AssetsType_OTHER, AccountInfo.ConsumerGrade_NORMAL, "110228198805235413", "null", "中国银行北京分行", 0));
        AccountContentProvider.insertAccount(this, new AccountInfo("叶国龙4", "男", "18600390104", "北京市", "中国银行",
                AccountInfo.AssetsType_OTHER, AccountInfo.ConsumerGrade_NORMAL, "110228198805235413", "null", "中国银行北京分行", 0));
    }

}
