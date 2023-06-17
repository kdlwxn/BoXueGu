package com.boxuegu.activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.boxuegu.MainActivity;
import com.boxuegu.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private TextView tv_version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }
    private void init(){
        //获取显示版本号信息的控件tv_version
        tv_version=findViewById(R.id.tv_version);
        try {
            //获取程序包信息
            PackageInfo info= getPackageManager().getPackageInfo(getPackageName(), 0);
            //将程序版本号信息设置到界面控件上
            tv_version.setText("V"+info.versionName);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            tv_version.setText("V");
        }
        //创建Timer类的对象
        Timer timer = new Timer();
        //通过TimerTask类实现界面跳转的功能
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        timer.schedule(task, 3000); //设置程序延迟3秒之后自动执行任务task
    }

}
