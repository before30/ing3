package me.joseph.web.app.controller;


import me.joseph.common.util.LogUtils;
import me.joseph.web.app.domain.UserCreateForm;
import me.joseph.web.app.domain.entity.User;
import me.joseph.web.app.service.UserService;
import me.joseph.web.app.service.exception.UserAlreadyExistsException;
import me.joseph.web.app.validator.UserCreateFormPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserCreateController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCreateFormPasswordValidator passwordValidator;

    @RequestMapping(value = "/user_create.html", method = RequestMethod.GET)
    public ModelAndView getCreateUserView() {
        LogUtils.debugLog.debug("Received request for user create view");
        return new ModelAndView("user_create", "form", new UserCreateForm());
    }

    @RequestMapping(value = "/user_create.html", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("form") @Valid UserCreateForm form, BindingResult result) {
        LogUtils.debugLog.debug("Received request to create {}, with result={}", form, result);
        if (result.hasErrors()) {
            return "user_create";
        }

        try {
            userService.save(new User(form.getId(), form.getPassword2()));
        } catch (UserAlreadyExistsException e) {
            LogUtils.debugLog.info("Tried to create user with existing id");
            LogUtils.debugLog.info("Error Messages : ", e);
            result.reject("user.error.exists");

            return "user_create";
        }

        return "redirect:/user_list.html";
    }

}