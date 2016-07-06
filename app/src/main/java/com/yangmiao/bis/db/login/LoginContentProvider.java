package com.yangmiao.bis.db.login;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import com.yangmiao.bis.db.DbHelper;
import com.yangmiao.bis.db.IProivderMetaData;
import com.yangmiao.bis.util.SpUtils;

public class LoginContentProvider extends ContentProvider {

    public static final String SP_NAME = "sp_name_login";
    public static final String SP_KEY_BOOLEAN_ISLOGIN = "sp_key_boolean_islogin";
    public static final String SP_KEY_STRING_LOGIN_USERNAME = "sp_key_string_login_username";

    private static UriMatcher uriMatcher;
    private static final int TYPE_LOGIN = 1;
    private static final String TABLE_NAME = IProivderMetaData.LoginColumns.TABLE_NAME;

    private DbHelper dbHelper;
    private SQLiteDatabase db;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(IProivderMetaData.AUTHORITY_LOGIN, TABLE_NAME, TYPE_LOGIN);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TYPE_LOGIN:
                return IProivderMetaData.LoginColumns.LIST;
            default:
                throw new IllegalArgumentException("This is a unKnow Uri"
                        + uri.toString());
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case TYPE_LOGIN:
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
            case TYPE_LOGIN:
                return db.query(TABLE_NAME, projection, selection, selectionArgs, null, null,
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

    public static boolean isLogin(Context context) {
        return SpUtils.getBoolean(context, SP_NAME, SP_KEY_BOOLEAN_ISLOGIN, false);
    }

    public static void insertUser(Context context, String username, String password) {
        if (context == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(IProivderMetaData.LoginColumns.COLUMNS_USERNAME, username);
        contentValues.put(IProivderMetaData.LoginColumns.COLUMNS_PASSWORD, password);
        context.getContentResolver().insert(IProivderMetaData.LoginColumns.CONTENT_URI, contentValues);
        SpUtils.putString(context, SP_NAME, SP_KEY_STRING_LOGIN_USERNAME, username);
    }

    public static boolean checkUser(Context context, String username, String password) {
        if(context == null){
            return false;
        }
        Cursor query = context.getContentResolver().query(IProivderMetaData.LoginColumns.CONTENT_URI, null, " " + IProivderMetaData.LoginColumns.COLUMNS_USERNAME + " = ?", new String[]{username}, null);
        while (query != null && query.moveToNext()) {
            String pw = query.getString(query
                    .getColumnIndex(IProivderMetaData.LoginColumns.COLUMNS_PASSWORD));
            if (!TextUtils.isEmpty(pw) && pw.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void logout(Context context) {
        deleteUser(context, getCurrentLoginUsername(context));
    }

    public static String getCurrentLoginUsername(Context context) {
        return SpUtils.getString(context, SP_NAME, SP_KEY_STRING_LOGIN_USERNAME);
    }

    public static void deleteUser(Context context, String username) {
        if (context == null) {
            return;
        }
        context.getContentResolver().delete(IProivderMetaData.LoginColumns.CONTENT_URI, " where username = ?", new String[]{" " + username + " "});
        SpUtils.remove(context, SP_NAME, SP_KEY_STRING_LOGIN_USERNAME);
    }

}
