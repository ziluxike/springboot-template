package com.ziluxike.springboottemplate.utils;

import cn.hutool.core.bean.BeanUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ziluxike.springboottemplate.exception.MyException;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ziluxike
 * @since 2023/8/16
 */
@Slf4j
public class JwtUtil {

    private static final String TOKEN_SECRET =
            "finpywwgkjdklfsKJHKLJGBkjvdkfsfbliwjbgKJNDJFBjndfkjbnldKJLKJBNDKFJBN";

    /**
     * 生成签名，24小时过期
     *
     * @param **userInfo** 用户信息 用户姓名
     * @param **other**    用户其他信息 用户id
     * @return
     */
    public static String sign(Integer id, Long tokenOverTime) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + tokenOverTime);
            //私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置Header
            Map<String, Object> header = new HashMap<>();
            header.put("key", "ziluxike_made...");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("id", id)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("jwt 签发出现错误! ", e);
            return null;
        }
    }

    /**
     * 检验token是否正确
     *
     * @param **token**
     * @return
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);//未验证通过会抛出异常
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static Integer getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asInt();
        } catch (JWTDecodeException e) {
            throw new MyException(ResCode.IS_NOT_USER);
        }
    }


}
