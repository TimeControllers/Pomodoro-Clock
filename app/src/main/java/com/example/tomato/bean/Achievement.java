package com.example.tomato.bean;

import com.example.tomato.R;

import java.util.ArrayList;
import java.util.List;

public class Achievement {
    public int image;
    public String name;
    public String details;

    public Achievement(int image, String name, String details)   {
        this.image = image;
        this.name = name;
        this.details = details;

    }
    //定义储存数据的数组
    private static int[] iconArray={R.drawable.achievement,R.drawable.achievement,R.drawable.achievement,R.drawable.achievement,R.drawable.achievement,
            R.drawable.achievement,R.drawable.achievement,R.drawable.achievement};
    private static String[] nameArray = {"初来乍到","初出茅庐","牛刀小试","初窥门径","登堂入室","炉火纯青","出类拔萃","心静如水","出神入化",};
    private static String[] detailsArray = {"首次打开番茄钟","使用番茄钟超过五次","使用番茄钟超过十次","使用番茄钟超过二十次","使用番茄钟超过三十次","使用番茄钟超过五十次",
            "使用番茄钟超过一百次","使用番茄钟超过一百五次"};

    //构建上述三个数组的集合，方便使用
    public static List<Achievement> getDefaultList()   {
        List<Achievement> achievementList = new ArrayList<Achievement>();
        for (int i = 0;i < iconArray.length;i++)    {
            achievementList.add(new Achievement(iconArray[i],nameArray[i],detailsArray[i] ));
        }
        return achievementList;
    }
    public static List<Achievement> getNullDefaultList()   {
        List<Achievement> achievementList = new ArrayList<Achievement>();
        return achievementList;
    }
}
