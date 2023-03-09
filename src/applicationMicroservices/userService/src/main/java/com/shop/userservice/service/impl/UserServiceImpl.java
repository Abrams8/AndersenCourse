package com.shop.userservice.service.impl;

import com.shop.userservice.dao.UserDAO;
import com.shop.userservice.dao.impl.UserDAOImpl;
import com.shop.userservice.entity.UserHistoryOfPurchasing;
import com.shop.userservice.entity.UserRole;
import com.shop.userservice.entity.Users;
import com.shop.userservice.service.UserService;
import io.jsonwebtoken.Jwts;
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
    public List<Users> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public Users findUserByUserName(String userName) {
        return userDAO.findUserByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userDAO.loadUserByUsername(username);
    }

    @Override
    public Users validateToken(String token) {
        String login = Jwts.parser()
                .setSigningKey("secret")
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        UserDetails userDetails = userDAO.loadUserByUsername(login);

        if (userDetails == null) {
            throw new RuntimeException("User not found");
        }
        Users user = new Users();
        user.setUserName(userDetails.getUsername());
        String userRole = userDetails.getAuthorities().stream().findFirst().get().toString();
        switch (userRole) {
            case "USER" -> user.setUserRole(UserRole.USER);
            case "MANAGER" -> user.setUserRole(UserRole.MANAGER);
            case "ADMIN" -> user.setUserRole(UserRole.ADMIN);
        }
        return user;
    }

}
