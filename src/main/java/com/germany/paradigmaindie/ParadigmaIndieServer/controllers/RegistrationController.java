package com.germany.paradigmaindie.ParadigmaIndieServer.controllers;

import com.germany.paradigmaindie.ParadigmaIndieServer.services.RegistrationService;
import com.germany.paradigmaindie.ParadigmaIndieServer.utils.RegistrationRequest;
import com.sun.istack.NotNull;
import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @CrossOrigin
    @PostMapping()
    public  String register(@RequestBody @NotNull  RegistrationRequest request) throws NotFoundException {
        return registrationService.register(request);
    }

    @CrossOrigin
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
