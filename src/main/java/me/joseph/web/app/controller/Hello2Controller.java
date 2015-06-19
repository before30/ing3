package me.joseph.web.app.controller;

import me.joseph.common.util.LogUtils;
import me.joseph.web.app.domain.entity.SomeThing;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Hello2Controller {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) throws Exception {
        LogUtils.debugLog.error("1111");

        ModelMap model = new ModelMap();
        model.addAttribute("h1str", "Hello my world!");


        return new ModelAndView("home", model);
    }

    @RequestMapping(value = "/closed.html")
    public ModelAndView closed() throws Exception {
        LogUtils.debugLog.debug("Reqeust Closed.html");

        return new ModelAndView("closed");
    }
}
