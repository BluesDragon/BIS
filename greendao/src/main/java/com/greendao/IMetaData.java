package com.greendao;

public interface IMetaData {

    /**
     * 数据库版本
     */
    int VERSION = 1;

    // 数据库名称
    String DB_NAME = "bis.db";// 登录

    interface LoginColumns {

        // 实体类的名字
        String ENTITY_NAME = "User";

        // 表名
        String TABLE_NAME = "user";

        // 表列名
        String COLUMNS_USERNAME = "username";
        String COLUMNS_PASSWORD = "password";
    }

    interface AccountColumns {

        // 实体类的名字
        String ENTITY_NAME = "Account";

        // 表名
        String TABLE_NAME = "account";

        // 表列名
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
    }

}
