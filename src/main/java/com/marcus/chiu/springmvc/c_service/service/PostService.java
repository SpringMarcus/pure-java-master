package com.marcus.chiu.springmvc.c_service.service;

import com.marcus.chiu.springmvc.e_entity.entity.Post;

import java.util.List;

/**
 * Created by marcus.chiu on 10/21/16.
 */
public interface PostService {
    Post findById(int post_id);

    void savePost(Post post);

    void updatePost(Post post);

    void deletePostById(int post_id);

    List<Post> findAllPosts();
}
