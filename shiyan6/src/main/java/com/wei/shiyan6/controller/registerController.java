package com.wei.shiyan6.controller;


import com.wei.shiyan6.ErrorMsg;
import com.wei.shiyan6.bean.RegisterForm;
import com.wei.shiyan6.model.User;
import com.wei.shiyan6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/register")
public class registerController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public String doRegister(@RequestBody RegisterForm register, ErrorMsg errorMsg) {
//        String name = register.getName();
//        System.out.println(name);
//        System.out.println(register.getName()+register.getEmail()+register.getPwd());
        User user = new User(register.getName(), register.getEmail(), register.getPwd());
        User adduser = userService.addUser(user, errorMsg);
        if (adduser == null) {
            return errorMsg.getErrorMsg();
        } else {
            return "doRegisterSuccess";
        }
    }
}
