package com.yangmiao.bis.db;

import android.content.Context;

import com.test.greendao.Account;
import com.test.greendao.AccountDao;
import com.yangmiao.bis.R;

import org.greenrobot.greendao.Property;

import java.util.List;

public class AccountProvider {

    public static final int AssetsType_OTHER = 0;
    public static final int AssetsType_LICAI = 1;
    public static final int AssetsType_HUOQI = 2;
    public static final int AssetsType_BAOXIAN = 3;
    public static final int AssetsType_DINGQI = 4;
    public static final int AssetsType_JIJIN = 5;

    public static final int ConsumerGrade_NORMAL = 1;
    public static final int ConsumerGrade_VIP = 2;
    public static final int ConsumerGrade_CAIFU = 3;
    public static final int ConsumerGrade_SIHANG = 4;

    public static String getAssetsTypeText(int assetsType, Context context) {
        String text = null;
        if (context != null) {
            int id = R.string.account_assets_type_other;
            switch (assetsType) {
                case AccountProvider.AssetsType_OTHER:
                    id = R.string.account_assets_type_other;
                    break;
                case AccountProvider.AssetsType_LICAI:
                    id = R.string.account_assets_type_licai;
                    break;
                case AccountProvider.AssetsType_HUOQI:
                    id = R.string.account_assets_type_huoqi;
                    break;
                case AccountProvider.AssetsType_BAOXIAN:
                    id = R.string.account_assets_type_baoxian;
                    break;
                case AccountProvider.AssetsType_DINGQI:
                    id = R.string.account_assets_type_dingqi;
                    break;
                case AccountProvider.AssetsType_JIJIN:
                    id = R.string.account_assets_type_jijin;
                    break;
            }
            text = context.getString(id);
        }
        return text;
    }

    public static String getConsumerGradeText(int consumerGrade, Context context) {
        String text = null;
        if (context != null) {
            int id = R.string.account_level_putong;
            switch (consumerGrade) {
                case ConsumerGrade_NORMAL:
                    id = R.string.account_level_putong;
                    break;
                case ConsumerGrade_VIP:
                    id = R.string.account_level_vip;
                    break;
                case ConsumerGrade_CAIFU:
                    id = R.string.account_level_caifu;
                    break;
                case ConsumerGrade_SIHANG:
                    id = R.string.account_level_sihang;
                    break;
            }
            text = context.getString(id);
        }
        return text;
    }

    public static void insertAccount(Context context, Account account) {
        if (context == null || account == null) {
            return;
        }
        DbManager.getInstance().getAccountDao().insert(account);
    }

    public static List<Account> queryAll(Context context) {
        return DbManager.getInstance().getAccountDao().queryBuilder().list();
    }

    public static List<Account> queryAllByIntegral() {
        return DbManager.getInstance().getAccountDao().queryBuilder().orderDesc(AccountDao.Properties.Integral).list();
    }

    public static List<Account> queryAllByCulumnsTel(Object selectionArgs) {
        return queryAllByCulumns(AccountDao.Properties.Tel, selectionArgs);
    }

    public static List<Account> queryAllByCulumnsCardId(Object selectionArgs) {
        return queryAllByCulumns(AccountDao.Properties.Card_id, selectionArgs);
    }

    public static List<Account> queryAllByCulumns(Property property, Object selectionArgs) {
        return DbManager.getInstance().getAccountDao().queryBuilder().where(AccountDao.Properties.Tel.eq(selectionArgs)).list();
    }
}
