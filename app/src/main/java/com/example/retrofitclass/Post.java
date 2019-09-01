package com.example.retrofitclass;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int UserId;
    private int id;
    private String title;
    @SerializedName("body")
    private String text;

    public Post(int userId, int id, String title, String text) {
        UserId = userId;
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
