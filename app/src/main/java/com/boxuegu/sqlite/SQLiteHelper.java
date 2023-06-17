package com.boxuegu.sqlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1; // 数据库版本号
    public static String DB_NAME = "bxg.db"; // 数据库名称
    public static final String U_USERINFO = "userinfo";//用户信息表的名称
    public static final String U_VIDEO_PLAY_LIST = "videoplaylist";//视频播放列表
    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 创建用户信息表
        sqLiteDatabase.execSQL("CREATE TABLE  IF NOT EXISTS " + U_USERINFO + "( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "userName VARCHAR, " // 用户名
                + "nickName VARCHAR, " // 昵称
                + "sex VARCHAR, "       // 性别
                + "signature VARCHAR"  // 签名
                + ")");
        sqLiteDatabase.execSQL("CREATE TABLE  IF NOT EXISTS " + U_VIDEO_PLAY_LIST + "( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "userName VARCHAR, "    	//用户名
                + "chapterId INT, "       	//章节Id号
                + "videoId INT, "         	//视频Id号
                + "videoPath VARCHAR, "  	//视频地址
                + "chapterName VARCHAR, "	//视频章节名称
                + "videoName VARCHAR, " 	//视频名称
                + "videoIcon VARCHAR "    	//视频图标
                + ")");

    }
    /**
     * 当数据库版本号增加时，程序会调用此方法
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion,
                          int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + U_USERINFO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + U_VIDEO_PLAY_LIST);
        onCreate(sqLiteDatabase);
    }

}

