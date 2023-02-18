package dev.vicwang.blog.repository;

import dev.vicwang.blog.model.Post;
import org.springframework.data.repository.ListCrudRepository;

public interface PostRepository extends ListCrudRepository<Post, Integer> {
}
