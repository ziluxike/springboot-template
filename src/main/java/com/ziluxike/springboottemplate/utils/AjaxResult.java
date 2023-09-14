package com.ziluxike.springboottemplate.utils;

import lombok.Data;
import lombok.ToString;

/**
 * @author ziluxike
 * @since 2023/9/14
 */
@Data
@ToString
public class AjaxResult<U> {

    private Integer code;
    private String msg;
    private Object data;

    public AjaxResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public AjaxResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回成功消息
     *
     * @param resCode  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static <U> AjaxResult<U> success(ResCode resCode, U data) {
        return new AjaxResult<>(resCode.getCode(), resCode.getMsg(), data);
    }

    public static <U> AjaxResult<U> success(ResCode resCode) {
        return new AjaxResult<>(resCode.getCode(), resCode.getMsg());
    }

    /**
     * 返回错误消息
     *
     * @param resCode  返回内容
     * @return 警告消息
     */
    public static <U> AjaxResult<U> fail(ResCode resCode) {
        return new AjaxResult<>(resCode.getCode(), resCode.getMsg());
    }


}
