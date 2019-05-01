package com.sd.controller;

import com.sd.entity.User;
import com.sd.service.UserService;
import com.sd.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public String login(@RequestParam("email")String email, @RequestParam("password")String password, HttpServletRequest request, HttpSession session){
        session = request.getSession();
        User user = userService.checkLogin(email,password);
        if(user!=null){
            session.setAttribute("user",user);
            return "redirect:/product/get-all";
        }
        return "redirect:/login";
    }
}
