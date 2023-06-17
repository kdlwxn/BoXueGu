package com.boxuegu.view;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.boxuegu.R;
import com.boxuegu.adapter.ExercisesAdapter;
import com.boxuegu.bean.ExercisesBean;
import com.boxuegu.utils.Constant;
import com.boxuegu.utils.JsonParse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExercisesView {
    private RecyclerView rv_list;
    private ExercisesAdapter adapter;
    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    private List<ExercisesBean> ebl;
    private MHandler mHandler;
    public static final int MSG_EXERCISES_OK = 1; //获取习题数据
    public ExercisesView(Activity context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }
    private void initView() {
        ebl=new ArrayList<>();
        mHandler=new MHandler();
        getExercisesData();
        mCurrentView = mInflater.inflate(R.layout.main_view_exercises, null);
        rv_list = mCurrentView.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new ExercisesAdapter(mContext);
        rv_list.setAdapter(adapter);
    }
    private void getExercisesData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constant.WEB_SITE +
                Constant.REQUEST_EXERCISES_URL).build();
        Call call = okHttpClient.newCall(request);
        // 开启异步线程访问网络
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws
                    IOException {
                String res = response.body().string(); //获取习题数据
                Message msg = new Message();
                msg.what = MSG_EXERCISES_OK;
                msg.obj = res;
                mHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Call call, IOException e){
            }
        });
    }
    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_EXERCISES_OK:
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        if (ebl!=null)ebl.clear();
                        //解析获取的JSON数据
                        ebl = JsonParse.getInstance().getExercisesList(vlResult);
                        adapter.setData(ebl);
                    }
                    break;
            }
        }
    }
    /**
     * 获取习题界面
     */
    public View getView() {
        if (mCurrentView == null) {
            initView(); //初始化界面控件
        }
        return mCurrentView;
    }
    /**
     * 显示习题界面
     */
    public void showView() {
        if (mCurrentView == null) {
            initView(); //初始化界面控件
        }
        mCurrentView.setVisibility(View.VISIBLE); //显示当前界面
    }
}
