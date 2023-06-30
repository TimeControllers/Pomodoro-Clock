//package com.example.tomato.tool;
//
//import android.app.ActivityManager;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Handler;
//import android.os.IBinder;
//
//import java.util.List;
//
//public class AppCheckService extends Service {
//    private Handler handler;
//    private Runnable runnable;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        handler = new Handler();
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//
//
//                Intent intent = new Intent("com.example.app.EXITED");
//                sendBroadcast(intent);
//
//                // 重复执行定时任务
//                handler.postDelayed(this, 1000); // 1秒钟执行一次，可根据需要调整时间间隔
//            }
//        };
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        // 启动定时任务
//        handler.postDelayed(runnable, 1000); // 1秒钟后执行任务，可根据需要调整延迟时间
//        return START_STICKY;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        // 停止定时任务
//        handler.removeCallbacks(runnable);
//    }
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//    private BroadcastReceiver appExitedReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals("com.example.app.EXIT")||intent.getAction().equals(com.example.app.BACKGROUND)) {
//                // 将悬浮窗口置顶的操作
//            }
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // 注册广播接收器
//        IntentFilter intentFilter = new IntentFilter();
//     intentFilter.addAction("com.example.app.BACKGROUND");
//    intentFilter.addAction("com.example.app.EXIT");
//        intentFilter.addAction("com.example.app.EXITED");
//        registerReceiver(appExitedReceiver, intentFilter);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // 注销广播接收器
//        unregisterReceiver(appExitedReceiver);
//    }
//}

//public class AppCheckService extends Service {
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//
//        String currentAppPackageName = getCurrentAppPackageName();
//
//        // 在这里与用户设置的白名单进行比较，判断应用程序是否在白名单中
//        // 在这里进行检查当前运行的应用程序是否在白名单中的逻辑
//        // 如果不在白名单中，可以采取相应的措施，如显示覆盖层或禁用应用程序
//        return START_NOT_STICKY;
//
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    // 获取当前正在运行的应用程序的包名
//    private String getCurrentAppPackageName() {
//        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        if (am != null) {
//            List<ActivityManager.RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
//            if (appProcesses != null && !appProcesses.isEmpty()) {
//                return appProcesses.get(0).processName;
//            }
//        }
//        return "";
//    }
//}
//打开app
//String packageName = "com.example.package"; // 替换为被点击应用程序的包名
//Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
//if (launchIntent != null) {
//    launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    startActivity(launchIntent);
//}我要实现锁机，帮我整理代码，我要有个悬浮窗，在打开app之后，对app做监听，app关闭就回到悬浮窗
//
//
//import android.app.ActivityManager;
//import android.app.Service;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.graphics.PixelFormat;
//import android.os.Handler;
//import android.os.IBinder;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.WindowManager;
//
//public class AppCheckService extends Service {
//    private Handler handler;
//    private BroadcastReceiver appExitedReceiver;
//    private WindowManager windowManager;
//    private View floatingView;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        handler = new Handler();
//        String packageName;
//        appExitedReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                if (intent.getAction().equals(packageName+".EXITED")) {
//                    // 处理应用退出的逻辑
//                    // 启动悬浮窗口
//                    showFloatingWindow();
//                }
//            }
//        };
//
//
//
//        // 将悬浮窗口添加到窗口管理器中
//        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
//        windowManager.addView(floatingView, params);
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        // 注册appExitedReceiver以监听应用退出事件
//        IntentFilter intentFilter = new IntentFilter("com.example.app.EXITED");
//        registerReceiver(appExitedReceiver, intentFilter);
//
//        return START_STICKY;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        // 移除appExitedReceiver
//        unregisterReceiver(appExitedReceiver);
//
//        // 从窗口管理器中移除悬浮窗口
//        if (floatingView != null && windowManager != null) {
//            windowManager.removeView(floatingView);
//        }
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    // 显示悬浮窗口
//    private void showFloatingWindow() {
//        // 在此处理显示悬浮窗口的逻辑
//    }
//}
//        package com.example.tomato.tool;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.Handler;
//import android.os.IBinder;
//
//public class AppCheckService extends Service {
//    private Handler handler;
//    private Runnable runnable;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        handler = new Handler();
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//
//
//                Intent intent = new Intent("com.example.app.EXITED");
//                sendBroadcast(intent);
//
//                // 重复执行定时任务
//                handler.postDelayed(this, 1000); // 1秒钟执行一次，可根据需要调整时间间隔
//            }
//        };
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        // 注册appExitedReceiver以监听应用退出事件
//        IntentFilter intentFilter = new IntentFilter(packageName+".EXITED");
//        registerReceiver(appExitedReceiver, intentFilter);
//
//        // 启动定时任务
//        handler.postDelayed(runnable, 1000); // 1秒钟后执行任务，可根据需要调整延迟时间
//        return START_STICKY;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(appExitedReceiver);
//
//        // 从窗口管理器中移除悬浮窗口
//        if (floatingView != null && windowManager != null) {
//            windowManager.removeView(floatingView);
//        }
//    }
//
//    @Nullable
//        // 停止定时任务
//        handler.removeCallbacks(runnable);
//    }
//        // 停止定时任务
//        handler.removeCallbacks(runnable);
//    }
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
////    @Override
////   public IBinder onBind(Intent intent) {
////        return null;
////    }
//
//    // 显示悬浮窗口
//    private void showFloatingWindow() {
//        // 在此处理显示悬浮窗口的逻辑
//    }
////    private BroadcastReceiver appExitedReceiver = new BroadcastReceiver() {
////        @Override
////        public void onReceive(Context context, Intent intent) {
////            if (intent.getAction().equals("com.example.app.EXIT")||intent.getAction().equals(com.example.app.BACKGROUND)) {
////                // 将悬浮窗口置顶的操作
////            }
////        }
////    };
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        // 注册广播接收器
////        IntentFilter intentFilter = new IntentFilter();
////     intentFilter.addAction("com.example.app.BACKGROUND");
////    intentFilter.addAction("com.example.app.EXIT");
////        intentFilter.addAction("com.example.app.EXITED");
////        registerReceiver(appExitedReceiver, intentFilter);
////    }
////
////    @Override
////    protected void onDestroy() {
////        super.onDestroy();
////        // 注销广播接收器
////        unregisterReceiver(appExitedReceiver);
////    }
//}
//
////public class AppCheckService extends Service {
////    @Override
////    public int onStartCommand(Intent intent, int flags, int startId) {
////
////
////        String currentAppPackageName = getCurrentAppPackageName();
////
////        // 在这里与用户设置的白名单进行比较，判断应用程序是否在白名单中
////        // 在这里进行检查当前运行的应用程序是否在白名单中的逻辑
////        // 如果不在白名单中，可以采取相应的措施，如显示覆盖层或禁用应用程序
////        return START_NOT_STICKY;
////
////    }
////
////    @Override
////    public IBinder onBind(Intent intent) {
////        return null;
////    }
////
////    // 获取当前正在运行的应用程序的包名
////    private String getCurrentAppPackageName() {
////        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
////        if (am != null) {
////            List<ActivityManager.RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
////            if (appProcesses != null && !appProcesses.isEmpty()) {
////                return appProcesses.get(0).processName;
////            }
////        }
////        return "";
////    }
////}
////打开app
////String packageName = "com.example.package"; // 替换为被点击应用程序的包名
////Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
////if (launchIntent != null) {
////    launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////    startActivity(launchIntent);
////}我要实现锁机，帮我整理代码，我要有个悬浮窗，在打开app之后，对app做监听，app关闭就回到悬浮窗
//}
