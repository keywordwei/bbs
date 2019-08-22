package com.wei.shiyan6.controller;

import com.wei.shiyan6.ErrorMsg;
import com.wei.shiyan6.bean.LoginForm;
import com.wei.shiyan6.model.User;
import com.wei.shiyan6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(ErrorMsg error, @RequestBody LoginForm loginForm, HttpSession session) {
//        System.out.println(loginForm.getName()+loginForm.getPwd());
        User user = new User(loginForm.getName(), loginForm.getPwd());
        User loginUser = userService.checkLogin(user, error);
        if (loginUser == null) {
            return error.getErrorMsg();
        } else {
            session.setAttribute("name", loginUser.getUsername());
            return "success" + loginUser.getUsername();
        }
    }

    //链接访问 为get
    @ResponseBody
    @RequestMapping(value = "/doLoginout", method = RequestMethod.GET)
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "doLoginout";
    }
}
