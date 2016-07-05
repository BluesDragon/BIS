package com.yangmiao.bis.db;

import android.net.Uri;
import android.provider.BaseColumns;

import com.yangmiao.bis.Constants;

public interface IProivderMetaData {

    // 定义外部访问的Authority
    String AUTHORITY_LOGIN = Constants.AUTH_LOGIN;
    String AUTHORITY_ACCOUNT = Constants.AUTH_ACCOUNT;

    // 数据库名称
    String DB_NAME = "bis.db";// 登录
    // 数据库版本
    int VERSION = 1;

    interface LoginMetaData extends BaseColumns {

        // 表名
        String TABLE_NAME = "login";

        // 外部程序访问本表的uri地址：登录
        Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY_LOGIN + "/" + TABLE_NAME);

        // 表列名
        String USERNAME = "username";
        String PASSWORD = "password";

        // 得到表中的所有记录
        String LIST = "vnd.android.cursor.dir/vnd.provider.list";
    }

    interface AccountMetaData extends BaseColumns {

        // 表名
        String TABLE_NAME = "account";

        // 外部程序访问本表的uri地址：登录
        Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY_ACCOUNT + "/" + TABLE_NAME);

        // 表列名
        String ASSETS = "assets";// 资产
        String INTEGRATION = "integration";// 积分

        // 得到表中的所有记录
        String LIST = "vnd.android.cursor.dir/vnd.provider.list";
    }

}
