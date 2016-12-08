package com.yangmiao.bis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yangmiao.bis.db.UserProvider;
import com.yangmiao.bis.util.SpUtils;
import com.yangmiao.bis.util.Validator;
import com.yangmiao.bis.util.ViewScaleInjector;

public class LoginActivity extends Activity implements View.OnClickListener {

    public static final String DEFAULT_USERNAME_YGL = "18600390104";
    public static final String DEFAULT_USERNAME_YM = "18910584367";
    public static final String DEFAULT_PASSWORD = "123456";

    private TextView login_btn_login;
    private TextView login_btn_cancel;
    private EditText login_username_edittext;
    private EditText login_password_edittext;

    public static void startLoginActivity(Context context){
        if(context != null){
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        if (!UserProvider.checkLogin(this, DEFAULT_USERNAME_YGL, DEFAULT_PASSWORD)) {
            UserProvider.insert(this, DEFAULT_USERNAME_YGL, DEFAULT_PASSWORD);
            UserProvider.insert(this, DEFAULT_USERNAME_YM, DEFAULT_PASSWORD);
        }
        initView();
        setListener();
    }

    private void initView() {
        login_btn_login = (TextView) findViewById(R.id.login_btn_login);
        login_btn_cancel = (TextView) findViewById(R.id.login_btn_cancel);
        login_username_edittext = (EditText) findViewById(R.id.login_username_edittext);
        login_password_edittext = (EditText) findViewById(R.id.login_password_edittext);

        String username = UserProvider.getCurrentLoginUsername(this);
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
        ViewScaleInjector.injectClickToBeSmallerIntoView(login_btn_login);
        ViewScaleInjector.injectClickToBeSmallerIntoView(login_btn_cancel);
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
        } else if (UserProvider.checkLogin(this, username, password)) {
            text = "登录成功！";
            succ = true;
        } else {
            text = "登录失败！";
        }
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        if (succ) {
            SpUtils.putBoolean(this, UserProvider.SP_NAME, UserProvider.SP_KEY_BOOLEAN_ISLOGIN, true);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }


}
