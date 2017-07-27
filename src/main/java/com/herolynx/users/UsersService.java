package com.herolynx.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UsersService {

    private final UUID id = UUID.randomUUID();
    @Autowired
    private ServiceConfig config;
    @Autowired
    private DbConfig dbConfig;
    private boolean healthy = true;
    private boolean ready = true;

    @GetMapping(path = "/hello")
    public String ping() {
        return String.format("[%s] %s", id, config.getMessage());
    }

}
