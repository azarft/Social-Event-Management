package com.alatoo.socialEventManagement.repositories;

import com.alatoo.socialEventManagement.entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Long> {

}
