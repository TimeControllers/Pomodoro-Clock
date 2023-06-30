package com.example.tomato;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AccountManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        Button logoutBtn = findViewById(R.id.logout_btn);
        Button reLoginBtn = findViewById(R.id.relogin_btn);
        Button changePasswordBtn = findViewById(R.id.changePasswordBtn);
        TextView tv_manager_email = findViewById(R.id.tv_manage_email);
        tv_manager_email.setText(User.getEmail());
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User.clearUserSession();
                Toast toast = Toast.makeText(AccountManageActivity.this, "已退出登录", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        reLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountManageActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountManageActivity.this,ChangePwd.class);
                startActivity(intent);
            }
        });
    }
}