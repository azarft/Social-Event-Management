package com.alatoo.socialEventManagement.repositories;

import com.alatoo.socialEventManagement.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
