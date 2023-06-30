package com.example.tomato.tool;

import android.util.Log;

import com.example.tomato.FUser;
import com.example.tomato.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ServerHelper {

    private String ip = "8.130.17.190";
    private String port = "8080";
    private boolean ok;

    public CompletableFuture<Boolean> signup(String email, String password,String name) {
        String url = "http://" + ip + ":" + port + "/server/Signup";
        RequestBody requestBody = new FormBody.Builder()
                .add("name",name)
                .add("email", email)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return FutureCall.futureCallBoolean(request);
    }

    public CompletableFuture<Boolean> login(String email, String password) {
        String url = "http://" + ip + ":" + port + "/server/Login";
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Log.i("login","start");

        return FutureCall.futureCallBoolean(request);
    }

    public CompletableFuture<Boolean> setName(String email, String name) {
        String url = "http://" + ip + ":" + port + "/server/SetName";
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("name", name)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return FutureCall.futureCallBoolean(request);
    }



    public CompletableFuture<User> getName(String email) {
        String url = "http://" + ip + ":" + port + "/server/GetName";
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return FutureCall.futureCallUser(request);
    }

    public CompletableFuture<Boolean> setScore(String score) {
        String url = "http://" + ip + ":" + port + "/server/SetScore";
        RequestBody requestBody = new FormBody.Builder()
                .add("uid", String.valueOf(User.getUid()))
                .add("score", score)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return FutureCall.futureCallBoolean(request);
    }
    public CompletableFuture<String> getScore() {
        String url = "http://" + ip + ":" + port + "/server/GetScore";
        RequestBody requestBody = new FormBody.Builder()
                .add("uid", String.valueOf(User.getUid()))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return FutureCall.futureCallString(request);
    }

    public CompletableFuture<User> getAllInfo(String email) {
        String url = "http://" + ip + ":" + port + "/server/GetAllInfo";
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Log.i("getAllInfo","start");
        return FutureCall.futureCallUser(request);
    }
    public CompletableFuture<Boolean> changePassword(String email, String newPwd) {
        String url = "http://" + ip + ":" + port + "/server/ChangePassword";
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("newPwd", newPwd)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return FutureCall.futureCallBoolean(request);
    }

    public CompletableFuture<Boolean> sendFriendRequest(String fid) {
        String url = "http://" + ip + ":" + port + "/server/SendFriendRequest";
        RequestBody requestBody = new FormBody.Builder()
                .add("uid", String.valueOf(User.getUid()))
                .add("fid", fid)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return FutureCall.futureCallBoolean(request);
    }


    public CompletableFuture<Boolean> setUnLockId(String unLockId) {

        String url = "http://" + ip + ":" + port + "/server/SetUnLockId";
        RequestBody requestBody = new FormBody.Builder()
                .add("uid", String.valueOf(User.getUid()))
                .add("unLockId",User.getUnLockId())
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return FutureCall.futureCallBoolean(request);

    }
    public CompletableFuture<String> getUnLockId(int fid) {
        String url = "http://" + ip + ":" + port + "/server/GetUnLockId";
        RequestBody requestBody = new FormBody.Builder()
                .add("fid", String.valueOf(fid))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return FutureCall.futureCallString(request);
    }
    public CompletableFuture<List<FUser>> showFriendList() {
        Log.i("showFriendList","start");
        String url = "http://" + ip + ":" + port + "/server/ShowFriendList";
        RequestBody requestBody = new FormBody.Builder()
                .add("uid", String.valueOf(User.getUid()))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Log.i("showFriendList","RequestBuild");

        return FutureCall.futureCallUserList(request);
    }
    public CompletableFuture<List<FUser>> getFriendRequest() {
        String url = "http://" + ip + ":" + port + "/server/GetFriendRequest";
        RequestBody requestBody = new FormBody.Builder()
                .add("uid", "1000")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return FutureCall.futureCallUserList(request);
    }
    public CompletableFuture<Boolean> isFriend(boolean let,int fid) {
        String url = "http://" + ip + ":" + port + "/server/IsFriend";
        RequestBody requestBody = new FormBody.Builder()
                .add("is", String.valueOf(let))
                .add("uid", String.valueOf(User.getUid()))
                .add("fid", String.valueOf(fid))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return FutureCall.futureCallBoolean(request);
    }

}