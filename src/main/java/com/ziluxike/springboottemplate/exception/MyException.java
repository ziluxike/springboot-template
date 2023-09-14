package com.ziluxike.springboottemplate.exception;

import com.ziluxike.springboottemplate.utils.ResCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ziluxike
 * @since 2023/9/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MyException extends RuntimeException{
    private final Integer code;
    private final String msg;

    public MyException(ResCode resCode){
        this.code = resCode.getCode();
        this.msg = resCode.getMsg();
    }
}
