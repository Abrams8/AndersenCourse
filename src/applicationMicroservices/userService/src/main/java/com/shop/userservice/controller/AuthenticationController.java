package com.shop.userservice.controller;

import com.shop.userservice.config.JwtUtils;
import com.shop.userservice.dao.AuthenticationRequest;
import com.shop.userservice.entity.Users;
import com.shop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    public final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final UserDetails user = userService.loadUserByUsername(request.getUsername());
        if (user != null) {
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody AuthenticationRequest request) throws SQLException {
        Users newUser = new Users();
        newUser.setUserName(request.getUsername());
        newUser.setPassword(request.getPassword());
        userService.createNewUser(newUser);
        final UserDetails user = userService.loadUserByUsername(newUser.getUserName());
        return ResponseEntity.ok(jwtUtils.generateToken(user));

    }

}
