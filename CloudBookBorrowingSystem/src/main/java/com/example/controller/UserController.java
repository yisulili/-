package com.example.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.domain.Resp;
import com.example.domain.User;
import com.example.service.UserService;
import com.example.utils.VerifyCodeUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    /*生成验证码的接口*/
    @GetMapping("/generateImageCode")
    public void generateImageCode(HttpSession session, HttpServletResponse response) throws IOException {
        //随机生成四位随机数
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //保存到session域中
        session.setAttribute("code", code);
        //根据随机数生成图片，reqponse响应图片
        response.setContentType("image/png");
        ServletOutputStream os = response.getOutputStream();
        VerifyCodeUtils.outputImage(130, 60, os, code);
    }


    @PostMapping("/login")
    public Resp login(User user, HttpServletRequest request,HttpSession session){
        try{
            User u = userService.login(user);
            if(u != null){

                /**验证码*/
//                String userInputCode = request.getParameter("code");
//                // 获取Session中保存的验证码
//                String sessionCode = (String) session.getAttribute("code");
//                // 验证验证码
//                if (!sessionCode.equals(userInputCode)) {
//                    // 验证码错误
//                    return new Resp(403,"验证码错误",null);
//                }
                /**验证码*/

                request.getSession().setAttribute("USER_SESSION",u);
                //登录成功，生成令牌，发下令牌
                Map<String, Object> claims = new HashMap<>();
                claims.put("id",u.getUser_id());
                claims.put("role",u.getUser_role());
                claims.put("email",u.getUser_email());
                String token = JWT.create().withClaim("user",claims)
                        .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60* 60*12L))
                        .sign(Algorithm.HMAC256("XIAOSU"));
                return new Resp(200,"登录成功:", token);
            }
            return new Resp(401,"用户名或密码错误",null);
        }catch (Exception e){
            e.printStackTrace();
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
    /*忘记密码*/

    @PostMapping("/newPwd")
    public Resp newPwd(String user_email,String newPwd ,HttpServletRequest request,HttpSession session){
        try {
            User u = userService.findUser(user_email);
            if(u != null){
                String userInputCode = request.getParameter("code");
                // 获取Session中保存的验证码
                String sessionCode = (String) session.getAttribute("code");
                // 验证验证码
                if (!sessionCode.equals(userInputCode)) {
                    // 验证码错误
                    return new Resp(403,"验证码错误",null);
                }
                u.setUser_password(newPwd);
                return new Resp(200,"更新成功",null);
            }
            return new Resp(403,"更新失败",null);
        }catch (Exception e){
            e.printStackTrace();
            return new Resp(500,"系统错误",null);
        }
    }

}
