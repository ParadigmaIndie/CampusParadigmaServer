package com.germany.paradigmaindie.ParadigmaIndieServer.utils;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private  final String username;
    private final String email;
    private final String password;
}
