package com.practice.Spring.MVC.controller;

import com.practice.Spring.MVC.model.User;
import com.practice.Spring.MVC.response.ApiResponse;
import com.practice.Spring.MVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<User>> addUser(@RequestBody User user){
        User savedUser=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true," User added",savedUser));

    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable Long id){
        Optional<User> user=userService.getByIdService(id);
        /** lets say user=null, but line no 36 code is executing**/
        if(user.isPresent()){
            return ResponseEntity.ok().body(new ApiResponse<>(true,"User Found",user.get()));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false,"User not Found",null));
        }

    }

 /**   @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<User>>> getAll(){
        

    }
**/
}
