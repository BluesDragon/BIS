package com.yangmiao.bis.db;

import android.content.Context;

import com.test.greendao.User;
import com.test.greendao.UserDao;
import com.yangmiao.bis.util.SpUtils;

import org.greenrobot.greendao.query.QueryBuilder;

public class UserProvider {

    public static final String SP_NAME = "sp_name_login";
    public static final String SP_KEY_BOOLEAN_ISLOGIN = "sp_key_boolean_islogin";
    public static final String SP_KEY_STRING_LOGIN_USERNAME = "sp_key_string_login_username";

    public static boolean isLogin(Context context) {
        return SpUtils.getBoolean(context, SP_NAME, SP_KEY_BOOLEAN_ISLOGIN, false);
    }

    public static void logout(Context context) {
        SpUtils.remove(context, SP_NAME, SP_KEY_STRING_LOGIN_USERNAME);
    }

    public static void insert(User login) {
        if (login == null) {
            return;
        }
        DbManager.getInstance().getUserDao().insert(login);
    }

    public static boolean checkLogin(User login) {
        if (login == null) {
            return false;
        }
        QueryBuilder<User> queryBuilder = DbManager.getInstance().getUserDao().queryBuilder();
        queryBuilder.and(UserDao.Properties.Username.eq(login.getUsername()), UserDao.Properties.Password.eq(login.getPassword()));
        User unique = queryBuilder.unique();
        return unique != null;
    }


    public static String getCurrentLoginUsername(Context context) {
        return SpUtils.getString(context, SP_NAME, SP_KEY_STRING_LOGIN_USERNAME);
    }

    public static void deleteUser(Context context, User login) {
        if (context == null) {
            return;
        }
        DbManager.getInstance().getUserDao().delete(login);
    }

}
