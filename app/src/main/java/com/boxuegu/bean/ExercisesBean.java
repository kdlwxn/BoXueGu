package com.boxuegu.bean;

import java.io.Serializable;
import java.util.List;

public class ExercisesBean implements Serializable {
    //序列化时保持ExercisesBean类版本的兼容性
    private static final long serialVersionUID = 1L;
    private int id;                                     // 章节id
    private String chapterName;                       	// 章节名称
    private int totalNum;                          	    // 习题总数
    private int background;                             // 章节序号背景
    private List<ExercisesDetailBean> detailList; 	    //习题详情列表

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

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public List<ExercisesDetailBean> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<ExercisesDetailBean> detailList) {
        this.detailList = detailList;
    }
}
