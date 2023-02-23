package com.abramchik.taskFive.controller;

import com.abramchik.taskFive.entity.User;
import com.abramchik.taskFive.service.UserService;
import com.abramchik.taskFive.service.impl.UserServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/users"))
public class UserController {
    UserService userService = new UserServiceImpl();

    @GetMapping("/getUser/{name}/{surname}")
    public User getAllUsers(@PathVariable
                                String name, @PathVariable String surname) {
        User user = new User(name,surname);
        return userService.getUserByNameAndSurname(user);
    }

    @PostMapping("/create")
    public boolean createNewUser(@RequestParam("name") String name, @RequestParam("surname") String surname, Model model){
        User user = new User(name,surname);
        return userService.createNewUser(user);
    }
}
