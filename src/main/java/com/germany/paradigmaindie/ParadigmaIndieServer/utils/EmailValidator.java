package com.germany.paradigmaindie.ParadigmaIndieServer.utils;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        //TODO chekc Email Regex :D
        return true;
    }
}
