package com.boxuegu.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.boxuegu.R;
import com.boxuegu.activity.ExercisesDetailActivity;
import com.boxuegu.bean.ExercisesBean;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ExercisesBean> ExercisesList;

    public ExercisesAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<ExercisesBean> ExercisesList) {
        this.ExercisesList = ExercisesList; //接收传递过来的习题列表数据
        notifyDataSetChanged();            //刷新界面数据
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.
                exercises_list_item, parent, false);
        RecyclerView.ViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,
                                 int position) {
        final ExercisesBean bean = ExercisesList.get(position);
        if (bean != null) {
            ((MyViewHolder) holder).tv_order.setText(position + 1 + "");
            ((MyViewHolder) holder).tv_chapterName.setText(bean.getChapterName());
            ((MyViewHolder) holder).tv_totalNum.setText("共计" + bean.getTotalNum() + "题");
            switch (bean.getBackground()) {
                case 1:
                    ((MyViewHolder) holder).tv_order.setBackgroundResource(
                            R.drawable.exercises_bg_1);
                    break;
                case 2:
                    ((MyViewHolder) holder).tv_order.setBackgroundResource(
                            R.drawable.exercises_bg_2);
                    break;
                case 3:
                    ((MyViewHolder) holder).tv_order.setBackgroundResource(
                            R.drawable.exercises_bg_3);
                    break;
                case 4:
                    ((MyViewHolder) holder).tv_order.setBackgroundResource(
                            R.drawable.exercises_bg_4);
                    break;
            }
        }
        //每个列表条目的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean == null) return;
                //跳转到习题详情界面
                Intent intent = new Intent(mContext, ExercisesDetailActivity.class);
                //将习题数据传递到习题详情界面中
                intent.putExtra("detailList",bean);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return ExercisesList == null ? 0 : ExercisesList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_order, tv_chapterName, tv_totalNum;

        public MyViewHolder(View view) {
            super(view);
            tv_order = view.findViewById(R.id.tv_order);
            tv_chapterName = view.findViewById(R.id.tv_chapterName);
            tv_totalNum = view.findViewById(R.id.tv_totalNum);
        }
    }
}
