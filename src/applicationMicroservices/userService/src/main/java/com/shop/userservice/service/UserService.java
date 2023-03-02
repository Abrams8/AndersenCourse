package com.shop.userservice.service;

import com.shop.userservice.entity.Users;
import com.shop.userservice.entity.UserHistoryOfPurchasing;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    boolean createNewUser(Users user);

    Users getUserById(int id);

    List<UserHistoryOfPurchasing> callStoresProcedure(int user_id);

    List<Users> getAllUsers();

    Users findUserByUserName(String userName);

    UserDetails loadUserByUsername(String username);
}
