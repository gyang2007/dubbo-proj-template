package com.gyang.web.controller;

import com.gyang.db.dao.TestDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gyang on 16-4-1.
 */
@Controller
public class DemoController {

    @Resource
    private TestDao dao;

    @RequestMapping(value = "/demo/id.do")
    public ModelAndView getMaxId(HttpServletRequest request, HttpServletResponse response) {
        int id = dao.select();
        ModelAndView view = new ModelAndView("Demo");
        view.addObject("id", id);

        return view;
    }
}
