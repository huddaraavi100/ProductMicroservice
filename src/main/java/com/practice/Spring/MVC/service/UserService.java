package com.practice.Spring.MVC.service;

import com.practice.Spring.MVC.model.User;
import com.practice.Spring.MVC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User saveUser(User user){
        return userRepo.save(user);
    }

    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public Optional<User> getById(Long id){
        return userRepo.findById(id);
    }

    public Optional< User> updateUser(long id,User updatedUser){
        return userRepo.findById(id).map(user ->{
            user.setAge(updatedUser.getAge());
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return userRepo.save(user);
        });

    }

    public boolean deleteUser(Long id){
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
