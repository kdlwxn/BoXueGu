package com.boxuegu.view;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.boxuegu.R;
import com.boxuegu.activity.LoginActivity;
import com.boxuegu.activity.PlayHistoryActivity;
import com.boxuegu.activity.SettingActivity;
import com.boxuegu.activity.UserInfoActivity;
import com.boxuegu.utils.UtilsHelper;
public class MyInfoView implements View.OnClickListener {
    public ImageView iv_head_icon;
    private LinearLayout ll_head;
    private RelativeLayout rl_course_history, rl_setting;
    private TextView tv_user_name;
    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    private boolean isLogin = false; //记录登录状态
    public MyInfoView(Activity context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }
    private void initView() {
        mCurrentView = mInflater.inflate(R.layout.main_view_myinfo, null);
        ll_head = mCurrentView.findViewById(R.id.ll_head);
        iv_head_icon = mCurrentView.findViewById(R.id.iv_head_icon);
        rl_course_history = mCurrentView.findViewById(R.id.rl_course_history);
        rl_setting = mCurrentView.findViewById(R.id.rl_setting);
        tv_user_name = mCurrentView.findViewById(R.id.tv_user_name);
        mCurrentView.setVisibility(View.VISIBLE);
        setLoginParams(isLogin);//设置登录时界面控件的显示信息
        ll_head.setOnClickListener(this);
        rl_course_history.setOnClickListener(this);
        rl_setting.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_head:
                if (UtilsHelper.readLoginStatus(mContext)) {
                    //跳转到个人资料界面
                    Intent intent = new Intent(mContext,UserInfoActivity.class);
                    mContext.startActivity(intent);
                } else {
                    //跳转到登录界面
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivityForResult(intent, 1);
                }
                break;
            case R.id.rl_course_history:
                if (UtilsHelper.readLoginStatus(mContext)) {
                    //跳转到播放记录界面
                    Intent intent=new Intent(mContext, PlayHistoryActivity.class);
                    mContext.startActivity(intent);
                } else {
                    Toast.makeText(mContext, "您还未登录，请先登录",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_setting:
                if (UtilsHelper.readLoginStatus(mContext)) {
                    //跳转到设置界面
                    Intent intent=new Intent(mContext,SettingActivity.class);
                    mContext.startActivityForResult(intent,1);
                } else {
                    Toast.makeText(mContext, "您还未登录，请先登录",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    /**
     * 设置"我"的界面中用户名控件的显示信息
     */
    public void setLoginParams(boolean isLogin) {
        if (isLogin) {
            tv_user_name.setText(UtilsHelper.readLoginUserName(mContext));
        } else {
            tv_user_name.setText("点击登录");
        }
    }
    public View getView() {
        //获取用户登录状态
        isLogin = UtilsHelper.readLoginStatus(mContext);
        if (mCurrentView == null) {
            initView();//初始化界面控件
        }
        return mCurrentView;
    }
    public void showView() {
        if (mCurrentView == null) {
            initView();//初始化界面控件
        }
        mCurrentView.setVisibility(View.VISIBLE);//设置"我"的界面为显示状态
    }
}
