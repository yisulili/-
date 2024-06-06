package com.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


import java.util.Date;
import java.util.Map;

public class JwtUtil {

    /*生成密钥*/
    public String createSH256(Map<String, Object> claims){
        String token = com.auth0.jwt.JWT.create().withClaim("user",claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60* 60*12L))
                .sign(Algorithm.HMAC256("XIAOSU"));
        return token;
    }
    /*解析令牌*/
    public DecodedJWT verifySH256(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("XIAOSU")).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }

    /*获取密钥中的某个值*/
    public String getTokenValue(DecodedJWT jwt,String key){
        Map<String, Object> claims = jwt.getClaim("user").asMap();
        String value = (String) claims.get(key);
        return value;
    }
    /*解析并且获取密钥一个键的值*/
    public String getVerifyValue(String token,String key){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("XIAOSU")).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Object> claims = jwt.getClaim("user").asMap();
        String value = (String) claims.get(key);
        return value;
    }
}
