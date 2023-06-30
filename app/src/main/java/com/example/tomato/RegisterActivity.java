package com.example.tomato;

import static com.example.tomato.ChangePwd.achieveCode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tomato.tool.ServerHelper;
import com.example.tomato.util.SendMailUtil;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_email,et_password,et_password_rePut,et_verification,et_name;
    Button btn_register,btn_sendVerificationCode;
    String email,password,passwordRePut,name;
    String verification = achieveCode();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_email = findViewById(R.id.et_email);
        et_name =findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_pwd);
        et_password_rePut = findViewById(R.id.et_pwd_rePut);
        btn_register = findViewById(R.id.btn_register);
        et_verification = findViewById(R.id.login_code);
        btn_sendVerificationCode = findViewById(R.id.btn_verification);
        btn_sendVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_password.getText().toString() != null && !et_password.getText().toString().equals("") &&
                        et_password_rePut.getText().toString() != null && !et_password_rePut.getText().toString().equals("")) {
                    if (et_password.getText().toString().equals(et_password_rePut.getText().toString())) {
                        SendMailUtil.send(et_email.getText().toString(), verification);
                        Toast toast = Toast.makeText(RegisterActivity.this, "成功发送验证码", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(RegisterActivity.this, "两次输入的密码不一致，请重新输入", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(RegisterActivity.this, "请正确输入密码", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                name=et_name.getText().toString();
                                                email = et_email.getText().toString();
                                                password = et_password.getText().toString();
                                                passwordRePut = et_password_rePut.getText().toString();
                                                if (verification.equals(et_verification.getText().toString())) {
                                                        ServerHelper serverHelper = new ServerHelper();
                                                        serverHelper.signup(email, password,name)
                                                                .thenAccept(complete -> {
                                                                    Log.i("complete", String.valueOf(complete));
                                                                    // 处理异步操作结果
                                                                    if (complete != false) {
                                                                        Log.i("注册", "成功，: " + ", uid: ");
                                                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                        startActivity(intent);
                                                                    } else {
                                                                        // 登录失败
                                                                        Log.i("注册", "失败");

                                                                    }
                                                                });
                                                    } else {
                                                        Toast toast = Toast.makeText(RegisterActivity.this, "验证码有误，请重新输入", Toast.LENGTH_SHORT);
                                                        toast.show();
                                                    }
                                            }

                                        }
        );

    }
}