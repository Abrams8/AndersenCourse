package com.shop.userservice.controller;

import com.shop.userservice.entity.User;
import com.shop.userservice.service.UserService;
import com.shop.userservice.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/users"))
public class UserController {

    UserService userService = new UserServiceImpl();

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get")
    public User getUserByNameAndSurname(@RequestParam String name, String surname) {
        User user = new User(name,surname);
        return userService.getUserByNameAndSurname(user);
    }

    @PostMapping("/create")
    public boolean createNewUser(@RequestParam String name, @RequestParam String surname){
        User user = new User(name,surname);
        return userService.createNewUser(user);
    }
}
