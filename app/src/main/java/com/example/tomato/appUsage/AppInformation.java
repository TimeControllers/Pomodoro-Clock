package com.example.tomato.appUsage;

import android.app.usage.UsageStats;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;


public class AppInformation {
    private UsageStats usageStats;
    private String packageName;
    private String label;
    private Drawable Icon;
    private long UsedTimeByDay;  //milliseconds
    private Context context;
    private int times;

    private boolean onWhitelist = false;

    //  构造函数同时对异常信息进行处理，在控制台打印输出
    public AppInformation(UsageStats usageStats, Context context) {
        this.usageStats = usageStats;
        this.context = context;

        try {
            GenerateInfo();
        } catch (PackageManager.NameNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
        //初始化init
    private void GenerateInfo() throws PackageManager.NameNotFoundException, NoSuchFieldException, IllegalAccessException {
        //获取包名
        PackageManager packageManager = context.getPackageManager();
        this.packageName = usageStats.getPackageName();
        Log.i("getPackageName",usageStats.getPackageName());

        if (this.packageName != null && !this.packageName.equals(""))
       {        Log.i("appConfig",packageName+"---start");

            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.packageName, 0);
            Log.i("GenerateInfo-applicationInfo", applicationInfo != null ? "ApplicationInfo found" : "ApplicationInfo not found");

            //获取应用信息
            this.label = (String) packageManager.getApplicationLabel(applicationInfo);
            Log.i("GenerateInfo-label",(String) packageManager.getApplicationLabel(applicationInfo));

            //前台使用总时长
            this.UsedTimeByDay = usageStats.getTotalTimeInForeground();
            //使用次数
            this.times = (Integer) usageStats.getClass().getDeclaredField("mLaunchCount").get(usageStats);
            Log.i("GenerateInfo-timeByDay", String.valueOf(usageStats.getTotalTimeInForeground()));

            if (this.UsedTimeByDay > 0) {
                this.Icon = applicationInfo.loadIcon(packageManager);
            }
        } Log.i("GenerateInfo","over"+usageStats.getPackageName());
    }

    public UsageStats getUsageStats() {
        return usageStats;
    }

    public boolean isOnWhitelist() {
        return onWhitelist;
    }

    public void setOnWhitelist(boolean onWhitelist) {
        this.onWhitelist = onWhitelist;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setUsedTimeByDay(long usedTimeByDay) {

        this.UsedTimeByDay = usedTimeByDay;

        this.UsedTimeByDay = usedTimeByDay;
    }

    public Drawable getIcon() {
        return Icon;
    }

    public long getUsedTimeByDay() {

        return UsedTimeByDay;


    }

    public String getLabel() {
        return label;
    }

    public String getPackageName() {
        return packageName;
    }

    private long timeStampMoveToForeground = -1;

    private long timeStampMoveToBackGround = -1;


    public void setTimeStampMoveToForeground(long timeStampMoveToForeground) {
        this.timeStampMoveToForeground = timeStampMoveToForeground;
    }

    public void timesPlusPlus(){
        times++;
    }

    public void setTimeStampMoveToBackGround(long timeStampMoveToBackGround) {
        this.timeStampMoveToBackGround = timeStampMoveToBackGround;
    }

    public long getTimeStampMoveToBackGround() {
        return timeStampMoveToBackGround;
    }

    public long getTimeStampMoveToForeground() {
        return timeStampMoveToForeground;
    }
    //用来计算程序的运行时间
    public void calculateRunningTime() {

        if (timeStampMoveToForeground < 0 || timeStampMoveToBackGround < 0) {
            return;
        }

        if (timeStampMoveToBackGround > timeStampMoveToForeground) {
            UsedTimeByDay += (timeStampMoveToBackGround - timeStampMoveToForeground);
            timeStampMoveToForeground = -1;
            timeStampMoveToBackGround = -1;
        }

    }

    public static long bootTime() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }


}
