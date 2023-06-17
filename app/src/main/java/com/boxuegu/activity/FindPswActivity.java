package com.boxuegu.activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.boxuegu.R;
import com.boxuegu.utils.UtilsHelper;

public class FindPswActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_validate_name, et_user_name;
    private Button btn_validate;
    private TextView tv_main_title;
    private TextView tv_back;
    private String from;
    private TextView tv_reset_psw, tv_user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);
        //获取从登录界面和设置界面传递过来的数据
        from = getIntent().getStringExtra("from");
        init();
    }
    private void init() {
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_back = findViewById(R.id.tv_back);
        et_validate_name = findViewById(R.id.et_validate_name);
        btn_validate = findViewById(R.id.btn_validate);
        tv_reset_psw = findViewById(R.id.tv_reset_psw);
        et_user_name = findViewById(R.id.et_user_name);
        tv_user_name = findViewById(R.id.tv_user_name);
        if ("security".equals(from)) {
            tv_main_title.setText("设置密保");
            btn_validate.setText("保存");
        } else {
            tv_main_title.setText("找回密码");
            tv_user_name.setVisibility(View.VISIBLE);
            et_user_name.setVisibility(View.VISIBLE);
            btn_validate.setText("验证");
        }
        tv_back.setOnClickListener(this);
        btn_validate.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                FindPswActivity.this.finish();
                break;
            case R.id.btn_validate:
                String validateName = et_validate_name.getText().toString().trim();
                if ("security".equals(from)) {//设置密保界面
                    if (TextUtils.isEmpty(validateName)) {
                        Toast.makeText(FindPswActivity.this, "请输入您的姓名",
                                Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(FindPswActivity.this, "密保设置成功",
                                Toast.LENGTH_SHORT).show();
                        //保存密保到SharedPreferences文件中
                        saveSecurity(validateName);
                        FindPswActivity.this.finish();
                    }
                } else {//找回密码界面
                    String userName = et_user_name.getText().toString().trim();
                    String sp_security = readSecurity(userName);
                    if (TextUtils.isEmpty(userName)) {
                        Toast.makeText(FindPswActivity.this, "请输入您的用户名",
                                Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!UtilsHelper.isExistUserName(FindPswActivity.this,
                            userName)) {
                        Toast.makeText(FindPswActivity.this, "您输入的用户名不存在",
                                Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(validateName)) {
                        Toast.makeText(FindPswActivity.this, "请输入要验证的姓名",
                                Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!validateName.equals(sp_security)) {
                        Toast.makeText(FindPswActivity.this, "输入的姓名不正确",
                                Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        //输入的密保正确，重新给用户设置一个密码
                        tv_reset_psw.setVisibility(View.VISIBLE);
                        tv_reset_psw.setText("初始密码：123456");
                        UtilsHelper.saveUserInfo(FindPswActivity.this, userName,
                                "123456");
                    }
                }
                break;
        }
    }
    private void saveSecurity(String validateName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();             //获取编辑器
        String userName= UtilsHelper.readLoginUserName(this); //获取登录用户名
        //保存密保信息
        editor.putString(userName+ "_security",validateName);
        editor.commit(); //提交修改
    }
    private String readSecurity(String userName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String security = sp.getString(userName + "_security", "");
        return security;
    }

}
