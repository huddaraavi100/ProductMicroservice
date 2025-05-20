package com.practice.Spring.MVC.controller;

import com.practice.Spring.MVC.model.User;
import com.practice.Spring.MVC.repository.UserRepository;
import com.practice.Spring.MVC.response.ApiResponse;
import com.practice.Spring.MVC.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUser() {
        User input=new User(null,"Ravya","huddaraavi10@gmail.com",25);
        User created=new User(3L,"Ravya","huddaraavi10@gmail.com",25);
        when(service.saveUser(input)).thenReturn(created);
        ResponseEntity<ApiResponse<User>> result=controller.addUser(input);
        ApiResponse<User> body=result.getBody();
        User actualUser=body.getData();
        assertNotNull(actualUser);
        assertEquals("Ravya",actualUser.getName());
        

    }


}