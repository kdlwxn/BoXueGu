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
import com.boxuegu.activity.VideoPlayActivity;
import com.boxuegu.bean.VideoBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class PlayHistoryAdapter extends RecyclerView.Adapter<PlayHistoryAdapter.
        MyViewHolder> {
    private Context mContext;
    private List<VideoBean> vbl;
    public PlayHistoryAdapter(Context context) {
        this.mContext = context;
    }
    public void setData(List<VideoBean> vbl) {
        this.vbl = vbl;            //接收传递过来的视频集合数据
        notifyDataSetChanged();  //刷新界面数据
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.
                play_history_list_item,null);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final VideoBean bean = vbl.get(position);                 //获取条目数据
        holder.tv_chapter_name.setText(bean.getChapterName()); //设置章节名称
        holder.tv_video_name.setText(bean.getVideoName());      //设置视频名称
        //设置视频图标
        Glide
                .with(mContext)
                .load(bean.getVideoIcon())
                .error(R.mipmap.ic_launcher)
                .into(holder.iv_icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到播放视频界面
                Intent intent=new Intent(mContext,VideoPlayActivity.class);
                intent.putExtra("videoPath", bean.getVideoPath());
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return vbl == null ? 0 : vbl.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_chapter_name, tv_video_name;
        ImageView iv_icon;
        public MyViewHolder(View view) {
            super(view);
            iv_icon = view.findViewById(R.id.iv_video_icon);
            tv_chapter_name = view.findViewById(R.id.tv_chapter_name);
            tv_video_name = view.findViewById(R.id.tv_video_name);
        }
    }
}

