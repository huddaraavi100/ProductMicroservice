package com.practice.Spring.MVC.controller;

import com.practice.Spring.MVC.model.User;
import com.practice.Spring.MVC.response.ApiResponse;
import com.practice.Spring.MVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<ApiResponse<User>> addUser(@RequestBody User user){
        User savedUser=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true," User added",savedUser));

    }


    @GetMapping("/users/{id}")
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

   @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<User>>> getAll(){
        List<User> users=userService.getAllUserService();
        return ResponseEntity.ok().body(new ApiResponse<>(true,"All Users",
                users));

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id,@RequestBody User updatedUser){
        Optional<User> updated=userService.updateUserService(id,updatedUser);
        if(updated.isPresent()){
            return ResponseEntity.ok(new ApiResponse<>(true,"User updated",updated.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false,
                "User not found",null));


    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id){
        boolean deleted = userService.deleteUser(id);
        if(deleted){
            return ResponseEntity.ok(new ApiResponse<>(true,"User deleted",null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false,
                "User not found",null));

    }

}
