package com.example.tomato;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tomato.adapter.AchievementAdapter;
import com.example.tomato.bean.Achievement;
import com.example.tomato.util.DatabaseHandler;

import java.util.List;

public class AchievementActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private GridView gv_achievement,gv_achievement_not;
    private List<Achievement>   achievementList,achievementListNot;
    private static DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHandler(this);
        db.openDatabase();
        db.getStats(true,false,null);
        setContentView(R.layout.activity_achievement);
        gv_achievement = findViewById(R.id.gv_achievement);
        gv_achievement_not = findViewById(R.id.gv_achievement_not);
        achievementListNot = Achievement.getDefaultList();
        achievementList = Achievement.getNullDefaultList();
        getAchievement();
        AchievementAdapter adapter1 = new AchievementAdapter(this,achievementList);
        AchievementAdapter adapter2 = new AchievementAdapter(this,achievementListNot);
        gv_achievement.setAdapter(adapter1);
        gv_achievement_not.setAdapter(adapter2);
        gv_achievement.setHorizontalSpacing(5);
        gv_achievement.setVerticalSpacing(5);
        gv_achievement_not.setHorizontalSpacing(5);
        gv_achievement_not.setVerticalSpacing(5);

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

    public void getAchievement(){
        if (db.getStats(false,false,null)==1)
        {
            Achievement firstAchievement = achievementListNot.remove(0);
            achievementList.add(firstAchievement);
        }else if (db.getStats(false,false,null) == 2 ){
            Achievement secAchievement = achievementListNot.remove(0);
            achievementList.add(secAchievement);
        }else if (db.getStats(false,false,null)== 3){
            Achievement thirdAchievement = achievementListNot.remove(0);
            achievementList.add(thirdAchievement);
        }else if (db.getStats(false,false,null) == 4){
            Achievement fourthAchievement = achievementListNot.remove(0);
            achievementList.add(fourthAchievement);
        }else if (db.getStats(false,false,null) == 5){
            Achievement fifthAchievement = achievementListNot.remove(0);
            achievementList.add(fifthAchievement);
        }else if (db.getStats(false,false,null) == 50 ){
            Achievement sixthAchievement = achievementListNot.remove(0);
            achievementList.add(sixthAchievement);
        }else if (db.getStats(false,false,null) == 60){
            Achievement seventhAchievement = achievementListNot.remove(0);
            achievementList.add(seventhAchievement);
        }else if (db.getStats(false,false,null) == 70){
            Achievement eighthAchievement = achievementListNot.remove(0);
            achievementList.add(eighthAchievement);
       }

    }

}