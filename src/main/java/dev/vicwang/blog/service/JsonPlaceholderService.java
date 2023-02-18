package dev.vicwang.blog.service;

import dev.vicwang.blog.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@Service
public interface JsonPlaceholderService {
    @GetExchange("/posts")
    List<Post> loadPosts();
}
