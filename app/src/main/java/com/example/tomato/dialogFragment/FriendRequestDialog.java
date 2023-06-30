package com.example.tomato.dialogFragment;


import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.fragment.app.DialogFragment;

import com.example.tomato.R;
import com.example.tomato.User;
import com.example.tomato.adapter.FriendListAdapter;
import com.example.tomato.friend.FriendInfo;
import com.example.tomato.tool.ServerHelper;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestDialog extends DialogFragment {
    private List<FriendInfo> list_friend = new ArrayList<FriendInfo>();
    private int[] images = {R.drawable.good, R.drawable.good, R.drawable.good,R.drawable.good,R.drawable.good};
    private String[] names = {"子鼠", "丑牛", "寅虎", "卯兔", "5"};
    private String[] emails = {"子鼠", "丑牛", "寅虎", "卯兔", "5"};
    private int[] scores = {6,4,5,3,4};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friendrequest, container, false);
        initFriends();
        FriendListAdapter adapter = new FriendListAdapter(getActivity(), R.layout.frienditem, list_friend);
        ListView lv_friend = view.findViewById(R.id.friendListView);
        lv_friend.setAdapter(adapter);
        ImageButton btn_addFriend=view.findViewById(R.id.addFriendButton);
        EditText et_search =view.findViewById(R.id.searchEditText);

        btn_addFriend.setOnClickListener(view1 -> {
            String fid=et_search.getText().toString();
            ServerHelper serverHelper =new ServerHelper();
            Log.i("RequestSend-uid", String.valueOf(User.getUid()));
            Log.i("fid",fid);

            serverHelper.sendFriendRequest(fid)
                    .thenAccept(complete -> {
                        Log.i("complete", String.valueOf(complete));
                        // 处理异步操作结果
                        if (complete != false) {
                            Log.i("发送", "成功");
                        } else {
                            //
                            Log.i("链接", "失败");
                        }
                    });


        });

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
