package com.boxuegu.bean;
import java.io.Serializable;
public class BannerBean implements Serializable {
    //序列化时保持BannerBean类版本的兼容性
    private static final long serialVersionUID = 1L;
    private int id;             //广告id
    private String bannerImg; //广告图片
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBannerImg() {
        return bannerImg;
    }
    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }
}
