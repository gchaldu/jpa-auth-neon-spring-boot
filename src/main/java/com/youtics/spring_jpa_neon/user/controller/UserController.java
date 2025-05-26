package com.youtics.spring_jpa_neon.user.controller;

import com.youtics.spring_jpa_neon.user.model.User;
import com.youtics.spring_jpa_neon.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User register(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }
}
