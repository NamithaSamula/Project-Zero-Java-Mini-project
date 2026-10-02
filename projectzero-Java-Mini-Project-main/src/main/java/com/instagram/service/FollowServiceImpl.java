package com.instagram.service;

import com.instagram.dao.FollowDAO;
import com.instagram.dao.FollowDAOImpl;
import com.instagram.model.Follow;

import java.util.List;

public class FollowServiceImpl implements FollowService {

    private final FollowDAO followDAO;

    public FollowServiceImpl() {
        followDAO = new FollowDAOImpl();
    }

    @Override
    public boolean addFollow(Follow follow) {
        if (follow == null) {
            return false;
        }

        if (follow.getFollowerId() <= 0 ||
                follow.getFollowingId() <= 0) {
            return false;
        }

        // User cannot follow themselves
        if (follow.getFollowerId() == follow.getFollowingId()) {
            return false;
        }

        // Prevent duplicate follow
        if (followDAO.isFollowing(
                follow.getFollowerId(),
                follow.getFollowingId())) {
            return false;
        }

        return followDAO.addFollow(follow);
    }

    @Override
    public boolean removeFollow(int followerId, int followingId) {
        if (followerId <= 0 || followingId <= 0) {
            return false;
        }

        if (followerId == followingId) {
            return false;
        }

        return followDAO.removeFollow(followerId, followingId);
    }

    @Override
    public boolean isFollowing(int followerId, int followingId) {
        if (followerId <= 0 || followingId <= 0) {
            return false;
        }

        if (followerId == followingId) {
            return false;
        }


        return followDAO.isFollowing(followerId, followingId);
    }

    @Override
    public List<Follow> getFollowers(int followingId) {
        if (followingId <= 0) {
            return List.of();
        }

        return followDAO.getFollowers(followingId);
    }

    @Override
    public List<Follow> getFollowing(int followerId) {
        if (followerId <= 0) {
            return List.of();
        }



        return followDAO.getFollowing(followerId);
    }

    @Override
    public int getFollowerCount(int userId) {
        return followDAO.getFollowerCount(userId);
    }

    @Override
    public int getFollowingCount(int userId) {
        return followDAO.getFollowingCount(userId);
    }
}