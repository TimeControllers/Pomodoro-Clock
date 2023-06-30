package com.example.tomato;

public class User {
    private static String name;
    private static String password;
    private static String email;
    private static int uid;
    private static String unLockId;

    private static int score;
    private static String avatar;

    public static String getAvatar() {
        return avatar;
    }

    public static void setAvatar(String avatar) {
        avatar = avatar;
    }

    public User(String name, String email, int uid, String unLockId) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.uid = uid;
        this.unLockId = unLockId;
        this.avatar="0";
    }

    public User() {

    }
    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        User.score = score;
    }
    // User session methods
    public static void setUserSession(String name, String email, int uid, int score, String avatar) {
        setEmail(email);
        setName(name);
        setUid(uid);
        setAvatar(avatar);
        setScore(score);
    }

    public static void clearUserSession() {
        setEmail(null);
        setUid(0);
        setPassword(null);
        setName(null);
    }
    // Getters
    public static String getName() {
        return name;
    }

    public static String getPassword() {
        return password;
    }

    public static String getEmail() {
        return email;
    }

    public static int getUid() {
        return uid;
    }

    public static String getUnLockId() {
        return unLockId;
    }

    // Setters
    public static void setName(String name) {
        User.name = name;
    }

    public static void  setPassword(String password) {
        User.password = password;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static void setUid(int uid) {
        User.uid = uid;
    }

    public static void setUnLockId(String unLockId) {
        User.unLockId = unLockId;
    }


}
