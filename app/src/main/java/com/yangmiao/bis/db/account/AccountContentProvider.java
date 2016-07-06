package com.yangmiao.bis.db.account;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.yangmiao.bis.db.IProivderMetaData;
import com.yangmiao.bis.db.DbHelper;
import com.yangmiao.bis.model.AccountInfo;

import java.util.ArrayList;
import java.util.List;

public class AccountContentProvider extends ContentProvider {

    private static UriMatcher uriMatcher;
    private static final int TYPE_ACCOUNT_LIST = 1;

    private static final String TABLE_NAME = IProivderMetaData.AccountColumns.TABLE_NAME;

    private DbHelper dbHelper;
    private SQLiteDatabase db;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(IProivderMetaData.AUTHORITY_ACCOUNT, TABLE_NAME, TYPE_ACCOUNT_LIST);
    }


    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TYPE_ACCOUNT_LIST:
                return IProivderMetaData.AccountColumns.LIST;
            default:
                throw new IllegalArgumentException("This is a unKnow Uri"
                        + uri.toString());
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case TYPE_ACCOUNT_LIST:
                db = dbHelper.getWritableDatabase();// 取得数据库操作实例

                // 执行添加，返回行号，如果主健字段是自增长的，那么行号会等于主键id
                long rowId = db.insert(TABLE_NAME, null, values);
                Uri insertUri = Uri.withAppendedPath(uri, "/" + rowId);
                // 发出数据变化通知(book表的数据发生变化)
                getContext().getContentResolver().notifyChange(uri, null);
                return insertUri;
            default:
                // 不能识别uri
                throw new IllegalArgumentException("This is a unKnow Uri"
                        + uri.toString());
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        db = dbHelper.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case TYPE_ACCOUNT_LIST:
                return db
                        .query(TABLE_NAME, projection, selection, selectionArgs, null, null,
                                sortOrder);
            default:
                throw new IllegalArgumentException("This is a unKnow Uri"
                        + uri.toString());
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static void insertAccount(Context context, AccountInfo accountInfo) {
        if (context == null || accountInfo == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(IProivderMetaData.AccountColumns.COLUMNS_NAME, accountInfo.name);
        contentValues.put(IProivderMetaData.AccountColumns.COLUMNS_SEX, accountInfo.sex);
        contentValues.put(IProivderMetaData.AccountColumns.COLUMNS_TEL, accountInfo.tel);
        contentValues.put(IProivderMetaData.AccountColumns.COLUMNS_ADDRESS, accountInfo.address);
        contentValues.put(IProivderMetaData.AccountColumns.COLUMNS_COMPANY, accountInfo.company);
        contentValues.put(IProivderMetaData.AccountColumns.COLUMNS_ASSETS_TYPE, accountInfo.assetsType);
        contentValues.put(IProivderMetaData.AccountColumns.COLUMNS_CONSUMER_GRADE, accountInfo.consumerGrade);
        contentValues.put(IProivderMetaData.AccountColumns.COLUMNS_CARD_ID, accountInfo.cardId);
        contentValues.put(IProivderMetaData.AccountColumns.COLUMNS_FLAG, accountInfo.flag);
        contentValues.put(IProivderMetaData.AccountColumns.COLUMNS_ATTRIBUTION, accountInfo.attribution);
        contentValues.put(IProivderMetaData.AccountColumns.COLUMNS_INTEGRAL, accountInfo.integral);
        context.getContentResolver().insert(IProivderMetaData.AccountColumns.URI_ACCOUNT, contentValues);
    }

    public static List<AccountInfo> queryAll(Context context) {
        List<AccountInfo> list = null;
        if (context != null) {
            list = new ArrayList<>();
            Cursor query = context.getContentResolver().query(IProivderMetaData.AccountColumns.URI_ACCOUNT, null, null, null, null);
            while (query != null && query.moveToNext()) {
                AccountInfo accountInfo = new AccountInfo();
                accountInfo.name = query.getString(query.getColumnIndex(IProivderMetaData.AccountColumns.COLUMNS_NAME));
                accountInfo.sex = query.getString(query.getColumnIndex(IProivderMetaData.AccountColumns.COLUMNS_SEX));
                accountInfo.tel = query.getString(query.getColumnIndex(IProivderMetaData.AccountColumns.COLUMNS_TEL));
                accountInfo.address = query.getString(query.getColumnIndex(IProivderMetaData.AccountColumns.COLUMNS_ADDRESS));
                accountInfo.company = query.getString(query.getColumnIndex(IProivderMetaData.AccountColumns.COLUMNS_COMPANY));
                accountInfo.assetsType = query.getInt(query.getColumnIndex(IProivderMetaData.AccountColumns.COLUMNS_ASSETS_TYPE));
                accountInfo.consumerGrade = query.getInt(query.getColumnIndex(IProivderMetaData.AccountColumns.COLUMNS_CONSUMER_GRADE));
                accountInfo.cardId = query.getString(query.getColumnIndex(IProivderMetaData.AccountColumns.COLUMNS_CARD_ID));
                accountInfo.flag = query.getString(query.getColumnIndex(IProivderMetaData.AccountColumns.COLUMNS_FLAG));
                accountInfo.attribution = query.getString(query.getColumnIndex(IProivderMetaData.AccountColumns.COLUMNS_ATTRIBUTION));
                accountInfo.integral = query.getInt(query.getColumnIndex(IProivderMetaData.AccountColumns.COLUMNS_INTEGRAL));
                list.add(accountInfo);
            }
        }
        return list;
    }
}
