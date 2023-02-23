package com.shop.userservice.service;

import com.shop.userservice.entity.User;
import com.shop.userservice.entity.UserHistoryOfPurchasing;

import java.util.List;

public interface UserService {

    User getUserByNameAndSurname(User user);

    boolean createNewUser(User user);

    User getUserById(int id);

    List<UserHistoryOfPurchasing> callStoresProcedure(int user_id);

    List<User> getAllUsers();
}
