package com.example.tomato;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartActivity extends Activity  {
    //柱状图
    private static BarChart barChart;


    //lists
    static ArrayList<BarEntry> barEntries = new ArrayList<>();
    static ArrayList<PieEntry> pieEntries = new ArrayList<>();
    //初始化
    public static void initialize_chart(MainActivity activity){
        View rootView;
        rootView = MainActivity.getView1();

        barChart = rootView.findViewById(R.id.bar_chart);

    //use for loop
    for (int i = 1; i < 10; i++) {
        // 将值转换为浮点型
        float value = (float) (i * 10.0);
        // 初始化 BarEntry 对象
        BarEntry barEntry = new BarEntry(i, value);
        // 初始化 PieEntry 对象
        PieEntry pieEntry = new PieEntry(i, value);
        // 将对象添加到数组列表中
        barEntries.add(barEntry);
        pieEntries.add(pieEntry);
    }


    //Initialize bat date set
    BarDataSet barDataSet =new BarDataSet(barEntries,"time");
    //set colors
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    //Hide draw value
        barDataSet.setDrawValues(false);
    //Set bar data
        barChart.setData(new BarData(barDataSet));
    //set animation
        barChart.animateY(5000);
    //Set description text and color
        barChart.getDescription().setText("time");
        barChart.getDescription().setTextColor(Color.BLUE);


    }
}
