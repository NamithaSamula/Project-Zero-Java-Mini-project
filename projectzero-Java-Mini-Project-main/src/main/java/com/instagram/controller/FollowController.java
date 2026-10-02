package com.instagram.controller;

import com.instagram.model.Follow;
import com.instagram.service.FollowService;

import java.util.List;

public class FollowController {

    private FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    public boolean addFollow(Follow follow) {
        return followService.addFollow(follow);
    }

    public boolean removeFollow(int followerId, int followingId) {
        return followService.removeFollow(followerId, followingId);
    }

    public boolean isFollowing(int followerId, int followingId) {
        return followService.isFollowing(followerId, followingId);
    }

    public List<Follow> getFollowers(int followingId) {
        return followService.getFollowers(followingId);
    }

    public List<Follow> getFollowing(int followerId) {
        return followService.getFollowing(followerId);
    }

    public int getFollowerCount(int userId) {
        return followService.getFollowerCount(userId);
    }

    public int getFollowingCount(int userId) {
        return followService.getFollowingCount(userId);
    }
}