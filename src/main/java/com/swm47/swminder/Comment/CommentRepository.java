package com.swm47.swminder.Comment;

import com.swm47.swminder.Comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
