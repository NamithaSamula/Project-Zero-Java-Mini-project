package com.instagram.service;

import com.instagram.model.Like;

import java.util.List;

public interface LikeService {

    boolean addLike(Like like);

    boolean removeLike(int userId, int postId);

    boolean isLiked(int userId, int postId);

    int getLikeCount(int postId);

    List<Like> getLikesByPostId(int postId);

    List<Like> getLikesByUserId(int userId);
}