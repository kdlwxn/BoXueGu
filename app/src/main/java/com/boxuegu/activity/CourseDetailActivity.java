package com.boxuegu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boxuegu.R;
import com.boxuegu.adapter.CourseDetailAdapter;
import com.boxuegu.bean.CourseBean;
import com.boxuegu.bean.VideoBean;
import com.boxuegu.utils.DBUtils;
import com.boxuegu.utils.UtilsHelper;

import java.util.List;

public class CourseDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_intro, tv_video, tv_chapter_intro;
    private RecyclerView rv_list;
    private ScrollView sv_chapter_intro;
    private CourseDetailAdapter adapter;
    private List<VideoBean> videoList;
    private String chapterName,intro;
    private DBUtils db;
    private CourseBean bean;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        //从课程界面传递过来的课程信息
        bean= (CourseBean) getIntent().getSerializableExtra("CourseBean");
        id = bean.getId();                   					 // 获取章节Id
        chapterName = bean.getChapterName(); 				 // 获取章节名称
        intro = bean.getChapterIntro();      					 // 获取章节简介
        videoList=bean.getVideoList();       					 // 获取视频列表信息
        db = DBUtils.getInstance(CourseDetailActivity.this); //创建数据库工具类的对象
        init();
    }
    private void init() {
        tv_intro = findViewById(R.id.tv_intro);
        tv_video = findViewById(R.id.tv_video);
        rv_list = findViewById(R.id.rv_list);
        tv_chapter_intro = findViewById(R.id.tv_chapter_intro);
        sv_chapter_intro= findViewById(R.id.sv_chapter_intro);
        adapter = new CourseDetailAdapter(this,
                new CourseDetailAdapter.OnSelectListener() {
                    @Override
                    public void onSelect(int position, ImageView iv) {
                        adapter.setSelectedPosition(position); // 设置适配器的选中项
                        VideoBean bean = videoList.get(position);
                        String videoPath = bean.getVideoPath();
                        adapter.notifyDataSetChanged();          // 更新列表界面数据
                        if (TextUtils.isEmpty(videoPath)) {
                            Toast.makeText(CourseDetailActivity.this,
                                    "本地没有此视频，暂无法播放", Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            // 判断用户是否登录，若登录则把此视频添加到数据库中
                            if(UtilsHelper.readLoginStatus(CourseDetailActivity.this)){
                                String userName= UtilsHelper.readLoginUserName(
                                        CourseDetailActivity.this);
                                db.saveVideoPlayList(id,chapterName,videoList.get(position),
                                        userName);
                            }
                            // 跳转到视频播放界面
                            Intent intent=new Intent(CourseDetailActivity.this,VideoPlayActivity.class);
                            intent.putExtra("videoPath", videoPath);
                            startActivityForResult(intent, 1);
                        }
                    }
                });
        //设置列表中内容的排列方向为垂直排列
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(adapter);
        adapter.setData(videoList);
        tv_chapter_intro.setText(intro);
        tv_intro.setOnClickListener(this);
        tv_video.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_intro:// 简介
                rv_list.setVisibility(View.GONE);
                sv_chapter_intro.setVisibility(View.VISIBLE);
                tv_intro.setBackgroundResource(R.drawable.video_list_intro_blue);
                tv_video.setBackgroundResource(R.drawable.video_list_intro_white);
                tv_intro.setTextColor(Color.parseColor("#FFFFFF"));
                tv_video.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.tv_video:// 视频
                rv_list.setVisibility(View.VISIBLE);
                sv_chapter_intro.setVisibility(View.GONE);
                tv_intro.setBackgroundResource(R.drawable.video_list_intro_white);
                tv_video.setBackgroundResource(R.drawable.video_list_intro_blue);
                tv_intro.setTextColor(Color.parseColor("#000000"));
                tv_video.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            default:
                break;
        }
    }

}
