package com.cleartrip;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cleartrip.service.ClearTripService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        ClearTripService service = new ClearTripService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Social Media Console Application!");

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Register User");
            System.out.println("2. Upload Post");
            System.out.println("3. Follow/Unfollow User");
            System.out.println("4. Show Feed");
            System.out.println("5. Like/Dislike Post");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1: // Register User
                    System.out.print("Enter userId and userName (e.g., 1 Akash): ");
                    String[] registerInput = scanner.nextLine().split(" ");
                    int userId = Integer.parseInt(registerInput[0]);
                    String userName = registerInput[1];
                    service.registerUser(userId, userName);
                    break;

                case 2: // Upload Post
                    System.out.print("Enter userId and post content (e.g., 1 \"This is my post\"): ");
                    String[] postInput = scanner.nextLine().split(" ", 2);
                    int postUserId = Integer.parseInt(postInput[0]);
                    String postContent = postInput[1];
                    service.uploadPost(postUserId, postContent);
                    break;

                case 3: // Follow/Unfollow User
                    System.out.print("Enter interaction type (FOLLOW/UNFOLLOW), userId1, and userId2 (e.g., FOLLOW 2 1): ");
                    String[] interactionInput = scanner.nextLine().split(" ");
                    String interactionType = interactionInput[0];
                    int userId1 = Integer.parseInt(interactionInput[1]);
                    int userId2 = Integer.parseInt(interactionInput[2]);
                    service.interactWithUser(interactionType, userId1, userId2);
                    break;

                case 4: // Show Feed
                    System.out.print("Enter userId to view feed: ");
                    int feedUserId = Integer.parseInt(scanner.nextLine().trim());
                    service.showFeed(feedUserId);
                    break;

                case 5: // Like/Dislike Post
                    System.out.print("Enter interaction type (LIKE/DISLIKE), userId, and postId (e.g., LIKE 2 001): ");
                    String[] postInteractionInput = scanner.nextLine().split(" ");
                    String postInteractionType = postInteractionInput[0];
                    int interactionUserId = Integer.parseInt(postInteractionInput[1]);
                    String postId = postInteractionInput[2];
                    service.interactWithPost(postInteractionType, interactionUserId, postId);
                    break;

                case 6: // Exit
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
