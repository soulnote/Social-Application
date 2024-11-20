package com.cleartrip.model;

import java.time.LocalDateTime;

public class Post {
    private final String postId;
    private final int userId;
    private final String content;
    private final LocalDateTime timestamp;
    private int likes;
    private int dislikes;

    public Post(String postId, int userId, String content, LocalDateTime timestamp) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.timestamp = timestamp;
        this.likes = 0;
        this.dislikes = 0;
    }

    public String getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void like() {
        likes++;
    }

    public void dislike() {
        dislikes++;
    }
}
