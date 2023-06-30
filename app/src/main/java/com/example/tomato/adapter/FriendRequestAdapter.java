package com.example.tomato.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tomato.FUser;
import com.example.tomato.R;
import com.example.tomato.friend.FriendInfo;
import com.example.tomato.tool.ServerHelper;

import java.util.ArrayList;
import java.util.List;


public class FriendRequestAdapter extends ArrayAdapter<FriendInfo> {
    private ArrayList<FUser> friendList;
    private int resourceId;
    public FriendRequestAdapter( Context context, int resource,  List<FriendInfo> objects) {
        super(context, resource, objects);
        resourceId = resource;
        ServerHelper serverHelper=new ServerHelper();
        Log.i("showFriend","ready" );
        serverHelper.getFriendRequest()
                .thenAccept(complete -> {
                    Log.i("showFriend","start" );
                    Log.i("complete", String.valueOf(complete));
                    if (complete != null) {
                                     friendList = (ArrayList<FUser>) complete;
                                    for(FUser user:friendList){
                                        Log.i("friendInfo",user.getEmail());
                                    }
                        Log.i("showFriend","yes" );

                    }
                    else {
                        Log.i("登录", "失败");
                    }});
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        FriendInfo friendInfo = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView ivFriend = (ImageView) view.findViewById(R.id.icon);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        TextView tv_email = (TextView) view.findViewById(R.id.tv_email);
        if (friendList != null) {
            if (position >= 0 && position < friendList.size()) {
                FUser user = friendList.get(position);
                if (user!=null) {
                    ivFriend.setImageResource(friendInfo.getIcon());
                    tv_name.setText(user.getName());
                    tv_email.setText(user.getEmail());
                }
            }

        }


        return view;
    }
}
