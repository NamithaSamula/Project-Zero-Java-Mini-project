package com.instagram.service;

import com.instagram.dao.LikeDAO;
import com.instagram.dao.LikeDAOImpl;
import com.instagram.model.Like;

import java.util.List;

public class LikeServiceImpl implements LikeService {

    private final LikeDAO likeDAO;

    public LikeServiceImpl() {
        likeDAO = new LikeDAOImpl();
    }

    @Override
    public boolean addLike(Like like) {
        return likeDAO.addLike(like);
    }

    @Override
    public boolean removeLike(int userId, int postId) {
        return likeDAO.removeLike(userId, postId);
    }

    @Override
    public boolean isLiked(int userId, int postId) {
        return likeDAO.isLiked(userId, postId);
    }

    @Override
    public int getLikeCount(int postId) {
        return likeDAO.getLikeCount(postId);
    }

    @Override
    public List<Like> getLikesByPostId(int postId) {
        return likeDAO.getLikesByPostId(postId);
    }

    @Override
    public List<Like> getLikesByUserId(int userId) {
        return likeDAO.getLikesByUserId(userId);
    }
}