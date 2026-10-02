package com.instagram.service;

import com.instagram.model.Post;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceImplTest {

    @Test
    void createPost() {

        PostService postService = new PostServiceImpl();

        Post post = new Post();

        post.setUserId(14);
        post.setCaption("My first Instagram post");
        post.setImageUrl("Manasa.jpg");

        boolean result = postService.createPost(post);

        assertTrue(result);
    }

    @Test
    void getPostById() {
        PostService postService = new PostServiceImpl();

        Post post = postService.getPostById(1);

        assertNotNull(post);
        assertEquals(1, post.getPostId());
        assertEquals(17, post.getUserId());
        assertEquals("My updated Instagram post", post.getCaption());
        System.out.println("Post retrived");
    }

    @Test
    void getPostsByUserId() {

        PostService postService = new PostServiceImpl();

        List<Post> posts = postService.getPostsByUserId(4);

        assertNotNull(posts);
        assertFalse(posts.isEmpty());

        for (Post post : posts) {
            System.out.println(post);
        }
    }

    @Test
    void getAllPosts() {
        PostService postService = new PostServiceImpl();

        List<Post> posts = postService.getAllPosts();

        assertNotNull(posts);
        assertFalse(posts.isEmpty());

        for (Post post : posts) {
            System.out.println(post);
        }
    }

    @Test
    void updatePost() {
        PostService postService = new PostServiceImpl();

        Post post = postService.getPostById(1);

        assertNotNull(post);

        post.setCaption("My updated Instagram post");
        post.setImageUrl("updated_nikitha.jpg");

        boolean result = postService.updatePost(post);

        assertTrue(result);
    }

    @Test
    void deletePost() {
        PostService postService = new PostServiceImpl();

        boolean result = postService.deletePost(2);

        assertTrue(result);
    }
}