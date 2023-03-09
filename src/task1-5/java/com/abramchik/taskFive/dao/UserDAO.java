package com.abramchik.taskFive.dao;

import com.abramchik.taskFive.entity.User;
import com.abramchik.taskFive.entity.UserHistoryOfPurchasing;

import java.util.List;

public interface UserDAO {

    boolean createNewUser(User user);

    User getUserByNameAndSurname(User user);

    User getUserById(int id);

    List<UserHistoryOfPurchasing> callStoredProcedureTradesInfoByUserId(int userId);
}
