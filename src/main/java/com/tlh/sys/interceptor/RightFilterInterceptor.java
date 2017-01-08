package com.tlh.sys.interceptor;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hup on 2017/1/8.
 */
public class RightFilterInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        uri=uri.substring(uri.lastIndexOf("/"));
        //如果是首页或登录请求
        if(uri.equals("/")||uri.equals("/login")){
            return true;
        }
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if(principal==null){
            httpServletRequest.setAttribute("msg","请重新登陆");
            httpServletRequest.getRequestDispatcher("login").forward(httpServletRequest,httpServletResponse);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
