package com.instagram.model;

public class Post {

    private int postId;
    private int userId;
    private String caption;
    private String imageUrl;

    public Post() {
    }

    public Post(int postId, int userId, String caption, String imageUrl) {

        this.postId = postId;
        this.userId = userId;
        this.caption = caption;
        this.imageUrl = imageUrl;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", caption='" + caption + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}