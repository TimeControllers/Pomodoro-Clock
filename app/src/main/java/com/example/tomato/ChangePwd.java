package com.example.tomato;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tomato.tool.EmailSend;
import com.example.tomato.tool.ServerHelper;
import com.example.tomato.util.SendMailUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChangePwd extends Activity  {
    private EditText et_email,et_password,et_password_rePut,et_verification;
    Button btn_sendVerificationCode,btn_sure;
    EmailSend emailSend = new EmailSend();
    String verification = achieveCode();
    String st_email,st_password,st_password_rePUt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepwd);
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_pwd);
        et_password_rePut=findViewById(R.id.et_rePwd);
        et_verification = findViewById(R.id.et_verificationCode);
        btn_sendVerificationCode = findViewById(R.id.btn_sendVerificationCode);
        btn_sure = findViewById(R.id.btn_sure);
        st_email = et_email.getText().toString();
        st_password = et_password.getText().toString();
        st_password_rePUt = et_password_rePut.getText().toString();
        btn_sendVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (st_password != null && !et_password.getText().toString().equals("")&&
                        st_password_rePUt != null&& !et_password_rePut.getText().toString().equals("")){
                    if (st_password_rePUt.equals(st_password)){
                        SendMailUtil.send(et_email.getText().toString(),verification);
                        Toast toast = Toast.makeText(ChangePwd.this, "成功发送验证码", Toast.LENGTH_SHORT);
                        toast.show();
                    }else {
                        Toast toast = Toast.makeText(ChangePwd.this, "两次输入的密码不一致，请重新输入", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else {
                    Toast toast = Toast.makeText(ChangePwd.this, "请正确输入密码", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verification.equals(et_verification.getText().toString())){

                    ServerHelper serverHelper =new ServerHelper();
                    Log.i("change",st_email);
                    Log.i("change",st_password);
                    serverHelper.changePassword(et_email.getText().toString(), et_password.getText().toString())
                            .thenAccept(complete -> {
                                Log.i("complete", String.valueOf(complete));

                                if (complete == true) {
                                    Intent intent = new Intent(ChangePwd.this,AccountManageActivity.class);
                                    startActivity(intent);

                                    Toast toast = Toast.makeText(ChangePwd.this, "密码修改成功", Toast.LENGTH_SHORT);
                                    toast.show();
                                    Log.i("登录", "成功，unlockedId: "  + ", uid: " );
                                } else {

                                    Log.i("登录", "失败");
                                }
                            });

                }else {
                    Toast toast = Toast.makeText(ChangePwd.this, "验证码有误，请重新输入", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }
    public static String achieveCode() {  //由于数字 1 、 0 和字母 O 、l 有时分不清楚，所以，没有数字 1 、 0
        String[] beforeShuffle= new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a",
                "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z" };
        List list = Arrays.asList(beforeShuffle);//将数组转换为集合
        Collections.shuffle(list);  //打乱集合顺序
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)); //将集合转化为字符串
        }
        return sb.toString().substring(3, 8);  //截取字符串第4到8
    }
}
