package com.example.tomato;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OpenAccess {
    private Button OpenButton;

    public void initialize_button(MainActivity activity) {
        View rootView;
        rootView = MainActivity.getView1();
        OpenButton=rootView.findViewById(R.id.OpenButton);
        // 设置按钮点击事件监听器
        OpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // 检查是否设置了应用统计的使用权限
                    if (!isStatAccessPermissionSet(activity)) {
                        // 打开系统设置界面，用于设置权限
                       activity.startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
                        // 显示提示信息
                        Toast toast = Toast.makeText(activity, "请开启应用统计的使用权限", Toast.LENGTH_SHORT);
                        toast.show();
                    }else{
                        Toast toast = Toast.makeText(activity, "已开启", Toast.LENGTH_SHORT);
                        OpenButton.setVisibility(View.GONE);
                        toast.show();
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static boolean JudgmentAuthority(MainActivity activity) {
        try {
            // 检查是否设置了应用统计的使用权限
            if (isStatAccessPermissionSet(activity)) {
            return true;
            }else {
                Toast toast = Toast.makeText(activity, "请点击权限按钮开启权限", Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    // 检查是否设置了应用统计的使用权限
    static boolean isStatAccessPermissionSet(Context c) throws PackageManager.NameNotFoundException {
        PackageManager pm = c.getPackageManager();
        ApplicationInfo info = pm.getApplicationInfo(c.getPackageName(), 0);
        AppOpsManager aom = (AppOpsManager) c.getSystemService(Context.APP_OPS_SERVICE);

        // 检查指定操作的权限状态，返回结果为权限状态值
        aom.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, info.uid, info.packageName);

        // 检查权限状态值是否为已授权状态
        return aom.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, info.uid, info.packageName)
                == AppOpsManager.MODE_ALLOWED;
    }

}

