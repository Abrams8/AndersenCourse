package com.shop.userservice.service.impl;

import com.shop.userservice.dao.UserDAO;
import com.shop.userservice.dao.impl.UserDAOImpl;
import com.shop.userservice.entity.User;
import com.shop.userservice.entity.UserHistoryOfPurchasing;
import com.shop.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }


}
