package com.marcobehler.quartz;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    public void sendTextUser() {
        System.out.println("I am sending a txt...");
    }
}
