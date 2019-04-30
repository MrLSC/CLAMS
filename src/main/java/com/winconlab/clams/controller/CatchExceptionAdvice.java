package com.winconlab.clams.controller;

import com.winconlab.clams.pojo.GlobalResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 捕获全局异常
 */
@ControllerAdvice
public class CatchExceptionAdvice {
    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public GlobalResult errorHandler(Exception ex) {
        ex.printStackTrace();
        GlobalResult build = GlobalResult.build(9999, "全局异常:" + ex.toString() + ":" + ex.getMessage());
        return build;
    }
}
