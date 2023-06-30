package com.example.tomato.util;

import com.example.tomato.tool.EmailSend;
import com.example.tomato.tool.MailInfo;

public class SendMailUtil {
    //qq
    //private static final String HOST = "smtp.qq.com";
    //private static final String PORT = "587";
    //private static final String FROM_ADD = "changyiqiang666@163.com";
    //private static final String FROM_PSW = "qwertyuiop123456";

    //    //163
    private static final String HOST = "smtp.163.com";
    private static final String PORT = "25"; //或者465  994

    private static final String FROM_ADD = "18749995291@163.com";
    private static final String FROM_PSW = "PLOUAXXSVKQEINND";//

    public static void send(String toAdd,String context){
        final MailInfo mailInfo = creatMail(toAdd,context);
        final EmailSend sms = new EmailSend();
        new Thread(new Runnable() {
            @Override
            public void run() {
                sms.sendTextMail(mailInfo);
            }
        }).start();
    }

    @androidx.annotation.NonNull
    private static MailInfo creatMail(String toAdd,String context) {
        final MailInfo mailInfo = new MailInfo();
        mailInfo.setMailServerHost(HOST);// 发送邮件的服务器的IP
        mailInfo.setMailServerPort(PORT);// 发送邮件的服务器的端口
        mailInfo.setValidate(true);// 是否需要身份验证
        mailInfo.setUserName(FROM_ADD); // 你的邮箱地址 // 登陆邮件发送服务器的用户名
        mailInfo.setPassword(FROM_PSW);// 您的邮箱密码 登陆邮件发送服务器的密码
        mailInfo.setFromAddress(FROM_ADD); // 发送的邮箱 // 邮件发送者的地址
        mailInfo.setToAddress(toAdd); // 发到哪个邮件去
        mailInfo.setSubject("您的登录验证码"); // 邮件主题
        mailInfo.setContent(context); // 邮件文本
        return mailInfo;
    }
}