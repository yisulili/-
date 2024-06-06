package com.example.service;

import com.example.domain.User;

public interface UserService  {
//    User login(User user);
    User login(User user);

    boolean addAccount(User user,String confirmPwd);
    User findUser(String user_email);

}
