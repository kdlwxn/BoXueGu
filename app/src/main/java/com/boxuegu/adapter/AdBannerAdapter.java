package com.boxuegu.adapter;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.boxuegu.bean.BannerBean;
import com.boxuegu.fragment.AdBannerFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * 广告栏的适配器
 */
public class AdBannerAdapter extends FragmentStatePagerAdapter {
    private List<BannerBean> bbl;
    public AdBannerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        bbl = new ArrayList<>();
    }

    public  void  setData(List<BannerBean> bbl) {
        this .bbl = bbl; //获取传递过来的广告数据
        notifyDataSetChanged(); //更新界面数据
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        if (bbl.size() > 0)
            args.putSerializable("ad",bbl.get(position % bbl.size()));
            return AdBannerFragment.newInstance(args);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    public  int  getSize() {
        return  bbl == null ? 0 : bbl.size();
    }
    @Override
    public  int  getItemPosition(@NonNull Object object) {
        return  POSITION_NONE;
    }
}
