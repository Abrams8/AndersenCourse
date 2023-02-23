package com.shop.userservice.dao;

import com.shop.userservice.entity.User;
import com.shop.userservice.entity.UserHistoryOfPurchasing;

import java.util.List;

public interface UserDAO {

    boolean createNewUser(User user);

    User getUserByNameAndSurname(User user);

    User getUserById(int id);

    List<UserHistoryOfPurchasing> callStoredProcedureTradesInfoByUserId(int userId);

    List<User> getAllUsers();
}
