package com.ziluxike.springboottemplate.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author ziluxike
 * @since 2023/9/14
 * <p>业务处理成功 -> 1000 ;
 * <p>业务处理失败 -> 1001 ;
 * <p>系统异常    -> 1003 ;
 * <p>参数异常    -> 1004 ;
 * <p>用户登录超时 -> 1005 ;
 * <p>用户权限不够 -> 1006 ;
 */
@AllArgsConstructor
@Getter
public enum ResCode {
    // 用户验证模块
    IS_NOT_USER(1006, "您的权限不足!"),
    IS_ADMIN(1000, "欢迎管理员！"),
    IS_NOT_ADMIN(1006, "您不是管理员！"),

    // 用户登录模块
    SIGN_ERROR(1005, "用户身份已过期！"),
    CAPTCHA_ERROR(1001, "验证码错误！"),
    CAPTCHA_CORRECT(1000, "验证码正确！"),
    CAPTCHA_GET_SUCCESS(1000, "验证码获取成功！"),
    CAPTCHA_GET_ERROR(1001, "您已经获取过验证码了，请再等会吧！"),
    USER_IS_NOT_EXIST(1001, "账号不存在！"),
    USER_STATUS_ERROR(1001, "该账号不可用！"),
    PASSWORD_ERROR(1004, "密码错误！"),
    LOGIN_SUCCESS(1000, "登录成功！"),
    LOGIN_INFO_ERROR(1004,"数据填写错误, 请检查"),
    REGISTER_SUCCESS(1000, "注册成功！"),
    REGISTER_ERROR(1001, "注册失败,请联系管理员!"),
    CODE_EXPIRED(1004, "验证码输入错误或已过期!"),
    CAPTCHA_RECOMMIT_ERROR(1001, "请重新试试看吧～"),
    REGISTER_EXIST(1001, "注册失败,您已经注册过了!"),
    REGISTER_PHONE_OR_USERNAME_EXIST(1001, "注册失败,您的手机号或账号已被注册!"),
    PHONE_ERROR(1001,"手机号错误!"),
    FIND_PASSWORD_NOT_EXIST(1001,"账号不存在!"),
    USER_LOGOUT_SUCCESS(1000,"登出成功!"),
    PASSWORD_LENGTH_ERROR(1004,"密码长度错误!"),
    USERNAME_LENGTH_ERROR(1004,"账号长度错误!"),
    REMOTE_LOGIN(1001,"账号在其他地方登陆,请重新登陆!");

    private final Integer code;
    private final String msg;
}
