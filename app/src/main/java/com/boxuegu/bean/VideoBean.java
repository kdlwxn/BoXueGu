package com.boxuegu.bean;

import java.io.Serializable;

public class VideoBean implements Serializable {
    //序列化时保持VideoBean类版本的兼容性
    private static final long serialVersionUID = 1L;
    private int videoId;          // 视频Id
    private String chapterName; // 章节名称
    private String videoName;	// 视频名称
    private String videoIcon;	// 视频图标
    private String videoPath; 	// 视频播放地址
    public int getVideoId() {
        return videoId;
    }
    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
    public String getChapterName() {
        return chapterName;
    }
    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
    public String getVideoName() {
        return videoName;
    }
    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
    public String getVideoIcon() {
        return videoIcon;
    }
    public void setVideoIcon(String videoIcon) {
        this.videoIcon = videoIcon;
    }
    public String getVideoPath() {
        return videoPath;
    }
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
