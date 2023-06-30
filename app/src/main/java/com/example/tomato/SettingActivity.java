package com.example.tomato;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

    Button aboutBtn = findViewById(R.id.setting_about);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this,AboutActivity.class);
                startActivity(intent);
                }
        });
        Button feedBackBtn = findViewById(R.id.setting_feedBack);
        feedBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this,FeedBackActivity.class);
                startActivity(intent);
                }
        });
        Button versionUpdateBtn = findViewById(R.id.version_update);
        versionUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(SettingActivity.this, "目前版本已是最新版本", Toast.LENGTH_SHORT);
                toast.show();
                }
        });
        Button accountManageBtn = findViewById(R.id.setting_accountManage);
        accountManageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this,AccountManageActivity.class);
                startActivity(intent);
            }
        });
        Button individuation = findViewById(R.id.setting_individuation);
        individuation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this,IndividuationActivity.class);
                startActivity(intent);
            }
        });

    }
}
