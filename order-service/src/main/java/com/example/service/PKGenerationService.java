package com.example.service;


public class PKGenerationService {

    public static String pkGen(String prefix) {
        return prefix + String.format("%03d", (int) (Math.random() * 1000));
    }
}
