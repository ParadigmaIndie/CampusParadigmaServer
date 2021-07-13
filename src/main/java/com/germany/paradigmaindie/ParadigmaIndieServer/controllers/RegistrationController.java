package com.germany.paradigmaindie.ParadigmaIndieServer.controllers;

import com.germany.paradigmaindie.ParadigmaIndieServer.services.RegistrationService;
import com.germany.paradigmaindie.ParadigmaIndieServer.utils.RegistrationRequest;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import net.bytebuddy.pool.TypePool;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    RegistrationService registrationService;

    @PostMapping
    public  String register(@RequestBody RegistrationRequest request) throws NotFoundException {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
