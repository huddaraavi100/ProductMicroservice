package com.practice.Spring.MVC.controller;

import com.practice.Spring.MVC.model.User;
import com.practice.Spring.MVC.response.ApiResponse;
import com.practice.Spring.MVC.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

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
    void testAddUser() {
        User input=new User(null,"Ravya","huddaraavi10@gmail.com",25);
        User created=new User(3L,"Ravya","huddaraavi10@gmail.com",25);
        when(service.saveUser(input)).thenReturn(created);
        ResponseEntity<ApiResponse<User>> result=controller.addUser(input);
        ApiResponse<User> body=result.getBody();
        User actualUser=body.getData();
        assertNotNull(actualUser);
        assertEquals("Ravya",actualUser.getName());
        

    }
    @Test
    void testGetById(){
        User user=new User(2L,"Aavi","aaviray19@yahoo.com",1);
        when(service.getByIdService(2L)).thenReturn(Optional.of(user));

        ResponseEntity<ApiResponse<?>> result=controller.getById(2L);
        ApiResponse<?> actualUser=result.getBody();
        assertNotNull(actualUser);
        assertTrue(actualUser.isSuccess());
        assertEquals("User Found",actualUser.getMessage());
        assertEquals(user,actualUser.getData());
    }
    @Test
    void getById_UserNotFound_Rturn404(){
        when(service.getByIdService(3L)).thenReturn(Optional.empty());
        ResponseEntity<ApiResponse<?>> result=controller.getById(3L);
        assertEquals(HttpStatus.NOT_FOUND,result.getStatusCode());
        ApiResponse<?> actualResult=result.getBody();
        assertNotNull(actualResult);
        assertFalse(actualResult.isSuccess());
        assertEquals("User Not Found",actualResult.getMessage());
    }




}