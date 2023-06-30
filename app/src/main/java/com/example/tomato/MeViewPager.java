package com.example.tomato;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.example.tomato.dialogFragment.FriendDialog;

import java.util.ArrayList;
import java.util.List;

public class MeViewPager {
    private static Button btn_info, btn_friend, btn_achievement, btn_setting,btn_changeAvatar;
    private static ImageView ibtn_setting,avatar;
    private static TextView tv_name,tv_email,tv_score;
   private static Context context;
   private static final List<String> availableAvatarList = new ArrayList<>();
   private static int[] scoreThresholds = {0, 50, 100, 150};//分数阈值
   private static String[] avatarList = {"avatar1", "avatar2", "avatar3"};
    public static void init(MainActivity activity) {
        View rootView;
        rootView = MainActivity.getView2();
        btn_info=rootView.findViewById(R.id.infoButton);
        btn_friend=rootView.findViewById(R.id.btn_friend);
        btn_achievement=rootView.findViewById(R.id.btn_achievements);
        ibtn_setting=rootView.findViewById(R.id.ib_setting);
        btn_setting=rootView.findViewById(R.id.btn_setting);
        avatar=rootView.findViewById(R.id.imageView);
        tv_name=rootView.findViewById(R.id.user_name);
        tv_email=rootView.findViewById(R.id.user_email);
        tv_score=rootView.findViewById(R.id.user_score);
        btn_changeAvatar=rootView.findViewById(R.id.btn_changeAvatar);
        context =activity;
        MainActivity.iv_avatar=rootView.findViewById(R.id.imageView);
        for (int i = 0; i < avatarList.length; i++) {
            if (User.getScore() >= scoreThresholds[i]) {
                availableAvatarList.add(avatarList[i]);
            }
        }
        if(User.getEmail()!=null){
            btn_info.setVisibility(View.GONE);
            tv_name.setText(User.getName());
             tv_email.setText(User.getEmail());
             tv_score.setText(String.valueOf(User.getScore()));
            int avatarResourceId = context.getResources().getIdentifier("avatar1", "drawable", context.getPackageName());
            Drawable avatarDrawable = ContextCompat.getDrawable(context, avatarResourceId);
            // 设置用户的新头像
            avatar.setImageDrawable(avatarDrawable);
        }else{
            int avatarResourceId = context.getResources().getIdentifier("nologin", "drawable", context.getPackageName());
            Drawable avatarDrawable = ContextCompat.getDrawable(context, avatarResourceId);
            avatar.setImageDrawable(avatarDrawable);
        }

        btn_info.setOnClickListener(view -> {
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);

        });

        btn_friend.setOnClickListener(view -> {
            FriendDialog friendDialog = new FriendDialog();
            friendDialog.show(activity.getSupportFragmentManager(), "Friend_dialog");

        });

        btn_achievement.setOnClickListener(view -> {
            Intent intent = new Intent(activity, AchievementActivity.class);
            activity.startActivity(intent);
        });
        btn_setting.setOnClickListener(view -> {
            Intent intent = new Intent(activity,SettingActivity.class);
            activity.startActivity(intent);
        });
        ibtn_setting.setOnClickListener(view -> {
            Intent intent = new Intent(activity,SettingActivity.class);
            activity.startActivity(intent);
        });
        btn_changeAvatar.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("选择头像");

                // 根据分数确定可供选择的头像
                int availableAvatars = 0;
                for (int i = 0; i < avatarList.length; i++) {
                    if (User.getScore() >= scoreThresholds[i]) {
                        availableAvatarList.add(avatarList[i]);
                        availableAvatars++;
                    }
                }

                if (availableAvatars > 0) {
                    builder.setItems(availableAvatarList.toArray(new String[0]), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String selectedAvatar = availableAvatarList.get(which);
                            int avatarResourceId = context.getResources().getIdentifier(selectedAvatar, "drawable", context.getPackageName());

                            Drawable avatarDrawable = ContextCompat.getDrawable(context, avatarResourceId);
                            // 设置用户的新头像
                            avatar.setImageDrawable(avatarDrawable);
                            Log.i("选择","good");
                        }
                    });
                } else {
                    builder.setMessage("您的分数不足，无法选择头像！");
                }

                builder.show();

        });
    }



}

