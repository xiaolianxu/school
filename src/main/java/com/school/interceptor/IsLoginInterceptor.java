package com.school.interceptor;

import com.school.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IsLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) throws Exception {

        boolean flag= false;
        HttpSession session = servletRequest.getSession();
        User user= (User) session.getAttribute("user");
        if (null != user){
            //已登录
            flag =true;
            return flag;

        }
        String url = servletRequest.getRequestURI();
        String dos = "";
        if(url.length()>4){
            dos = url.substring(url.length()-3,url.length());
        }
        if(dos.equals(".do")){

        }else{
            servletResponse.sendRedirect("/school_war_exploded/loginPage");
        }
        return flag;
    }

}
