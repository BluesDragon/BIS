package com.yangmiao.bis.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.greendao.IMetaData;
import com.test.greendao.AccountDao;
import com.test.greendao.DaoMaster;
import com.test.greendao.DaoSession;
import com.test.greendao.LoginDao;

public class DbManager {

    private static DbManager sInstance = null;

    private DaoSession daoSession;

    private DbManager() {
    }

    public static synchronized DbManager getInstance() {
        if (sInstance == null) {
            synchronized (DbManager.class) {
                sInstance = new DbManager();
            }
        }
        return sInstance;
    }

    public void init(Context context) {
        if (context == null)
            return;
        UpgradeOpenhelper mOpenHelper = new UpgradeOpenhelper(context.getApplicationContext(), IMetaData.DB_NAME, null);
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public LoginDao getLoginDao() {
        if (daoSession != null) {
            return daoSession.getLoginDao();
        }
        return null;
    }

    public AccountDao getAccountDao() {
        if (daoSession != null) {
            return daoSession.getAccountDao();
        }
        return null;
    }

}
