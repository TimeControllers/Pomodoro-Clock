package com.example.tomato.dialogFragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import androidx.fragment.app.DialogFragment;

import com.example.tomato.MainActivity;
import com.example.tomato.R;
import com.example.tomato.adapter.FriendListAdapter;
import com.example.tomato.firend.FriendInfo;

import java.util.ArrayList;
import java.util.List;

public class FriendDialog extends DialogFragment {
    private List<FriendInfo> list_friend = new ArrayList<FriendInfo>();
    private int[] images = {R.drawable.good, R.drawable.good, R.drawable.good,R.drawable.good,R.drawable.good};
    private String[] names = {"子鼠", "丑牛", "寅虎", "卯兔", "5"};
    private String[] emails = {"子鼠", "丑牛", "寅虎", "卯兔", "5"};
    private int[] scores = {6,4,5,3,4};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend, container, false);
        initFriends();
        FriendListAdapter adapter = new FriendListAdapter(getActivity(), R.layout.frienditem, list_friend);
        ListView lvAnimals = view.findViewById(R.id.friendListView);
        lvAnimals.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 设置弹窗的宽度和高度
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.66);
        layoutParams.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.66);
        // 设置弹窗的位置
        getDialog().getWindow().setGravity(Gravity.CENTER);
        getDialog().getWindow().setAttributes(layoutParams);
    }




    private void initFriends() {
        for (int i = 0; i < names.length; i++) {
            FriendInfo friendInfo = new FriendInfo(images[i], names[i],emails[i],scores[i] );
            list_friend.add(friendInfo);
        }
    }
}
