package com.cleartrip.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final int userId;
    private final String userName;
    private final Set<Integer> following;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.following = new HashSet<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Set<Integer> getFollowing() {
        return following;
    }

    public void follow(User user) {
        following.add(user.getUserId());
    }

    public void unfollow(User user) {
        following.remove(user.getUserId());
    }
}
