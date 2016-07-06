package com.yangmiao.bis.util;

import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SpUtils {

    private static int mode = Context.MODE_WORLD_WRITEABLE;

    // 将参数保存到SharedPreferences中
    public static void putString(Context context, String name, String key,
                                 String value) {
        SharedPreferences pref = context.getSharedPreferences(name, mode);
        Editor edit = pref.edit();
        edit.putString(key, value);
        edit.commit();
    }

    // 获取SharedPreferences中保存的参数
    public static String getString(Context context, String name, String key) {
        SharedPreferences pref = context.getSharedPreferences(name, mode);
        return pref.getString(key, "");
    }

    public static void putBoolean(Context context, String name, String key,
                                  boolean value) {
        SharedPreferences pref = context.getSharedPreferences(name, mode);
        Editor edit = pref.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public static boolean getBoolean(Context context, String name, String key) {
        SharedPreferences pref = context.getSharedPreferences(name, mode);
        return pref.getBoolean(key, false);
    }

    public static boolean getBoolean(Context context, String name, String key,
                                     boolean defValue) {
        SharedPreferences pref = context.getSharedPreferences(name, mode);
        return pref.getBoolean(key, defValue);
    }

    public static void putInt(Context context, String name, String key,
                              int value) {
        SharedPreferences pref = context.getSharedPreferences(name, mode);
        Editor edit = pref.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static int getInt(Context context, String name, String key) {
        SharedPreferences pref = context.getSharedPreferences(name, mode);
        return pref.getInt(key, 0);
    }

    public static int getInt(Context context, String name, String key,
                             int defValue) {
        SharedPreferences pref = context.getSharedPreferences(name, mode);
        return pref.getInt(key, defValue);
    }

    public static void remove(Context context, String name, String key) {
        SharedPreferences pref = context.getSharedPreferences(name, mode);
        Editor edit = pref.edit();
        edit.remove(key);
        edit.commit();
    }

    public static Map<String, String> getAll(Context context, String name) {
        SharedPreferences pref = context.getSharedPreferences(name, mode);
        return (Map<String, String>) pref.getAll();
    }

    @SuppressLint("CommitPrefEdits")
    public static void clear(Context context, String name) {
        SharedPreferences pref = context.getSharedPreferences(name, mode);
        Editor edit = pref.edit();
        edit.clear();
        edit.commit();
    }
}