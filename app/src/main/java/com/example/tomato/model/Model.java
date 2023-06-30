package com.example.tomato.model;

import android.util.Log;

public class Model {
    private int id,  timeMinute, timeSec;
    private boolean quit;

    private String task, date;

    public int getId() {
        return id;
    }

    public int getTimeSec() {
        return timeSec;
    }

    public void setTimeSec(int timeSec) {
        this.timeSec = timeSec;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTimeMinute() {
        return timeMinute;
    }

    public void setTimeMinute(int timeMinute) {
        Log.i("db TEsting", ""+timeMinute);
        this.timeMinute = timeMinute;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", timeMinute=" + timeMinute +
                ", timeSec=" + timeSec +
                ", quit=" + quit +
                ", task='" + task + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
