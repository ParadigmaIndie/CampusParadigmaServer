package com.germany.paradigmaindie.undefinedlearn.controllers;

import com.germany.paradigmaindie.undefinedlearn.models.User;
import com.germany.paradigmaindie.undefinedlearn.services.AppUserDetailsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final AppUserDetailsService appUserDetailsService;

    @Autowired
    public UsersController(AppUserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('READ_USER_PRIVILEGE')")
    public List<User> getAllUsers() throws NotFoundException {
        return appUserDetailsService.getAllUsers();
    }

    public String deleteUser(){
        return null;
    }

    public User updatedUser(){
        return null;
    }

    public User getUser(){
        return null;
    }

    public User createUser(){
        return null;
    }

    @PostMapping()
    public User singup(User user){
        return null;
    }
}
