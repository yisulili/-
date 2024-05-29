package com.example.service.impl;

import com.example.domain.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.getUser(user);
    }

    //注册账号
    @Override
    public boolean addAccount(User user,String confirmPwd) {
        System.out.println(userMapper.getUser(user) + "000000");
        if(userMapper.getUser(user) == null && user.getUser_password().equals(confirmPwd)) {
            user.setUser_status("0");
            user.setUser_role("user");
            userMapper.insert(user);
            return true;
        }else return false;
    }


}
