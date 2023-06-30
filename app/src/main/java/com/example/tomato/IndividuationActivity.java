package com.example.tomato;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IndividuationActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individuation);
        RadioGroup theme = findViewById(R.id.theme);
        theme.setOnCheckedChangeListener(this);
        Switch nightPattern = findViewById(R.id.night_pattern);
        nightPattern.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.theme_normal) {
            Toast toast = Toast.makeText(IndividuationActivity.this, "当前功能尚未开发，请以后再来探索吧", Toast.LENGTH_SHORT);
            toast.show();
        } else if (checkedId == R.id.theme_succinct) {
            Toast toast2 = Toast.makeText(IndividuationActivity.this, "当前功能尚未开发1，请以后再来探索吧", Toast.LENGTH_SHORT);
            toast2.show();
        } else if (checkedId == R.id.theme_cool) {
            Toast toast3 = Toast.makeText(IndividuationActivity.this, "当前功能尚未开发2，请以后再来探索吧", Toast.LENGTH_SHORT);
            toast3.show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}