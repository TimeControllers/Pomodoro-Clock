package com.example.tomato.friend;

public class FriendInfo {
    private int Icon;
    private String email;
    private String name;
    private int score;
    public FriendInfo(int icon, String email, String name, int score) {
        Icon = icon;
        this.email = email;
        this.name = name;
        this.score = score;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
