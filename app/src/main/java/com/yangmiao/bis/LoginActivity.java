package com.yangmiao.bis;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yangmiao.bis.db.IProivderMetaData;
import com.yangmiao.bis.util.SpUtils;
import com.yangmiao.bis.util.Validator;

public class LoginActivity extends Activity implements View.OnClickListener {

    public static final String SP_NAME = "sp_name_login";
    public static final String SP_KEY_BOOLEAN_ISLOGIN = "sp_key_boolean_islogin";
    public static final String SP_KEY_STRING_LOGIN_USERNAME = "sp_key_string_login_username";

    public static final String DEFAULT_USERNAME = "18600390104";
    public static final String DEFAULT_PASSWORD = "123456";

    private TextView login_btn_login;
    private TextView login_btn_cancel;
    private EditText login_username_edittext;
    private EditText login_password_edittext;

    private ContentResolver mContentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        mContentResolver = getContentResolver();
        if (!checkUser(DEFAULT_USERNAME, DEFAULT_PASSWORD)) {
            insertUser(DEFAULT_USERNAME, DEFAULT_PASSWORD);
        }
        initView();
        setListener();
    }

    private void initView() {
        login_btn_login = (TextView) findViewById(R.id.login_btn_login);
        login_btn_cancel = (TextView) findViewById(R.id.login_btn_cancel);
        login_username_edittext = (EditText) findViewById(R.id.login_username_edittext);
        login_password_edittext = (EditText) findViewById(R.id.login_password_edittext);

        String username = SpUtils.getString(this, SP_NAME, SP_KEY_STRING_LOGIN_USERNAME);
        if (!TextUtils.isEmpty(username)) {
            login_username_edittext.setText(username);
            login_password_edittext.requestFocus();
        } else {
            login_username_edittext.requestFocus();
        }
    }

    private void setListener() {
        login_btn_login.setOnClickListener(this);
        login_btn_cancel.setOnClickListener(this);
    }

    public static boolean isLogin(Context context) {
        return SpUtils.getBoolean(context, SP_NAME, SP_KEY_BOOLEAN_ISLOGIN, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn_login:
                check();
                break;
            case R.id.login_btn_cancel:
                finish();
                break;
        }
    }

    private void check() {
        boolean succ = false;
        String text;
        String username = login_username_edittext.getText().toString();
        String password = login_password_edittext.getText().toString();
        if (!Validator.isMobile(username) && !Validator.isIDCard(username)) {
            text = "用户名输入错误！";
        } else if (!Validator.isPassword(password)) {
            text = "密码输入错误！";
        } else if (checkUser(username, password)) {
            text = "登录成功！";
            succ = true;
        } else {
            text = "登录失败！";
        }
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        if (succ) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private boolean checkUser(String username, String password) {
        Cursor query = mContentResolver.query(IProivderMetaData.LoginMetaData.CONTENT_URI, null, " " + IProivderMetaData.LoginMetaData.USERNAME + " = ?", new String[]{username}, null);
        while (query.moveToNext()) {
            String pw = query.getString(query
                    .getColumnIndex(IProivderMetaData.LoginMetaData.PASSWORD));
            if (!TextUtils.isEmpty(pw) && pw.equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void insertUser(String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(IProivderMetaData.LoginMetaData.USERNAME, username);
        contentValues.put(IProivderMetaData.LoginMetaData.PASSWORD, password);
        mContentResolver.insert(IProivderMetaData.LoginMetaData.CONTENT_URI, contentValues);
        SpUtils.putBoolean(this, SP_NAME, SP_KEY_BOOLEAN_ISLOGIN, true);
        SpUtils.putString(this, SP_NAME, SP_KEY_STRING_LOGIN_USERNAME, username);
    }
}
