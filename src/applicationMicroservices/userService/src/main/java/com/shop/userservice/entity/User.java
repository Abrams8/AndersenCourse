package com.shop.userservice.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    int id;

    String name;

    String surname;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
