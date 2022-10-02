package com.witalis.jkit.shell.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    /**
     * It uses to greet the username.
     * <p/>
     * @param username the name of user
     * @return the greeting message for user
     */
    public String greeting(String username) {
        return String.format("Hello, %s", username);
    }
}
