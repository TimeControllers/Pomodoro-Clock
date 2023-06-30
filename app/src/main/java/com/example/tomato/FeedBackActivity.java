package com.example.tomato;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener {
private EditText et_opinion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        et_opinion = findViewById(R.id.feedback_opinion);
        Button emailSend = findViewById(R.id.email_send);
        emailSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String opinionContent = et_opinion.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_TEXT, opinionContent);
        intent.putExtra(Intent.EXTRA_SUBJECT, "邮件主题"); // 设置邮件主题
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"2022302111001@whu.edu.cn"}); // 设置收件人邮箱地址
        startActivity(Intent.createChooser(intent, "选择邮件客户端"));
    }
}