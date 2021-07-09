package com.germany.paradigmaindie.undefinedlearn.controllers;

import com.germany.paradigmaindie.undefinedlearn.models.User;
import com.germany.paradigmaindie.undefinedlearn.services.AppUserDetailsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class TemplateController {




    @GetMapping("login")
    public String getLoginView(){
        return "login";
    }

    @GetMapping("courses")
    public String getCoursesView(){
        return "courses";
    }


}
