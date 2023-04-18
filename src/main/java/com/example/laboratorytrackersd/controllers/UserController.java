package com.example.laboratorytrackersd.controllers;

import com.example.laboratorytrackersd.model.User;
import com.example.laboratorytrackersd.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@EnableMethodSecurity
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    //@PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<List<User>> getAllUsers(){

        return ResponseEntity.ok().body(userService.fetchUsers());
    }

    @PostMapping("/createUser")
    @PreAuthorize("hasAuthority('TEACHER')")
    public User createUser(@RequestBody User user){

        return userService.createUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<?>deleteUser(@PathVariable("id") Integer id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateUser/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public User updateUser(@RequestBody User user, @PathVariable("id") Integer id){
        return userService.updateUser(user, id);
    }
}
