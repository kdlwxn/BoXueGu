package com.boxuegu.adapter;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boxuegu.R;
import com.boxuegu.bean.VideoBean;
import java.util.List;
public class CourseDetailAdapter extends RecyclerView.Adapter<CourseDetailAdapter.MyViewHolder> {
    private Context mContext;
    private List<VideoBean> vbl;        //视频列表数据
    private int selectedPosition = -1; //点击时选中的列表条目位置
    private OnSelectListener onSelectListener;
    public CourseDetailAdapter(Context context, OnSelectListener onSelectListener) {
        this.mContext = context;
        this.onSelectListener = onSelectListener;
    }
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }
    public void setData(List<VideoBean> vbl) {
        this.vbl = vbl;         	// 接收传递过来的视频列表数据
        notifyDataSetChanged();	// 刷新界面数据
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext).inflate(
                R.layout.course_detail_list_item,null);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final VideoBean bean = vbl.get(position);
        holder.iv_icon.setImageResource(R.drawable.course_detail_list_icon);
        holder.tv_name.setTextColor(Color.parseColor("#333333"));
        if (bean != null) {
            holder.tv_name.setText(bean.getVideoName());
            // 设置条目被选中时的效果
            if (selectedPosition == position) {
                holder.iv_icon.setImageResource(R.drawable.course_intro_icon);
                holder.tv_name.setTextColor(Color.parseColor("#009958"));
            } else {
                holder.iv_icon.setImageResource(R.drawable.course_detail_list_icon);
                holder.tv_name.setTextColor(Color.parseColor("#333333"));
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean == null)return;
                onSelectListener.onSelect(position, holder.iv_icon);
            }
        });
    }
    @Override
    public int getItemCount() {
        return vbl == null ? 0 : vbl.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView iv_icon;
        public MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_video_name);
            iv_icon = view.findViewById(R.id.iv_left_icon);
        }
    }
    public interface OnSelectListener {
        void onSelect(int position, ImageView iv);
    }
}
