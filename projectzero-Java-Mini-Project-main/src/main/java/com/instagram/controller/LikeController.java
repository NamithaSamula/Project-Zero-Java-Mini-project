package com.instagram.controller;

import com.instagram.model.Like;
import com.instagram.service.LikeService;

import java.util.List;

public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    public boolean addLike(Like like) {
        return likeService.addLike(like);
    }

    public boolean removeLike(int userId, int postId) {
        return likeService.removeLike(userId, postId);
    }

    public boolean isLiked(int userId, int postId) {
        return likeService.isLiked(userId, postId);
    }

    public int getLikeCount(int postId) {
        return likeService.getLikeCount(postId);
    }

    public List<Like> getLikesByPostId(int postId) {
        return likeService.getLikesByPostId(postId);
    }

    public List<Like> getLikesByUserId(int userId) {
        return likeService.getLikesByUserId(userId);
    }
}