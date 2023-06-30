package com.example.tomato.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tomato.MainActivity;
import com.example.tomato.R;
import com.example.tomato.appUsage.AppInformation;
import com.example.tomato.appUsage.StatisticsInfo;

import java.util.ArrayList;
import java.util.List;

public class WhiteListAdapter extends RecyclerView.Adapter<WhiteListAdapter.RecyclerViewHolder> {

    private ArrayList<AppInformation> appInformations;
    private Context mcontext;


    ArrayList<Integer> list = new ArrayList<>();
    private int index = 0;

    public WhiteListAdapter(ArrayList<AppInformation> appInformations, Context mcontext) {
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
//

                    index = position;
                    list.add(index);
                    if(MainActivity.whiteList.contains(index)){
                        MainActivity.whiteList.remove(MainActivity.whiteList.indexOf(index));
                        Log.i("AHHH","AAAHHHHHHHHHHHHHHHHHHHHHHHHH");
                        MainActivity.whiteListApp.remove(appInformations.get(index));


                    }else{
                        MainActivity.whiteList.add(index);
                        MainActivity.whiteListApp.add(appInformations.get(index));

                    }
                    notifyDataSetChanged();

                    //MainActivity.addWl(appInformations.get(position));


                }


            });

            if(MainActivity.whiteList.contains(position)){
                holder.item.setCardBackgroundColor(mcontext.getResources().getColor(R.color.lavender));
            }else{
                holder.item.setCardBackgroundColor(mcontext.getResources().getColor(R.color.white));
            }


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

