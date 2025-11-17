package com.ilhamyp.blog.repository;

import com.ilhamyp.blog.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Integer> {

    Optional<Post> findBySlugAndIsDeleted(String slug, boolean isDeleted);

    List<Post> findByIsDeleted(boolean isDeleted);

    Optional<Post> findByIdAndIsDeleted(Integer id, Boolean isDeleted);
}
