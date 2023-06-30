package com.example.tomato;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tomato.appUsage.AppInformation;
import com.example.tomato.appUsage.StatisticsInfo;
import com.example.tomato.util.DatabaseHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordPageInfo  {
    private  int style; // 统计样式，用于表示统计是按天、周、月还是年进行
    private long totalTime; // 总运行时间
    private int totalTimes; // 总操作次数
    private Button buttonDay,buttonMonth,buttonYear;
    private ListView listView;
    private final Context context;
    private TextView tv_lock_time,tv_lock_times;
    DatabaseHandler db;

    OpenAccess openAccess = new OpenAccess();

    public RecordPageInfo(Context context) {
        this.context = context;
    }

    protected void init(MainActivity activity) {
        View rootView;
        rootView = MainActivity.getView1();

        buttonDay = rootView.findViewById(R.id.daybuttonlist3);
        buttonMonth = rootView.findViewById(R.id.monthbuttonlist3);
        buttonYear = rootView.findViewById(R.id.yearbuttonlist3);
        listView = rootView.findViewById(R.id.AppStatisticsList);
        tv_lock_time = rootView.findViewById(R.id.tv_lock_time);
        tv_lock_times = rootView.findViewById(R.id.tv_lock_times);
        db = new DatabaseHandler(activity);
        db.openDatabase();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        int lock_times = db.getStats(true,true,dtf.format(now));
        int lock_time = db.getStats(false,true,dtf.format(now));
        tv_lock_time.setText(""+lock_time);
        tv_lock_times.setText(""+lock_times);
        // 获取视图元素
        this.style = StatisticsInfo.DAY;
        Refresh(activity);
        buttonDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style != StatisticsInfo.DAY) {
                    Log.i("test","reset");
                    style = StatisticsInfo.DAY;
                    openAccess.JudgmentAuthority(activity);
                    Refresh(activity);
                }
            }
        });

        buttonMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style != StatisticsInfo.MONTH) {
                    style = StatisticsInfo.MONTH;
                    openAccess.JudgmentAuthority(activity);
                    Refresh(activity);
                }
            }
        });
        buttonYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style != StatisticsInfo.YEAR) {
                    style = StatisticsInfo.YEAR;
                    Toast toast = Toast.makeText(activity, "nian", Toast.LENGTH_SHORT);
                    toast.show();
                    openAccess.JudgmentAuthority(activity);
                    Refresh(activity);
                }
            }
        });
    }

    // 设置按钮颜色
    private void SetButtonColor() {


        Log.i("set","start");
        // 设置按钮文字颜色为白色
        buttonDay.setTextColor(Color.BLUE);
        buttonMonth.setTextColor(Color.BLUE);
        buttonYear.setTextColor(Color.BLUE);

        // 根据统计样式设置相应按钮的文字颜色
        switch (style) {
            case StatisticsInfo.DAY:
                buttonDay.setTextColor(Color.BLACK);
                break;
            case StatisticsInfo.MONTH:
                buttonMonth.setTextColor(Color.BLACK);
                break;
            case StatisticsInfo.YEAR:
                buttonYear.setTextColor(Color.BLACK);
                break;
        }

    }


    // 每次重新进入界面的时候加载listView
    public void Refresh(MainActivity activity) {
        if (openAccess.JudgmentAuthority(activity)) {
            SetButtonColor();
            List<Map<String, Object>> dataList = null;
            // 创建 StatisticsInfo 对象，根据统计样式获取相应的统计信息
            StatisticsInfo statisticsInfo = new StatisticsInfo(context, this.style);
            totalTime = statisticsInfo.getTotalTime();
            totalTimes = statisticsInfo.getTotalTimes();
            dataList = getDataList(statisticsInfo.getShowList());

            SimpleAdapter adapter = new SimpleAdapter(context, dataList, R.layout.inner_list,
                    new String[]{"label", "info", "times", "icon"},
                    new int[]{R.id.label, R.id.info, R.id.times, R.id.icon});
            listView.setAdapter(adapter);

            // 设置视图绑定器，用于设置图标的显示
            adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Object o, String s) {
                    if (view instanceof ImageView && o instanceof Drawable) {
                        ImageView iv = (ImageView) view;
                        iv.setImageDrawable((Drawable) o);
                        return true;
                    } else return false;
                }
            });
        }
    }

    // 获取数据列表
        public List<Map<String, Object>> getDataList (ArrayList < AppInformation > ShowList) {
            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

        // 添加全部应用的统计信息
        Map<String, Object> map = new HashMap<String, Object>();
            // 添加每个应用的统计信息
            for (AppInformation appInformation : ShowList) {
                map = new HashMap<String, Object>();
                map.put("label", appInformation.getLabel());
                map.put("info", "运行时间: " + DateUtils.formatElapsedTime(appInformation.getUsedTimeByDay() / 1000));
                map.put("times", "本次开机操作次数: " + appInformation.getTimes());
                map.put("icon", appInformation.getIcon());
                dataList.add(map);
            }

            return dataList;
        }


}

