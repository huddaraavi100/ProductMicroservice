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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;


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
    void getById_WhenUserExists_ReturnsUser(){
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
    void getById_WhenUserNotFound_Returns404(){
        when(service.getByIdService(3L)).thenReturn(Optional.empty());
        ResponseEntity<ApiResponse<?>> result=controller.getById(3L);
        assertEquals(HttpStatus.NOT_FOUND,result.getStatusCode());
        ApiResponse<?> actualResult=result.getBody();
        assertNotNull(actualResult);
        assertFalse(actualResult.isSuccess());
        assertEquals("User Not Found",actualResult.getMessage());
    }

    @Test
    void testGetAll(){
        List<User> list= Arrays.asList(
                new User(1L,"Kaya","kaya100@yahoo.com",20),
                new User(2L,"Siya","siya100@gmail.com",20)
        );
        when(service.getAllUserService()).thenReturn(list);
       ResponseEntity<ApiResponse<List<User>>> users=controller.getAll();
        assertEquals(HttpStatus.OK,users.getStatusCode());
     ApiResponse<List<User>> body=users.getBody();
     assertNotNull(body);
     assertTrue(body.isSuccess());
     assertEquals("All Users",body.getMessage());

        List<User> result=body.getData();
        assertEquals("Kaya",result.get(0).getName());
        assertEquals("Siya",result.get(1).getName());


    }

    @Test
    void testUpdateUser(){

        User input=new User(null,"Kia","new@yahoo.com",2);
        User updated=new User(1L,"Kia","new@yahoo.com",2);
        when(service.updateUserService(1L,input)).thenReturn(Optional.of(updated));
        ResponseEntity<ApiResponse<User>> response=controller.updateUser(1L,input);
        assertEquals(HttpStatus.OK,response.getStatusCode());

        ApiResponse<User> body=response.getBody();
        assertNotNull(body);
        assertTrue(body.isSuccess());
        assertEquals("User updated", body.getMessage());

        User result=body.getData();
        assertNotNull(result);
        assertEquals("Kia",result.getName());

    }

    @Test
    void testUpdateUser_ifNotFound(){

        User input=new User(null,"Kia","new@yahoo.com",2);

        when(service.updateUserService(2L,input)).thenReturn(Optional.empty());
        ResponseEntity<ApiResponse<User>> response=controller.updateUser(2L,input);

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

        ApiResponse<User> body=response.getBody();
        assertFalse(body.isSuccess());
        assertEquals("User not found",body.getMessage());
        assertNull(body.getData());



    }

    @Test
    void testDeleteUser() {
        when(service.deleteUser(1L)).thenReturn(true);
        ResponseEntity<ApiResponse<Void>> result = controller.deleteUser(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());

        ApiResponse<Void> body = result.getBody();
        assertNotNull(body);
        assertEquals(true, body.isSuccess());
        assertEquals("User deleted", body.getMessage());
        assertNull(body.getData());
    }


    @Test
    void testDeleteUser_whenNotFound() {
        when(service.deleteUser(2L)).thenReturn(false);
        ResponseEntity<ApiResponse<Void>> result = controller.deleteUser(2L);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

        ApiResponse<Void> body = result.getBody();
        assertNotNull(body);
        assertEquals(false, body.isSuccess());
        assertEquals("User not found" , body.getMessage());
        assertNull(body.getData());
        verify(service, times(1)).deleteUser(2L);
    }



}