package com.boxuegu.bean;

import java.io.Serializable;
import java.util.List;

public class CourseBean implements Serializable {
    //序列化时保持CourseBean类版本的兼容性
    private static final long serialVersionUID = 1L;
    private int id;                 		// 章节id
    private String chapterName;  		// 章节名称
    private String chapterIntro; 		// 章节简介
    private String chapterImg;   		// 章节图片
    private List<VideoBean> videoList;  //视频列表
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getChapterName() {
        return chapterName;
    }
    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterIntro() {
        return chapterIntro;
    }

    public void setChapterIntro(String chapterIntro) {
        this.chapterIntro = chapterIntro;
    }

    public String getChapterImg() {
        return chapterImg;
    }

    public void setChapterImg(String chapterImg) {
        this.chapterImg = chapterImg;
    }

    public List<VideoBean> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoBean> videoList) {
        this.videoList = videoList;
    }
}
