package com.cleartrip.service;

import com.cleartrip.model.Post;
import com.cleartrip.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClearTripService {
    private final Map<Integer, User> users = new HashMap<>();
    private final List<Post> posts = new ArrayList<>();
    private int postCounter = 1;

    public void registerUser(int userId, String userName) {
        if (users.containsKey(userId)) {
            System.out.println("User with ID " + userId + " already exists.");
            return;
        }
        users.put(userId, new User(userId, userName));
        System.out.println(userName + " Registered!");
    }

    public void uploadPost(int userId, String content) {
        if (!users.containsKey(userId)) {
            System.out.println("User not found. Cannot upload post.");
            return;
        }
        String postId = String.format("%03d", postCounter++);
        Post post = new Post(postId, userId, content, LocalDateTime.now());
        posts.add(post);
        System.out.println("Upload Successful with post id: " + postId);
    }

    public void interactWithUser(String interactionType, int userId1, int userId2) {
        if (!users.containsKey(userId1) || !users.containsKey(userId2)) {
            System.out.println("Invalid user IDs.");
            return;
        }
        User user1 = users.get(userId1);
        User user2 = users.get(userId2);

        if ("FOLLOW".equalsIgnoreCase(interactionType)) {
            user1.follow(user2);
            System.out.println("Followed " + user2.getUserName() + "!");
        } else if ("UNFOLLOW".equalsIgnoreCase(interactionType)) {
            user1.unfollow(user2);
            System.out.println("Unfollowed " + user2.getUserName() + "!");
        } else {
            System.out.println("Invalid interaction type.");
        }
    }

    public void interactWithPost(String interactionType, int userId, String postId) {
        Post post = null;
        for (Post p : posts) {
            if (p.getPostId().equals(postId)) {
                post = p;
                break;
            }
        }

        if (post == null) {
            System.out.println("Post not found.");
            return;
        }
        if ("LIKE".equalsIgnoreCase(interactionType)) {
            post.like();
            System.out.println("Post Liked!!");
        } else if ("DISLIKE".equalsIgnoreCase(interactionType)) {
            post.dislike();
            System.out.println("Post Disliked!!");
        } else {
            System.out.println("Invalid interaction type.");
        }
    }

    public void showFeed(int userId) {
        if (!users.containsKey(userId)) {
            System.out.println("User not found.");
            return;
        }

        User user = users.get(userId);
        List<Post> followedPosts = new ArrayList<>();
        List<Post> nonFollowedPosts = new ArrayList<>();

        for (Post p : posts) {
            if (user.getFollowing().contains(p.getUserId())) {
                followedPosts.add(p);
            } else {
                nonFollowedPosts.add(p);
            }
        }

        followedPosts.sort((p1, p2) -> p2.getTimestamp().compareTo(p1.getTimestamp()));
        nonFollowedPosts.sort((p1, p2) -> p2.getTimestamp().compareTo(p1.getTimestamp()));

        System.out.println("Feed for " + user.getUserName() + ":");
        for (Post post : followedPosts) {
            printPost(post);
        }
        for (Post post : nonFollowedPosts) {
            printPost(post);
        }
    }

    private void printPost(Post post) {
        User user = users.get(post.getUserId());
        System.out.println("UserName - " + user.getUserName());
        System.out.println("Post - " + post.getContent());
        System.out.println("Number of Likes - " + post.getLikes());
        System.out.println("Number of Dislikes - " + post.getDislikes());
        System.out.println("Post time - " + post.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println();
    }
}
