package com.yangmiao.bis.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper implements IProivderMetaData {

    private static final String TAG = "DbHelper";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLESQL_LOGIN = "create table if not exists "
                + LoginColumns.TABLE_NAME + " ("
                + LoginColumns.COLUMNS_ID + " integer primary key,"
                + LoginColumns.COLUMNS_USERNAME + " varchar,"
                + LoginColumns.COLUMNS_PASSWORD + " varchar)";
        db.execSQL(TABLESQL_LOGIN);

        String TABLESQL_ACCOUNT = "create table if not exists "
                + AccountColumns.TABLE_NAME + " ("
                + AccountColumns.COLUMNS_ID + " integer primary key,"
                + AccountColumns.COLUMNS_NAME + " varchar,"
                + AccountColumns.COLUMNS_SEX + " varchar,"
                + AccountColumns.COLUMNS_TEL + " varchar,"
                + AccountColumns.COLUMNS_ADDRESS + " varchar,"
                + AccountColumns.COLUMNS_COMPANY + " varchar,"
                + AccountColumns.COLUMNS_ASSETS_TYPE + " integer,"
                + AccountColumns.COLUMNS_CONSUMER_GRADE + " integer,"
                + AccountColumns.COLUMNS_CARD_ID + " varchar,"
                + AccountColumns.COLUMNS_FLAG + " varchar,"
                + AccountColumns.COLUMNS_ATTRIBUTION + " varchar,"
                + AccountColumns.COLUMNS_INTEGRAL + " integer)";
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