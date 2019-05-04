package com.sd.controller;

import com.sd.entity.Product;
import com.sd.entity.User;
import com.sd.service.ProductService;
import com.sd.service.UserService;
import com.sd.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public String shop(Model model,@RequestParam(name = "page",required = false,defaultValue = "1") Integer page,
                       @RequestParam(name = "size",required = false,defaultValue = "12") Integer size){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Product> findAllProduct = productService.findAll(pageable);
        int totalPages = findAllProduct.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("products", findAllProduct);
        return "front/index";
    }
}
