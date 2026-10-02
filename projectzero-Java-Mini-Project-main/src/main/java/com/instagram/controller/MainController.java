package com.instagram.controller;

import com.instagram.model.Follow;
import com.instagram.model.Users;
import com.instagram.service.*;

import java.util.Scanner;

public class MainController {

    public static void main(String[] args) {

        // Create Service objects
        UserService userService = new UserServiceImpl();
        ProfileService profileService = new ProfileServiceImpl();
        PostService postService = new PostServiceImpl();
        CommentService commentService = new CommentServiceImpl();
        LikeService likeService = new LikeServiceImpl();
        FollowService followService = new FollowServiceImpl();
        AdminService adminService = new AdminServiceImpl();

        // Create Controller objects
        UserController userController =
                new UserController(userService);

        ProfileController profileController =
                new ProfileController(profileService);

        PostController postController =
                new PostController(postService);

        CommentController commentController =
                new CommentController(commentService);

        LikeController likeController =
                new LikeController(likeService);

        FollowController followController =
                new FollowController(followService);

        AdminController adminController =
                new AdminController(adminService);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Instagram Application =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    Users user = new Users();

                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPasswordHash(password);
                    user.setRole("USER");
                    user.setStatus("ACTIVE");

                    boolean registered =
                            userController.registerUser(user);

                    if (registered) {
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Registration failed!");
                    }

                    break;

                case 2:

                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    Users loggedInUser =
                            userController.login(
                                    loginUsername,
                                    loginPassword
                            );

//                    if (loggedInUser == null) {
//                        System.out.println("Invalid username or password!");
//                        break;
//                    }
//
//                    if ("INACTIVE".equals(loggedInUser.getStatus())) {
//                        System.out.println("Your account is inactive.");
//                        break;
//                    }
//
//                    System.out.println(
//                            "Login successful! Welcome, "
//                                    + loggedInUser.getUsername()
//
//                    );
//
//                    userMenu(
//                            scanner,
//                            loggedInUser,
//                            profileController,
//                            postController,
//                            commentController,
//                            likeController,
//                            followController
//                    );


                    if (loggedInUser == null) {

                        System.out.println("Invalid username or password.");

                    } else if ("INACTIVE".equals(loggedInUser.getStatus())) {

                        System.out.println("Your account is inactive.");
                        break;

                    } else {

                        System.out.println("Login successful!");

                        if ("ADMIN".equals(loggedInUser.getRole())) {

                            adminMenu(scanner, adminController);

                        } else {

                            userMenu(
                                    scanner,
                                    loggedInUser,
                                    userController,
                                    profileController,
                                    postController,
                                    commentController,
                                    likeController,
                                    followController
                            );
                        }
                    }
                    break;

                case 3:

                    System.out.println("Application closed.");
                    scanner.close();
                    return;

                default:

                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void adminMenu(
            Scanner scanner,
            AdminController adminController) {

        while (true) {

            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. View Dashboard");
            System.out.println("2. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    int totalUsers =
                            adminController.getTotalUsers();

                    // int totalFollowers =
                    //       adminController.getTotalFollowers();

                    //int totalFollowing =
                    //       adminController.getTotalFollowing();

                    int totalLikes =
                            adminController.getTotalLikes();

                    System.out.println();

                    System.out.println(
                            "╔══════════════════════════════════════════════╗"
                    );

                    System.out.println(
                            "║               ADMIN DASHBOARD               ║"
                    );

                    System.out.println(
                            "╠══════════════════════════════════════════════╣"
                    );

                    System.out.println(
                            "║                                              ║"
                    );

                    System.out.println(
                            "║  Total Users      : "
                                    + fitText(
                                    String.valueOf(totalUsers),
                                    20
                            )
                                    + "║"
                    );

//                    System.out.println(
//                            "║  Total Followers  : "
//                                    + fitText(
//                                    String.valueOf(totalFollowers),
//                                    20
//                            )
//                                    + "║"
//                    );

//                    System.out.println(
//                            "║  Total Following  : "
//                                    + fitText(
//                                    String.valueOf(totalFollowing),
//                                    20
//                            )
//                                    + "║"
//                    );

                    System.out.println(
                            "║  Total Likes      : "
                                    + fitText(
                                    String.valueOf(totalLikes),
                                    20
                            )
                                    + "║"
                    );

                    System.out.println(
                            "║                                              ║"
                    );

                    System.out.println(
                            "╚══════════════════════════════════════════════╝"
                    );

                    break;

                case 2:

                    System.out.println(
                            "Admin logged out successfully."
                    );

                    return;

                default:

                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void userMenu(
            Scanner scanner,
            Users loggedInUser,
            UserController userController,
            ProfileController profileController,
            PostController postController,
            CommentController commentController,
            LikeController likeController,
            FollowController followController) {

        while (true) {

            System.out.println("\n===== User Menu =====");

            System.out.println("1. Create Profile");
            System.out.println("2. View Profile");
            System.out.println("3. Edit Profile");
            System.out.println("4. Delete Profile");
            System.out.println("5. Create Post");
            System.out.println("6. View My Posts");
            System.out.println("7. Edit Post");
            System.out.println("8. Delete Post");
            System.out.println("9. Add Comment");
            System.out.println("10. View Post Comments");
            System.out.println("11. Like Post");
            System.out.println("12. Unlike Post");
            System.out.println("13. View Like Count");
            System.out.println("14. Follow User");
            System.out.println("15. Unfollow User");
            System.out.println("16. View Followers");
            System.out.println("17. View Following");
            System.out.println("18. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter profile name: ");
                    String profileName = scanner.nextLine();

                    System.out.print("Enter bio: ");
                    String bio = scanner.nextLine();

                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();

                    System.out.print("Enter profile image URL (optional): ");
                    String profileImageUrl = scanner.nextLine();

                    com.instagram.model.Profile profile =
                            new com.instagram.model.Profile();

                    profile.setUserId(loggedInUser.getUserId());
                    profile.setProfileName(profileName);
                    profile.setBio(bio);
                    profile.setPhone(phone);
                    profile.setProfileImageUrl(profileImageUrl);

                    if (profileController.addProfile(profile)) {
                        System.out.println("Profile created successfully!");
                    } else {
                        System.out.println("Failed to create profile.");
                    }

                    break;

                case 2:

                    com.instagram.model.Profile profiles =
                            profileController.getProfileByUserId(
                                    loggedInUser.getUserId()
                            );

                    java.util.List<com.instagram.model.Post> posts =
                            postController.getPostsByUserId(
                                    loggedInUser.getUserId()
                            );

                    int followers =
                            followController.getFollowerCount(
                                    loggedInUser.getUserId()
                            );

                    int following =
                            followController.getFollowingCount(
                                    loggedInUser.getUserId()
                            );

                    System.out.println();
                    System.out.println("╔══════════════════════════════════════════════╗");
                    System.out.println("║                 USER PROFILE                 ║");
                    System.out.println("╠══════════════════════════════════════════════╣");

                    System.out.println("║  @" +
                            fitText(loggedInUser.getUsername(), 39) + "          ║");

                    System.out.println("║                                               ║");

                    System.out.println("║  User ID: " +
                            fitText(String.valueOf(loggedInUser.getUserId()), 32) + "   ║");

                    if (profiles != null) {

                        System.out.println("║  Name: " +
                                fitText(profiles.getProfileName(), 35) + "               ║");

                        System.out.println("║  Bio: " +
                                fitText(profiles.getBio(), 36) + "                       ║");

                        System.out.println("║  Image: " +
                                fitText(profiles.getProfileImageUrl(), 33) + "           ║");

                    } else {

                        System.out.println("║  Profile: Not created yet                   ║");
                    }

                    System.out.println("║                                              ║");

                    System.out.println("║  Followers: " +
                            fitText(String.valueOf(followers), 7) +
                            " Following: " +
                            fitText(String.valueOf(following), 7) +
                            " Posts: " +
                            fitText(String.valueOf(posts.size()), 6) + "║");

                    System.out.println("╠══════════════════════════════════════════════╣");
                    System.out.println("║                    POSTS                     ║");
                    System.out.println("╠══════════════════════════════════════════════╣");

                    if (posts.isEmpty()) {

                        System.out.println("║  No posts available.                         ║");

                    } else {

                        for (com.instagram.model.Post post : posts) {

                            System.out.println("║                                              ║");

                            System.out.println("║  Post #" +
                                    fitText(String.valueOf(post.getPostId()), 34) + "║");

                            System.out.println("║  Caption: " +
                                    fitText(post.getCaption(), 31) + "║");

                            System.out.println("║  Image: " +
                                    fitText(post.getImageUrl(), 33) + "║");
                        }
                    }

                    System.out.println("╚══════════════════════════════════════════════╝");

                    break;


                case 3:
                    com.instagram.model.Profile currentProfile =
                            profileController.getProfileByUserId(
                                    loggedInUser.getUserId()
                            );

                    if (currentProfile == null) {
                        System.out.println("Profile not found. Create a profile first.");
                        break;
                    }

                    System.out.print("Enter new profile name: ");
                    currentProfile.setProfileName(scanner.nextLine());

                    System.out.print("Enter new bio: ");
                    currentProfile.setBio(scanner.nextLine());

                    System.out.print("Enter new phone: ");
                    currentProfile.setPhone(scanner.nextLine());

                    System.out.print("Enter new profile image URL: ");
                    currentProfile.setProfileImageUrl(scanner.nextLine());

                    if (profileController.updateProfile(currentProfile)) {
                        System.out.println("Profile updated successfully!");
                    } else {
                        System.out.println("Failed to update profile.");
                    }

                    break;


                case 4:

                    com.instagram.model.Profile profileToDelete =
                            profileController.getProfileByUserId(
                                    loggedInUser.getUserId()
                            );

                    if (profileToDelete == null) {
                        System.out.println("Profile not found.");
                        break;
                    }

                    System.out.print("Are you sure you want to delete your profile? (yes/no): ");
                    String confirmation = scanner.nextLine();

                    if (confirmation.equalsIgnoreCase("yes")) {

                        if (profileController.deleteProfile(
                                profileToDelete.getProfileId())) {

                            System.out.println("Profile deleted successfully!");

                        } else {
                            System.out.println("Failed to delete profile.");
                        }

                    } else {
                        System.out.println("Profile deletion cancelled.");
                    }

                    break;

                case 5:

                    System.out.print("Enter caption: ");
                    String caption = scanner.nextLine();

                    System.out.print("Enter image URL (optional): ");
                    String imageUrl = scanner.nextLine();

                    com.instagram.model.Post post =
                            new com.instagram.model.Post();

                    post.setUserId(loggedInUser.getUserId());
                    post.setCaption(caption);
                    post.setImageUrl(imageUrl);

                    if (postController.addPost(post)) {
                        System.out.println("Post created successfully!");
                    } else {
                        System.out.println("Failed to create post.");
                    }

                    break;

                case 6:
                    System.out.println("My Post is:");
                    System.out.println(
                            postController.getPostsByUserId(
                                    loggedInUser.getUserId()
                            )
                    );

                    break;

                case 7:

                    System.out.print("Enter post ID to edit: ");
                    int postId = scanner.nextInt();
                    scanner.nextLine();

                    com.instagram.model.Post postToUpdate =
                            postController.getPostById(postId);

                    if (postToUpdate == null) {
                        System.out.println("Post not found.");
                        break;
                    }

                    // Make sure the logged-in user owns the post
                    if (postToUpdate.getUserId() != loggedInUser.getUserId()) {
                        System.out.println("You can only edit your own posts.");
                        break;
                    }

                    System.out.print("Enter new caption: ");
                    String newCaption = scanner.nextLine();

                    System.out.print("Enter new image URL (optional): ");
                    String newImageUrl = scanner.nextLine();

                    postToUpdate.setCaption(newCaption);
                    postToUpdate.setImageUrl(newImageUrl);

                    if (postController.updatePost(postToUpdate)) {
                        System.out.println("Post updated successfully!");
                    } else {
                        System.out.println("Failed to update post.");
                    }

                    break;


                case 8:

                    System.out.print("Enter post ID to delete: ");
                    int deletePostId = scanner.nextInt();
                    scanner.nextLine();

                    com.instagram.model.Post postToDelete =
                            postController.getPostById(deletePostId);

                    if (postToDelete == null) {
                        System.out.println("Post not found.");
                        break;
                    }

                    // Make sure the logged-in user owns the post
                    if (postToDelete.getUserId() != loggedInUser.getUserId()) {
                        System.out.println("You can only delete your own posts.");
                        break;
                    }

                    System.out.print("Are you sure you want to delete this post? (yes/no): ");
                    String D_confirmation = scanner.nextLine();

                    if (D_confirmation.equalsIgnoreCase("yes")) {

                        if (postController.deletePost(deletePostId)) {
                            System.out.println("Post deleted successfully!");
                        } else {
                            System.out.println("Failed to delete post.");
                        }

                    } else {
                        System.out.println("Post deletion cancelled.");
                    }

                    break;

                case 9:

                    System.out.print("Enter post ID: ");
                    int post_Id = scanner.nextInt();
                    scanner.nextLine();

                    // Check whether the post exists
                    com.instagram.model.Post Apost =
                            postController.getPostById(post_Id);

                    if (Apost == null) {
                        System.out.println("Post not found.");
                        break;
                    }

                    System.out.print("Enter your comment: ");
                    String commentText = scanner.nextLine();

                    com.instagram.model.Comment comment =
                            new com.instagram.model.Comment();

                    comment.setUserId(loggedInUser.getUserId());
                    comment.setPostId(post_Id);
                    comment.setParentCommentId(null);
                    comment.setCommentText(commentText);

                    if (commentController.addComment(comment)) {
                        System.out.println("Comment added successfully!");
                    } else {
                        System.out.println("Failed to add comment.");
                    }

                    break;

                case 10:

                    System.out.print("Enter post ID: ");
                    int V_post_Id = scanner.nextInt();
                    scanner.nextLine();

                    // Check whether the post exists
                    com.instagram.model.Post postid =
                            postController.getPostById(V_post_Id);

                    if (postid == null) {
                        System.out.println("Post not found.");
                        break;
                    }

                    System.out.print("Enter your comment: ");
                    String comment_Text = scanner.nextLine();

                    com.instagram.model.Comment Pcomment =
                            new com.instagram.model.Comment();

                    Pcomment.setUserId(loggedInUser.getUserId());
                    Pcomment.setPostId(V_post_Id);
                    Pcomment.setParentCommentId(null);
                    Pcomment.setCommentText(comment_Text);

                    if (commentController.addComment(Pcomment)) {
                        System.out.println("Comment added successfully!");
                    } else {
                        System.out.println("Failed to add comment.");
                    }

                    break;


                case 11:

                    System.out.print("Enter post ID: ");
                    int likePostId = scanner.nextInt();
                    scanner.nextLine();

                    com.instagram.model.Post likePost =
                            postController.getPostById(likePostId);

                    if (likePost == null) {
                        System.out.println("Post not found.");
                        break;
                    }

                    com.instagram.model.Like like =
                            new com.instagram.model.Like();

                    like.setUserId(loggedInUser.getUserId());
                    like.setPostId(likePostId);

                    if (likeController.addLike(like)) {
                        System.out.println("Post liked successfully!");
                    } else {
                        System.out.println("You have already liked this post.");
                    }

                    break;

                case 12:

                    System.out.print("Enter post ID: ");
                    int unlikePostId = scanner.nextInt();
                    scanner.nextLine();

                    if (likeController.removeLike(
                            loggedInUser.getUserId(),
                            unlikePostId)) {

                        System.out.println("Post unliked successfully!");

                    } else {

                        System.out.println("Like not found.");
                    }

                    break;

                case 13:

                    System.out.print("Enter post ID: ");
                    int countPostId = scanner.nextInt();
                    scanner.nextLine();

                    com.instagram.model.Post countPost =
                            postController.getPostById(countPostId);

                    if (countPost == null) {
                        System.out.println("Post not found.");
                        break;
                    }

                    int likeCount =
                            likeController.getLikeCount(countPostId);

                    System.out.println(
                            "Post " + countPostId +
                                    " has " + likeCount + " like(s)."
                    );

                    break;


                case 14:

                    System.out.print("Enter user ID to follow: ");
                    int followingId = scanner.nextInt();
                    scanner.nextLine();

                    Users userToFollow =
                            userController.getAllUsers()
                                    .stream()
                                    .filter(user -> user.getUserId() == followingId)
                                    .findFirst()
                                    .orElse(null);

                    if (userToFollow == null) {
                        System.out.println("User not found.");
                        break;
                    }

                    Follow follow = new Follow();

                    follow.setFollowerId(loggedInUser.getUserId());
                    follow.setFollowingId(followingId);

                    if (followController.addFollow(follow)) {
                        System.out.println(
                                "You are now following "
                                        + userToFollow.getUsername()
                        );
                    } else {
                        System.out.println(
                                "Unable to follow user. " +
                                        "You may already be following this user."
                        );
                    }

                    break;

                case 15:

                    System.out.print("Enter user ID to unfollow: ");
                    int unfollowId = scanner.nextInt();
                    scanner.nextLine();

                    if (followController.removeFollow(
                            loggedInUser.getUserId(),
                            unfollowId)) {

                        System.out.println("User unfollowed successfully!");

                    } else {

                        System.out.println("You are not following this user.");
                    }

                    break;

                case 16:

                    java.util.List<Follow> V_followers =
                            followController.getFollowers(
                                    loggedInUser.getUserId()
                            );

                    System.out.println();
                    System.out.println("╔══════════════════════════════════════════════╗");
                    System.out.println("║                  FOLLOWERS                   ║");
                    System.out.println("╠══════════════════════════════════════════════╣");

                    if (V_followers.isEmpty()) {

                        System.out.println("║  No followers yet.                           ║");

                    } else {

                        for (Follow follower : V_followers) {

                            Users vfollowers =
                                    userController.getAllUsers()
                                            .stream()
                                            .filter(user ->
                                                    user.getUserId()
                                                            == follower.getFollowerId())
                                            .findFirst()
                                            .orElse(null);

                            if (follower != null) {

                                System.out.println(
                                        "║  " +
                                                fitText(
                                                        vfollowers.getUsername(),
                                                        40
                                                ) +
                                                "║"
                                );
                            }
                        }
                    }

                    System.out.println("╚══════════════════════════════════════════════╝");

                    break;


                case 17:

                    java.util.List<Follow> followings =
                            followController.getFollowing(
                                    loggedInUser.getUserId()
                            );

                    System.out.println();
                    System.out.println("╔══════════════════════════════════════════════╗");
                    System.out.println("║                  FOLLOWING                   ║");
                    System.out.println("╠══════════════════════════════════════════════╣");

                    if (followings.isEmpty()) {

                        System.out.println("║  You are not following anyone yet.           ║");

                    } else {

                        for (Follow follows : followings) {

                            Users followingUser =
                                    userController.getAllUsers()
                                            .stream()
                                            .filter(user ->
                                                    user.getUserId()
                                                            == follows.getFollowingId())
                                            .findFirst()
                                            .orElse(null);

                            if (followingUser != null) {

                                System.out.println(
                                        "║  " +
                                                fitText(
                                                        followingUser.getUsername(),
                                                        40
                                                ) +
                                                "║"
                                );
                            }
                        }
                    }

                    System.out.println("╚══════════════════════════════════════════════╝");

                    break;

                case 18:

                    System.out.println("Logged out successfully.");
                    return;


                default:

                    System.out.println("Invalid choice!");
            }
        }
    }
























    private static String fitText(String text, int length) {

        if (text == null) {
            text = "";
        }

        if (text.length() > length) {
            return text.substring(0, length - 3) + "...";
        }

        return String.format("%-" + length + "s", text);
    }
}