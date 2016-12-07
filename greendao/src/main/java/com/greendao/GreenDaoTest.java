package com.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class GreenDaoTest implements IMetaData {

    public static void main(String[] args) {

        Schema schema = new Schema(VERSION, "com.test.greendao");

        addEntity(schema);

        try {
            new DaoGenerator().generateAll(schema, "./../BIS/app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addEntity(Schema schema) {
        initLoginEntity(schema);
        initAccountEntity(schema);
    }

    /**
     * 初始化Login实体
     *
     * @param schema
     */
    private static void initLoginEntity(Schema schema) {
        Entity accountBook = schema.addEntity(LoginColumns.ENTITY_NAME);
        accountBook.setDbName(LoginColumns.TABLE_NAME);
        accountBook.addIdProperty().autoincrement();//添加Id,自增长
        accountBook.addStringProperty(LoginColumns.COLUMNS_USERNAME);
        accountBook.addStringProperty(LoginColumns.COLUMNS_PASSWORD);
    }

    /**
     * 初始化Account实体
     *
     * @param schema
     */
    private static void initAccountEntity(Schema schema) {
        Entity accountBook = schema.addEntity(AccountColumns.ENTITY_NAME);
        accountBook.setDbName(AccountColumns.TABLE_NAME);
        accountBook.addIdProperty().autoincrement();//添加Id,自增长
        accountBook.addStringProperty(AccountColumns.COLUMNS_NAME);
        accountBook.addStringProperty(AccountColumns.COLUMNS_SEX);
        accountBook.addIntProperty(AccountColumns.COLUMNS_TEL);
        accountBook.addStringProperty(AccountColumns.COLUMNS_ADDRESS);
        accountBook.addStringProperty(AccountColumns.COLUMNS_COMPANY);
        accountBook.addIntProperty(AccountColumns.COLUMNS_ASSETS_TYPE);
        accountBook.addIntProperty(AccountColumns.COLUMNS_CONSUMER_GRADE);
        accountBook.addStringProperty(AccountColumns.COLUMNS_CARD_ID);
        accountBook.addStringProperty(AccountColumns.COLUMNS_FLAG);
        accountBook.addStringProperty(AccountColumns.COLUMNS_ATTRIBUTION);
        accountBook.addIntProperty(AccountColumns.COLUMNS_INTEGRAL);
    }

}
