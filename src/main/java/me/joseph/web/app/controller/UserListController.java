package me.joseph.web.app.controller;

import me.joseph.common.util.LogUtils;
import me.joseph.web.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserListController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user_list.html")
    public ModelAndView getListUserView(){
        LogUtils.debugLog.debug("Received request to get user list view");
        ModelMap model = new ModelMap();
        model.addAttribute("users", userService.getList());

        return new ModelAndView("user_list", model);
    }
}
