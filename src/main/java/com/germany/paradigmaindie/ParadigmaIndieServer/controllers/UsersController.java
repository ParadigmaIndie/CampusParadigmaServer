package com.germany.paradigmaindie.ParadigmaIndieServer.controllers;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.User;
import com.germany.paradigmaindie.ParadigmaIndieServer.services.AppUserDetailsService;
import com.sun.istack.NotNull;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final AppUserDetailsService appUserDetailsService;

    @Autowired
    public UsersController(AppUserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return appUserDetailsService.allCategories();
    }

    @PostMapping
    public User createUser(@RequestBody @NotNull User user) throws DuplicateMemberException, NotFoundException {

        return appUserDetailsService.createUser(user);
    }

    @PutMapping(path = "{userid}")
    public User updatedUser(@PathVariable("userid") @NotNull Long id, @NotNull @RequestBody User user) throws NotFoundException {
        return appUserDetailsService.updatedUser(user, id);
    }



    @DeleteMapping(path= "{userid}")
    public String deleteUser(@PathVariable("userid") Long id){
        appUserDetailsService.deleteUser(id);
        return "Delete Success";
    }
}
