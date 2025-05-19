package com.practice.Spring.MVC.repository;

import com.practice.Spring.MVC.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Long> {

}
