package com.ziluxike.springboottemplate.annotation;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

/**
 * @author ziluxike
 * @since 2023/8/16
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.ziluxike.springboottemplate.annotation.PrintLog)")
    public void pt() {}

    @Around("pt()")
    public Object printLog(ProceedingJoinPoint pjp) throws Throwable {
        Object ret;
        handleBefore(pjp);
        ret = pjp.proceed();
        return ret;
    }

    private void handleBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        PrintLog printLog = getSystemLog(joinPoint);
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String content = "收到请求 | URL:" + request.getRequestURL() +
                    " | FUNCTION_NAME:" + printLog.value() +
                    " | HTTP_METHOD:" + request.getMethod() +
                    " | IP:" + request.getRemoteAddr() +
                    " | CLASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() +
                    " | ARGS:" + Arrays.toString(joinPoint.getArgs());
            log.info(content);
        }
    }

    private PrintLog getSystemLog(JoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        return signature.getMethod().getAnnotation(PrintLog.class);
    }
}
