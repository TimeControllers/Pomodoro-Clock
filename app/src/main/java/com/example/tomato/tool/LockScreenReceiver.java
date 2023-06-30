package com.example.tomato.tool;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Arrays;
import java.util.List;

public class LockScreenReceiver extends BroadcastReceiver {
    // 定义白名单列表
    private List<String> whitelist = Arrays.asList("com.example.app1", "com.example.app2");

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals(Intent.ACTION_SCREEN_OFF)) {
            // 获取当前运行的应用程序包名
            String currentPackageName = getForegroundPackageName(context);
            // 检查当前包名是否在白名单中
            if (whitelist.contains(currentPackageName)) {
                // 在白名单中，执行逻辑
                // 启动服务来检查当前运行的应用程序是否在白名单中
            }
        }
    }

    // 获取当前运行的应用程序包名
    private String getForegroundPackageName(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processes = manager.getRunningAppProcesses();
        if (processes != null && !processes.isEmpty()) {
            return processes.get(0).processName;
        }
        return "";
    }
}
