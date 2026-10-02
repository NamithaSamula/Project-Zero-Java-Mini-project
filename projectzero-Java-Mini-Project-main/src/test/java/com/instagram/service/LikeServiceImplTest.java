package com.instagram.service;

import com.instagram.model.Like;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LikeServiceImplTest {

    @Test
    void addLike() {
        LikeService likeService = new LikeServiceImpl();

        Like like = new Like();

        like.setUserId(4);
        like.setPostId(3);

        boolean result = likeService.addLike(like);

        assertTrue(result);
    }

    @Test
    void removeLike() {
        LikeService likeService = new LikeServiceImpl();

        boolean result = likeService.removeLike(17, 1);

        assertTrue(result);

        boolean isLiked = likeService.isLiked(17, 1);

        assertFalse(isLiked);
    }

    @Test
    void isLiked() {

        LikeService likeService = new LikeServiceImpl();

        boolean result = likeService.isLiked(17, 1);

        assertTrue(result);
    }

    @Test
    void isNotLiked() {

        LikeService likeService = new LikeServiceImpl();

        boolean result = likeService.isLiked(14, 3);

        assertFalse(result);
    }

    @Test
    void getLikeCount() {

        LikeService likeService = new LikeServiceImpl();

        int count = likeService.getLikeCount(1);

        assertEquals(1, count);
    }

   /* @Test
    void getLikesByPostId() {
        LikeService likeService = new LikeServiceImpl();

        List<Like> likes = likeService.getLikesByPostId(1);

        assertNotNull(likes);
        assertFalse(likes.isEmpty());
        assertEquals(1, likes.get(0).getUserId());
    }*/

    @Test
    void getLikesByPostId() {

        LikeService likeService = new LikeServiceImpl();

        List<Like> likes = likeService.getLikesByPostId(1);

        assertNotNull(likes);
        assertFalse(likes.isEmpty());
        assertEquals(17, likes.get(0).getUserId());
        assertEquals(1, likes.get(0).getPostId());
    }

    @Test
    void getLikesByUserId() {

        LikeService likeService = new LikeServiceImpl();

        List<Like> likes = likeService.getLikesByUserId(17);

        assertNotNull(likes);
        assertFalse(likes.isEmpty());
        assertEquals(17, likes.get(0).getUserId());
        assertEquals(1, likes.get(0).getPostId());
    }

    @Test
    void duplicateLike() {

        LikeService likeService = new LikeServiceImpl();

        Like like = new Like();

        like.setUserId(17);
        like.setPostId(1);

        boolean firstResult = likeService.addLike(like);

        assertTrue(firstResult);

        boolean secondResult = likeService.addLike(like);

        assertFalse(secondResult);
    }
}