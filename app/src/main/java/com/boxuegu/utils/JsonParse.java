package com.boxuegu.utils;

import com.boxuegu.bean.BannerBean;
import com.boxuegu.bean.CourseBean;
import com.boxuegu.bean.ExercisesBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParse {
    private static JsonParse instance;
    private JsonParse() {
    }
    public static JsonParse getInstance() {
        if (instance == null) {
            instance = new JsonParse();
        }
        return instance;
    }
    public List<ExercisesBean> getExercisesList(String json) {
        Gson gson = new Gson();
        // 创建一个TypeToken的匿名子类对象，并调用该对象的getType()方法
        Type listType = new TypeToken<List<ExercisesBean>>() {
        }.getType();
        // 把获取到的数据存放到集合exercisesList中
        List<ExercisesBean> exercisesList = gson.fromJson(json, listType);
        return exercisesList;
    }
    public List<BannerBean> getBannerList(String json) {
        Gson gson = new Gson();
        // 创建一个TypeToken的匿名子类对象，并调用该对象的getType()方法
        Type listType = new TypeToken<List<BannerBean>>() {}.getType();
        // 把获取到的数据存放在集合bannerList中
        List<BannerBean> bannerList = gson.fromJson(json, listType);
        return bannerList;
    }
    public List<CourseBean> getCourseList(String json) {
        Gson gson = new Gson();
        // 创建一个TypeToken的匿名子类对象，并调用该对象的getType()方法
        Type listType = new TypeToken<List<CourseBean>>() {}.getType();
        // 把获取到的数据存放在集合courseList中
        List<CourseBean> courseList = gson.fromJson(json, listType);
        return courseList;
    }

}
