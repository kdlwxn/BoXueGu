package com.boxuegu.activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.boxuegu.R;
public class VideoPlayActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController controller;
    private String videoPath;    // 视频地址
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置界面全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_play);
        //设置界面为横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //获取从视频详情界面或播放记录界面传递过来的视频地址
        videoPath = getIntent().getStringExtra("videoPath");
        init();
    }
    private void init() {
        videoView = findViewById(R.id.videoView);
        controller = new MediaController(this);
        videoView.setMediaController(controller);
        play();
    }
    /**
     * 实现播放视频功能
     */
    private void play() {
        if (TextUtils.isEmpty(videoPath)) {
            Toast.makeText(this, "没有此视频，暂无法播放", Toast.LENGTH_SHORT).show();
            return;
        }
        videoView.setVideoPath(videoPath); 	//加载视频地址
        videoView.start(); 					//播放视频
    }
}
