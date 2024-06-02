package com.example.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class ResourceInterceptor implements HandlerInterceptor {
    //目标资源方法运行前执行，返回true ：放行，false 拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        try{
            String token = request.getHeader("token");
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("XIAOSU")).build();
            DecodedJWT verify = jwtVerifier.verify(token); // 验证token，生成解析后的jwt对象
            System.out.println(verify.getClaims().get("user"));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
