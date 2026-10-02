package com.instagram.service;

import com.instagram.dao.PostDAO;
import com.instagram.dao.PostDAOImpl;
import com.instagram.model.Post;
import java.util.List;

public class PostServiceImpl implements PostService {

    private PostDAO postDAO;

    public PostServiceImpl() {
        this.postDAO = new PostDAOImpl();
    }

    @Override
    public boolean createPost(Post post) {
        // TODO: Add validation and call PostDAO
        if (post == null) {
            return false;
        }

        if (post.getUserId() <= 0) {
            return false;
        }

        // Post must have either caption or image
        if ((post.getCaption() == null || post.getCaption().trim().isEmpty())
                && (post.getImageUrl() == null || post.getImageUrl().trim().isEmpty())) {
            return false;
        }

        return postDAO.addPost(post);
    }

    @Override
    public Post getPostById(int postId) {
        // TODO: Call PostDAO
        if (postId <= 0) {
            return null;
        }
        return postDAO.getPostById(postId);
    }

    @Override
    public List<Post> getPostsByUserId(int userId) {
        // TODO: Call PostDAO
        if (userId <= 0) {
            return List.of();
        }

        return postDAO.getPostsByUserId(userId);
    }

    @Override
    public List<Post> getAllPosts() {

        return postDAO.getAllPosts();
    }

    @Override
    public boolean updatePost(Post post) {
        // TODO: Add validation and call PostDAO
        if (post == null) {
            return false;
        }

        if (post.getPostId() <= 0) {
            return false;
        }

        if (post.getUserId() <= 0) {
            return false;
        }

        if ((post.getCaption() == null || post.getCaption().trim().isEmpty())
                && (post.getImageUrl() == null || post.getImageUrl().trim().isEmpty())) {
            return false;
        }
        return postDAO.updatePost(post);
    }

    @Override
    public boolean deletePost(int postId) {
        if(postId<=0)
        {
            return  false;
        }
        return postDAO.deletePost(postId);
    }
}