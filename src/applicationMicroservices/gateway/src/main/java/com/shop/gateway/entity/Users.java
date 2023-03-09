package com.shop.gateway.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    int id;
    String userName;
    String password;
    String userRole;

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
