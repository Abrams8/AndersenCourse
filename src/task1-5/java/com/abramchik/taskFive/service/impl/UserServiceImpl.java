package com.abramchik.taskFive.service.impl;

import com.abramchik.taskFive.dao.UserDAO;
import com.abramchik.taskFive.dao.impl.UserDAOImpl;
import com.abramchik.taskFive.entity.User;
import com.abramchik.taskFive.entity.UserHistoryOfPurchasing;
import com.abramchik.taskFive.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public User getUserByNameAndSurname(User user) {
        return userDAO.getUserByNameAndSurname(user);
    }

    @Override
    public boolean createNewUser(User user) {
        return userDAO.createNewUser(user);
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List<UserHistoryOfPurchasing> callStoresProcedure(int user_id) {
        return userDAO.callStoredProcedureTradesInfoByUserId(user_id);
    }


}
