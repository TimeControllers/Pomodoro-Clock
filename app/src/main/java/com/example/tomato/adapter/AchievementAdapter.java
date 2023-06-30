package com.example.tomato.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tomato.R;
import com.example.tomato.bean.Achievement;

import java.util.List;

public class AchievementAdapter extends BaseAdapter {
    private Context mContext;
    private List<Achievement> mAchievement;
    public AchievementAdapter(Context mContext,List<Achievement> mAchievement){
        this.mContext = mContext;
        this.mAchievement = mAchievement;
    }
    @Override
    public int getCount() {
        return mAchievement.size();
    }

    @Override
    public Object getItem(int position) {
        return mAchievement.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //设置视图文件
        View view = LayoutInflater.from(mContext).inflate(R.layout.achievement_item,null);
        TextView tv_title = view.findViewById(R.id.tv_title);
        ImageView iv_icon = view.findViewById(R.id.iv_icon);
        TextView tv_condition = view.findViewById(R.id.tv_condition);
        //给各个部件设置好数据
        Achievement achievement =   mAchievement.get(position);
        tv_title.setText(achievement.name);
        iv_icon.setImageResource(achievement.image);
        tv_condition.setText(achievement.details);
        return view;
    }

}
