package com.boxuegu.view;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.boxuegu.R;
import com.boxuegu.adapter.AdBannerAdapter;
import com.boxuegu.adapter.CourseAdapter;
import com.boxuegu.bean.BannerBean;
import com.boxuegu.bean.CourseBean;
import com.boxuegu.utils.Constant;
import com.boxuegu.utils.JsonParse;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CourseView {
    private RecyclerView rv_list;
    private CourseAdapter adapter;
    private FragmentActivity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    private ViewPager adPager;    		// 广告
    private View adBannerLay;     		// 广告条容器
    private AdBannerAdapter ada;  		// 适配器
    private ViewPagerIndicator vpi; 	// 小圆点
    public static final int MSG_BANNER_OK = 001; 	// 广告数据
    public static final int MSG_COURSE_OK = 002; 	// 课程数据
    private MHandler mHandler;                   	// 事件捕获
    public static final int MSG_AD_SLID = 003;      // 广告自动滑动
    public CourseView(FragmentActivity context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        initView();
    }
    private void initView() {
        mHandler = new MHandler();
        getData(Constant.REQUEST_BANNER_URL,MSG_BANNER_OK); //获取广告数据
        getData(Constant.REQUEST_COURSE_URL,MSG_COURSE_OK); //获取课程数据
        mCurrentView = mInflater.inflate(R.layout.main_view_course, null);
        rv_list = mCurrentView.findViewById(R.id.rv_list);
        adapter = new CourseAdapter(mContext);
        rv_list.setLayoutManager(new GridLayoutManager(mContext,2));
        rv_list.setAdapter(adapter);
        adPager =  mCurrentView.findViewById(R.id.vp_advertBanner);
        adPager.setLongClickable(false); //设置ViewPager控件的长按点击事件失效
        ada = new AdBannerAdapter(mContext.getSupportFragmentManager());
        adPager.setAdapter(ada);          // 给ViewPager控件设置适配器
        vpi = mCurrentView.findViewById(R.id.vpi_advert_indicator); // 获取广告上的小圆点
        vpi.setCount(ada.getSize()); 	// 设置小圆点的个数
        adBannerLay = mCurrentView.findViewById(R.id.rl_adBanner);
        resetSize();
        new AdAutoSlidThread().start();
        adPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (ada.getSize() > 0) {
                    //用position%ada.getSize()来标记滑动到的当前位置
                    vpi.setCurrentPosition(position % ada.getSize());
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    /**
     * 实现广告栏每隔5秒自动滑动的功能
     */
    class AdAutoSlidThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    sleep(5000); //线程睡眠5秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mHandler != null){
                    mHandler.sendEmptyMessage(MSG_AD_SLID);
                }
            }
        }
    }
    private void getData(String url, final int ok) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constant.WEB_SITE + url).build();
        Call call = okHttpClient.newCall(request);
        // 开启异步线程访问网络
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string(); //获取数据
                Message msg = new Message();
                msg.what = ok;
                msg.obj = res;
                mHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Call call, IOException e) {
            }
        });
    }
    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_COURSE_OK:
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        //解析获取的JSON数据
                        List<CourseBean> cbl = JsonParse.getInstance().
                                getCourseList(vlResult);
                        adapter.setData(cbl); //设置课程数据到课程适配器中
                    }
                    break;
                case MSG_BANNER_OK:
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        //解析获取的JSON数据
                        List<BannerBean> bbl = JsonParse.getInstance().
                                getBannerList(vlResult);
                        ada.setData(bbl);       	//设置广告栏数据到广告栏适配器中
                        vpi.setCount(bbl.size()); 	//设置小圆点的数量
                        vpi.setCurrentPosition(0);	//设置当前小圆点的位置为0
                    }
                    break;
                case MSG_AD_SLID:
                    if (ada.getCount() > 0) {
                        // 设置滑动到下一张广告图片
                        adPager.setCurrentItem(adPager.getCurrentItem() + 1);
                    }
                    break;
            }
        }
    }
    /**
     * 设置广告栏布局的宽度与高度
     */
    private void resetSize() {
        int sw = getScreenWidth(mContext); //获取屏幕宽度
        int adLheight = sw / 2;              // 广告栏高度设置为宽度的1/2
        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();
        adlp.width = sw;
        adlp.height = adLheight;
        adBannerLay.setLayoutParams(adlp); //设置广告栏布局的宽度和高度
    }
    /**
     * 获取屏幕宽度
     */
    private int getScreenWidth(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = context.getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics); 	//将获取的屏幕的宽度与高度存放在对象metrics中
        return metrics.widthPixels; 	//返回屏幕的宽度
    }
    public View getView() {
        if (mCurrentView == null) {
            initView();
        }
        return mCurrentView;
    }
    public void showView() {
        if (mCurrentView == null) {
            initView();
        }
        mCurrentView.setVisibility(View.VISIBLE); // 显示当前视图
    }
}
