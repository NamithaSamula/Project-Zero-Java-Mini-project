package com.instagram.service;

import com.instagram.model.Follow;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FollowServiceImplTest {

    @Test
    void addFollow() {
        FollowService followService = new FollowServiceImpl();

        Follow follow = new Follow();

        follow.setFollowerId(14);
        follow.setFollowingId(17);

        boolean result = followService.addFollow(follow);

        assertTrue(result);
    }

    @Test
    void removeFollow() {
        FollowService followService = new FollowServiceImpl();

        boolean result = followService.removeFollow(17, 14);

        assertTrue(result);

        boolean isFollowing = followService.isFollowing(17, 14);

        assertFalse(isFollowing);
    }

    @Test
    void isFollowing() {

        FollowService followService = new FollowServiceImpl();

        boolean result = followService.isFollowing(17, 4);

        assertTrue(result);
    }

    @Test
    void getFollowers() {
        FollowService followService = new FollowServiceImpl();

        List<Follow> follows = followService.getFollowers(4);

        assertNotNull(follows);
        assertFalse(follows.isEmpty());

        assertEquals(17, follows.get(0).getFollowerId());
        assertEquals(4, follows.get(0).getFollowingId());
    }

    @Test
    void getFollowing() {
        FollowService followService = new FollowServiceImpl();

        List<Follow> follows = followService.getFollowing(17);

        assertNotNull(follows);
        assertFalse(follows.isEmpty());

        assertEquals(17, follows.get(0).getFollowerId());
        assertEquals(4, follows.get(0).getFollowingId());
    }

    @Test
    void getFollowerCount() {
        FollowService followService = new FollowServiceImpl();

        int count = followService.getFollowerCount(14);

        assertEquals(2, count);
    }

    @Test
    void getFollowingCount() {
        FollowService followService = new FollowServiceImpl();

        int count = followService.getFollowingCount(17);

        assertEquals(1, count);
    }
    @Test
    void duplicateFollow() {

        FollowService followService = new FollowServiceImpl();

        Follow follow = new Follow();

        follow.setFollowerId(17);
        follow.setFollowingId(14);

        boolean result = followService.addFollow(follow);

        assertFalse(result);
    }

    @Test
    void selfFollow() {

        FollowService followService = new FollowServiceImpl();

        Follow follow = new Follow();

        follow.setFollowerId(17);
        follow.setFollowingId(17);

        boolean result = followService.addFollow(follow);

        assertFalse(result);
    }
}