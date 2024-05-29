package com.example.controller;

import com.example.domain.Resp;
import com.example.domain.User;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

//@RequestMapping("/users")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Resp login(User user, HttpServletRequest request){
        try{
            User u = userService.login(user);
            if(u != null){
                request.getSession().setAttribute("USER_SESSION",u);
                return new Resp(200,"登录成功",u);
            };
//            request.setAttribute("msg","用户名或密码错误");
            return new Resp(401,"用户名或密码错误",null);
        }catch (Exception e){
            e.printStackTrace();
//            request.setAttribute("msg","系统错误");
            return new Resp(500,"系统错误",null);
        }
    }


    //注册账号
    @PostMapping("/register")
    public Resp addAcount(User user,String confirmPwd ){
        System.out.println(user);
        try {
            if(userService.addAccount(user,confirmPwd)) return new Resp(200,"注册成功",null);
            return new Resp(403,"注册失败",null);
        }catch (Exception e){
            e.printStackTrace();
            return new Resp(500,"系统错误",null);
        }
    }



}
