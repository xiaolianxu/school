package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class turnController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/homeHtml")
    public ModelAndView toRoleForm(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        return mv;
    }

    @RequestMapping(value = "/loginPage")
    public ModelAndView toIndex(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

}
