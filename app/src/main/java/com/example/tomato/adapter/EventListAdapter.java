package com.example.tomato.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tomato.MainActivity;
import com.example.tomato.R;
import com.example.tomato.Timer;
import com.example.tomato.model.Model;
import com.example.tomato.util.DatabaseHandler;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
    private List<Model> eventsList;
    private MainActivity activity;
    private DatabaseHandler db;

    public EventListAdapter(DatabaseHandler db, MainActivity activity){
        this.db = db;
        this.activity = activity;

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_layout, parent, false);
        return new ViewHolder(itemView);

    }

    public void deleteItem(int position){
        Model item = eventsList.get(position);
        db.deleteTask(item.getId());
        eventsList.remove(position);
        notifyItemRemoved(position);
    }

    public void onBindViewHolder( ViewHolder holder, int position) {
        //db.openDatabase();
        int pos = position;
        Model item = eventsList.get(position);
        holder.eventName.setText(item.getTask());
        holder.eventTime.setText(item.getTimeMinute()+" minutes "+item.getTimeSec()+" seconds");
        holder.setTime.setOnClickListener(new View.OnClickListener() {

            TextView time = MainActivity.getTimer();
            TextView btn_start = MainActivity.getBtnT();
            ProgressBar progressBar = MainActivity.getPB();
            @Override
            public void onClick(View view) {
                long milliseconds = (item.getTimeMinute()*60000)+(item.getTimeSec()*1000);
                if(Timer.isTimerRunning()){
                    Toast.makeText(activity,"Timer is still running",Toast.LENGTH_SHORT).show();
                }else{

                    MainActivity.setEid(pos);
                    Log.i("DATABASEEEEE", "This is the position that the item is going by so this is the local list? "+ pos);

                    MainActivity.setEventName(item.getTask());
                    String timeLeftFormatted = String.format("%02d:%02d", item.getTimeMinute(), item.getTimeSec());
                    time.setText(timeLeftFormatted);
                    MainActivity.setTimeMili(milliseconds);
                    progressBar.setProgress(0);


                }


            }
        });

        holder.event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.createEvent(activity,true,pos);
            }
        });

    }

    public int getItemCount(){
        return  eventsList.size();
    }

    //idk wut this does probably don't need it but it's here just in case :)
    private boolean toBoolean(int n){
        return n!=0;
    }

    public void setEvent (List<Model> eventsList){
        this.eventsList = eventsList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView event;
        TextView eventName;
        TextView eventTime;
        Button setTime;

        ViewHolder(View view){
            super(view);
            event = view.findViewById(R.id.eventItem);
            eventName = view.findViewById(R.id.eventName);
            eventTime = view.findViewById(R.id.eventTime);
            setTime = view.findViewById(R.id.btn_set);
        }
    }
}