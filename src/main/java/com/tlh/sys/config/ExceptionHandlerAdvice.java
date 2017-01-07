package com.tlh.sys.config;

import org.apache.shiro.authc.AccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局的Controller的配置
 * Created by hup on 2017/1/7.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception exception){
        ModelAndView mv=new ModelAndView();
        if(exception instanceof AccountException){//如果是shiro的认证异常
            mv.addObject("msg",exception.getMessage());
            mv.setViewName("login");
        }
        return mv;
    }

}
