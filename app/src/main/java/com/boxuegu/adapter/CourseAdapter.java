package com.boxuegu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boxuegu.R;
import com.boxuegu.activity.CourseDetailActivity;
import com.boxuegu.bean.CourseBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {
    private Context mContext;
    private List<CourseBean> cbl;
    public CourseAdapter(Context context) {
        this.mContext = context;
    }
    public void setData(List<CourseBean> cbl) {
        this.cbl = cbl;            //获取课程集合数据
        notifyDataSetChanged();  //刷新界面信息
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext).inflate(
                R.layout.course_list_item,null);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final CourseBean bean = cbl.get(position);         //获取课程列表条目的数据
        holder.tv_title.setText(bean.getChapterName());   //设置章节名称
        Glide
                .with(mContext)
                .load(bean.getChapterImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.iv_img);
        holder.iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到课程详情界面
                Intent intent = new Intent(mContext, CourseDetailActivity.class);
                intent.putExtra("CourseBean", bean);
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return cbl == null ? 0 : cbl.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView iv_img;
        public MyViewHolder(View view) {
            super(view);
            iv_img = view.findViewById(R.id.iv_img);
            tv_title = view.findViewById(R.id.tv_title);
        }
    }
}

