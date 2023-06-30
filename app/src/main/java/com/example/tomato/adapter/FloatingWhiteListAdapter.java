package com.example.tomato.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tomato.FloatingWindow;
import com.example.tomato.MainActivity;
import com.example.tomato.R;
import com.example.tomato.appUsage.AppInformation;
import com.example.tomato.appUsage.StatisticsInfo;

import java.util.ArrayList;
import java.util.List;

public class FloatingWhiteListAdapter extends RecyclerView.Adapter<FloatingWhiteListAdapter.RecyclerViewHolder> {

    private ArrayList<AppInformation> appInformations;
    private Context mcontext;



    ArrayList<Integer> list = new ArrayList<>();
    private int index = 0;

    public FloatingWhiteListAdapter(ArrayList<AppInformation> appInformations, Context mcontext) {
        this.appInformations =appInformations;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_card, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        int x = Color.parseColor("#E6E6FA");
        int pos = position;

//        list.add(holder.item);

        AppInformation appInfo = appInformations.get(position);
        holder.icon.setImageDrawable(appInfo.getIcon());
        holder.appName.setText(appInfo.getLabel());
//            holder.item.setOnClickListener();
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String packageName = appInfo.getPackageName();
                String appName = appInfo.getLabel();
                PackageManager packageManager = mcontext.getPackageManager();
                Intent launchIntent = packageManager.getLaunchIntentForPackage(packageName);

                if (launchIntent != null) {
                    mcontext.startActivity(launchIntent);
                } else {
                    Toast.makeText(mcontext, "无法启动应用程序：" + appName, Toast.LENGTH_SHORT).show();
                }
            }


        });


    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return appInformations.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView appName;
        private ImageView icon;
        private CardView item;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.appName);
            icon = itemView.findViewById(R.id.icon_wl);
            item = itemView.findViewById(R.id.wlItem);

        }
    }
}

