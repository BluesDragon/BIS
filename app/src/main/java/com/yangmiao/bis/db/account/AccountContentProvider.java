package com.yangmiao.bis.db.account;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.yangmiao.bis.db.IProivderMetaData;
import com.yangmiao.bis.db.DbHelper;

public class AccountContentProvider extends ContentProvider {

    private static UriMatcher uriMatcher;
    private static final int TYPE_ACCOUNT_LIST = 1;

    private static final String TABLE_NAME = IProivderMetaData.AccountMetaData.TABLE_NAME;

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
                return IProivderMetaData.AccountMetaData.LIST;
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
}
