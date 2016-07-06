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
    int VERSION = 2;

    interface LoginColumns extends BaseColumns {

        // 表名
        String TABLE_NAME = "login";

        // 外部程序访问本表的uri地址：登录
        Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY_LOGIN + "/" + TABLE_NAME);

        // 表列名
        String COLUMNS_ID = "_id";// id
        String COLUMNS_USERNAME = "username";
        String COLUMNS_PASSWORD = "password";

        // 得到表中的所有记录
        String LIST = "vnd.android.cursor.dir/vnd.provider.list";
    }

    interface AccountColumns extends BaseColumns {

        // 表名
        String TABLE_NAME = "account";

        // 外部程序访问本表的uri地址：登录
        Uri URI_ACCOUNT = Uri.parse("content://"
                + AUTHORITY_ACCOUNT + "/" + TABLE_NAME);

        // 表列名
        String COLUMNS_ID = "_id";// id
        String COLUMNS_NAME = "name";// 姓名
        String COLUMNS_SEX = "sex";// 性别
        String COLUMNS_TEL = "tel";// 电话
        String COLUMNS_ADDRESS = "address";// 地址
        String COLUMNS_COMPANY = "company";// 单位
        String COLUMNS_ASSETS_TYPE = "assets_type";// 资产类型
        String COLUMNS_CONSUMER_GRADE = "consumer_grade";// 客户等级
        String COLUMNS_CARD_ID = "card_id";// 身份证号
        String COLUMNS_FLAG = "flag";// 标记
        String COLUMNS_ATTRIBUTION = "attribution";// 归属地
        String COLUMNS_INTEGRAL = "integral";// 积分

        // 得到表中的所有记录
        String LIST = "vnd.android.cursor.dir/vnd.provider.list";
    }

}
