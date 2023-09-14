package com.ziluxike.springboottemplate.annotation;



import cn.hutool.core.util.StrUtil;
import com.ziluxike.springboottemplate.exception.MyException;
import com.ziluxike.springboottemplate.utils.JwtUtil;
import com.ziluxike.springboottemplate.utils.ResCode;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author ziluxike
 * @since 2023/8/16
 */
@Component
public class UserIdResolver implements HandlerMethodArgumentResolver {
    // 用于表明哪些参数支持该解析器
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 判断当前参数是否加上UserId注解
        return parameter.hasParameterAnnotation(UserId.class);
    }

    // 进行参数解析的方法
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        // 从请求头中获取token，并获取token中的uid
        String token = webRequest.getHeader("Authorization");
        if (StrUtil.isNotBlank(token)) {
            return JwtUtil.getUserId(token);
        }
        throw new MyException(ResCode.IS_NOT_USER);
    }
}
