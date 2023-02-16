package com.abramchik.taskFive.service;

import com.abramchik.taskFive.entity.User;
import com.abramchik.taskFive.entity.UserHistoryOfPurchasing;

import java.util.List;

public interface UserService {

    User getUserByNameAndSurname(User user);

    boolean createNewUser(User user);

    User getUserById(int id);

    List<UserHistoryOfPurchasing> callStoresProcedure(int user_id);
}
