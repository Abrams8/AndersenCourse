package com.shop.userservice.service.impl;

import com.shop.userservice.dao.UserDAO;
import com.shop.userservice.dao.impl.UserDAOImpl;
import com.shop.userservice.entity.Users;
import com.shop.userservice.entity.UserHistoryOfPurchasing;
import com.shop.userservice.service.UserService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean createNewUser(Users user) {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String password = user.getPassword();
        user.setPassword(bCrypt.encode(password));
        return userDAO.createNewUser(user);
    }

    @Override
    public Users getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List<UserHistoryOfPurchasing> callStoresProcedure(int user_id) {
        return userDAO.callStoredProcedureTradesInfoByUserId(user_id);
    }

    @Override
    public List<Users> getAllUsers(){
        return userDAO.getAllUsers();
    }

    @Override
    public Users findUserByUserName(String userName){
        return userDAO.findUserByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userDAO.loadUserByUsername(username);
    }


}
