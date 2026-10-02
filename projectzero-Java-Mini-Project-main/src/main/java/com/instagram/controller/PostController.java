package com.instagram.controller;

import com.instagram.model.Post;
import com.instagram.service.PostService;

import java.util.List;

public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public boolean addPost(Post post) {
        return postService.createPost(post);
    }

    public Post getPostById(int postId) {
        return postService.getPostById(postId);
    }

    public List<Post> getPostsByUserId(int userId) {
        return postService.getPostsByUserId(userId);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public boolean updatePost(Post post) {
        return postService.updatePost(post);
    }

    public boolean deletePost(int postId) {
        return postService.deletePost(postId);
    }
}