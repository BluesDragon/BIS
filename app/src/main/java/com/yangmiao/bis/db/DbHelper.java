package com.yangmiao.bis.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yangmiao.bis.db.IProivderMetaData;

public class DbHelper extends SQLiteOpenHelper implements IProivderMetaData {

    private static final String TAG = "DbHelper";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLESQL_LOGIN = "create table if not exists "
                + LoginMetaData.TABLE_NAME + " ("
                + LoginMetaData.USERNAME + " varchar,"
                + LoginMetaData.PASSWORD + " varchar)";
        db.execSQL(TABLESQL_LOGIN);

        String TABLESQL_ACCOUNT = "create table if not exists "
                + LoginMetaData.TABLE_NAME + " ("
                + LoginMetaData.USERNAME + " varchar,"
                + LoginMetaData.PASSWORD + " varchar)";
        db.execSQL(TABLESQL_ACCOUNT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "DbHelper-->onUpgrade-->database from version " + oldVersion
                + " to " + newVersion + ", destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }
}