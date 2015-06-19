package me.joseph.web.app.controller;

import me.joseph.common.util.LogUtils;
import me.joseph.web.app.domain.entity.User;
import me.joseph.web.app.service.UserService;
import me.joseph.web.app.service.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUser(@RequestBody @Valid final User user) {
        LogUtils.debugLog.debug("Received request to create the {}", user);

        return userService.save(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> listUsers() {
        LogUtils.debugLog.debug("Reveived request to list all users");
        return userService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return "User Already Exists Exception";
    }

//    @ExceptionHandler
//    public ModelAndView handleException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception e) {
//        if (e instanceof UserAlreadyExistsException) {
//            LogUtils.debugLog.debug("User Already Exists Exception !!!'");
//        }
//
//        return new ModelAndView("closed");
//    }
}
