package com.sd.controller;

import com.sd.entity.User;
import com.sd.service.ProductService;
import com.sd.service.UserService;
import com.sd.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String index (){
        return "back/index";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "/back/login";
    }


    @GetMapping("/shop")
    public String shop(Model model){
        Pageable pageable = PageRequest.of(0, 10);
        model.addAttribute("products", productService.findAll(pageable));
        return "front/index";
    }
}
