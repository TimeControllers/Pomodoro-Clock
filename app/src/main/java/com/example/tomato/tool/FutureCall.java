package com.example.tomato.tool;

import android.util.Log;

import com.example.tomato.FUser;
import com.example.tomato.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FutureCall {
    public static CompletableFuture<Boolean> futureCallBoolean(Request request){
        OkHttpClient httpClient = new OkHttpClient();
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                future.complete(false);
                Log.e("callback", "Request failed:未连接 " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i("callback","服务器连接");
                Log.i("result",result);
                if (result.equals("true")) {future.complete(true);}
               else{future.complete(false);}


            }
        });
        return future;
    } ;
    public static CompletableFuture<User> futureCallUser(Request request) {
        OkHttpClient httpClient = new OkHttpClient();
        CompletableFuture<User> future = new CompletableFuture<>();
        Call call = httpClient.newCall(request);
        User user =new User();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("callback", "Request failed:未连接 " + e.getMessage());


                future.complete(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("callbackUser","success");
                    String responseBody = response.body().string();
                    Log.i("responseBody",responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(responseBody);
                    user.setScore(Integer.parseInt(jsonObject.getString("score")));
                    user.setAvatar(jsonObject.getString("avatar"));
                    user.setUid(jsonObject.getInt("uid"));
                    user.setName(jsonObject.getString("name"));
                    user.setEmail(jsonObject.getString("email"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                Log.i("callbackUser_user",User.getScore()+User.getName()+User.getEmail()+User.getUid());

                future.complete(user);

            }
        });
        return future;
    }

    public static CompletableFuture<String> futureCallString(Request request) {
        OkHttpClient httpClient = new OkHttpClient();
        CompletableFuture<String> future = new CompletableFuture<>();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                future.complete(null);
                Log.e("callback", "Request failed:未连接 " + e.getMessage());

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                if (!result.equals(null)) {future.complete(result);}

            }
        });
        return future;
    }

    public static CompletableFuture<List<FUser>> futureCallUserList(Request request) {
        OkHttpClient httpClient = new OkHttpClient();
        CompletableFuture<List<FUser>> future = new CompletableFuture<>();
        Call call = httpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("callback", "failed");
                future.complete(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("callbackUser", "success");
                String responseBody = response.body().string();
                Log.i("responseBody", responseBody);
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(responseBody);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                if (jsonArray.length() == 0) {
                    future.complete(null);
                } else {

                    List<FUser> userList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = jsonArray.getJSONObject(i);
                        } catch (JSONException e) {

                        }
                        FUser user = new FUser();
                        try {
                            user.setId(jsonObject.getInt("uid"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        } try {
                            user.setAvatar(jsonObject.getString("avatar"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            user.setName(jsonObject.getString("name"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            user.setEmail(jsonObject.getString("email"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        userList.add(user);
                    }
                    future.complete(userList);
                }

            }

        });

        return future;
    }
}
