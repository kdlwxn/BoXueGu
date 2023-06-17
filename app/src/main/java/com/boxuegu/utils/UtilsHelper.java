package com.boxuegu.utils;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.ImageView;

public class UtilsHelper {
    /**
     * 判断SharedPreferences文件中是否存在要保存的用户名
     */
    public static boolean isExistUserName(Context context,String userName){
        boolean has_userName=false;
        SharedPreferences sp=context.getSharedPreferences("loginInfo",
                Context.MODE_PRIVATE);
        String spPsw=sp.getString(userName, "");
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }
    public static void saveUserInfo(Context context,String userName,String psw){
        //将密码用MD5加密
        String md5Psw=MD5Utils.md5(psw);
        //获取SharedPreferences类的对象sp
        SharedPreferences sp= context.getSharedPreferences("loginInfo",
                Context.MODE_PRIVATE);
        //获取编辑器对象editor
        SharedPreferences.Editor editor=sp.edit();
        //将用户名和密码封装到编辑器对象editor中
        editor.putString(userName, md5Psw);
        editor.commit();//提交保存信息
    }
    public static String readPsw(Context context,String userName){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",
                Context.MODE_PRIVATE);
        String spPsw=sp.getString(userName, "");
        return spPsw;
    }
    /**
     * 保存登录状态和登录用户名到SharedPreferences文件中
     */
    public static void saveLoginStatus(Context context,boolean status,String userName){
        SharedPreferences sp= context.getSharedPreferences("loginInfo",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();      //获取编辑器
        editor.putBoolean("isLogin", status);            //存入boolean类型的登录状态
        editor.putString("loginUserName", userName);   //存入登录时的用户名
        editor.commit();                                     //提交修改
    }
    public static boolean readLoginStatus(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo",
                Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }
    public static void clearLoginStatus(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();  //获取编辑器
        editor.putBoolean("isLogin", false);            //清除登录状态
        editor.putString("loginUserName", "");         //清除登录时的用户名
        editor.commit();                                   //提交修改
    }
    public static String readLoginUserName(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",
                Context.MODE_PRIVATE);
        String userName=sp.getString("loginUserName", "");//获取登录时的用户名
        return userName;
    }
    public static void setABCDEnable(boolean value,ImageView iv_a,ImageView
            iv_b,ImageView iv_c,ImageView iv_d){
        iv_a.setEnabled(value); //设置选项A的图片是否可被点击
        iv_b.setEnabled(value); //设置选项B的图片是否可被点击
        iv_c.setEnabled(value); //设置选项C的图片是否可被点击
        iv_d.setEnabled(value); //设置选项D的图片是否可被点击
    }
}
