package com.instagram.model;

public class Follow {

    private int followId;
    private int followerId;
    private int followingId;

    public Follow() {
    }

    public Follow(int followId, int followerId, int followingId) {
        this.followId = followId;
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "followId=" + followId +
                ", followerId=" + followerId +
                ", followingId=" + followingId +
                '}';
    }
}