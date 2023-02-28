package com.shop.userservice.dao;

import com.shop.userservice.entity.Users;
import com.shop.userservice.entity.UserHistoryOfPurchasing;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDAO {

    boolean createNewUser(Users user);

    Users getUserById(int id);

    List<UserHistoryOfPurchasing> callStoredProcedureTradesInfoByUserId(int userId);

    List<Users> getAllUsers();

    Users findUserByUserName(String userName);

    UserDetails loadUserByUsername(String username);
}
