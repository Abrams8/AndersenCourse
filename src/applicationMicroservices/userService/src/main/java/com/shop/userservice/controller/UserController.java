package com.shop.userservice.controller;

import com.shop.userservice.entity.Users;
import com.shop.userservice.service.UserService;
import com.shop.userservice.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    UserService userService = new UserServiceImpl();

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user")
    public Users getUserByNameAndSurname(@RequestParam String userName) {
        return userService.findUserByUserName(userName);
    }
}
