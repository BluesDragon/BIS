package com.yangmiao.bis.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.test.greendao.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class UpgradeOpenhelper extends DaoMaster.DevOpenHelper {

    public UpgradeOpenhelper(Context context, String name) {
        super(context, name);
    }

    public UpgradeOpenhelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
//        super.onUpgrade(db, oldVersion, newVersion);
//        LogUtil.d("UpgradeOpenhelper-->onUpgrade-->oldVersion:" + oldVersion + "-->newVersion:" + newVersion);
        switch (oldVersion) {
            case 1:
//                db.execSQL("ALTER TABLE " + AccountBookDao.TABLENAME + " ADD COLUMN " + IMetaData.AccountBookColumns.COLUMNS_ACCOUNT_BOOK_TYPE);
                break;
        }
    }
}
