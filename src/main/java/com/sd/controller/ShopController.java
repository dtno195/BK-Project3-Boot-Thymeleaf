package com.sd.controller;

import com.sd.bean.Cart;
import com.sd.entity.Product;
import com.sd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ShopController {

    @Autowired
    ProductService productService;

    @GetMapping("/shop")
    public String shop(Model model, @RequestParam(name = "page",required = false,defaultValue = "1") Integer page,
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

    @GetMapping("/shop/{id}/show")
    public String productDetail(Model model, @PathVariable("id") Long id){
        Optional<Product> optionalProduct = productService.findOne(id);
        Product product = optionalProduct.get();
        model.addAttribute("product",product);
        return "front/product_detail";
    }

    @GetMapping("/shop/cart")
    public String cartShop(Model model, HttpSession session,HttpServletRequest request){
        session = request.getSession();
        List<Cart> cart = (List<Cart>)session.getAttribute("cart");
        model.addAttribute("listProduct" , cart);
        if (cart!=null){
            model.addAttribute("total",getTotal(cart));
        }

        return "front/cart";
    }

    @GetMapping("/shop/confirmation")
    public String confirm(Model model,HttpSession  session){
        return "/front/confirmation";
    }

    @GetMapping("/shop/{id}/addToCart")
    public String addToCart(Model model, HttpSession session, HttpServletRequest request,@PathVariable("id")Long id,@RequestParam(name = "qty",required = false,defaultValue = "1") int quantity ){
        session = request.getSession();
        List<Cart> lstCart = (List<Cart>) session.getAttribute("cart");
        Optional<Product> opProduct = productService.findOne(id);
        Product product = opProduct.get();
        if(lstCart == null){
            lstCart = new ArrayList<>();
            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setQuantityBuy(quantity);
            lstCart.add(cart);
        }
        else{
            boolean flag = true;
            for (int i = 0; i < lstCart.size(); i++) {
                if(lstCart.get(i).getProduct().getId()  == id){
                    int quantityBuy = lstCart.get(i).getQuantityBuy();
                    lstCart.get(i).setQuantityBuy(quantityBuy+1);
                    session.setAttribute("cart", lstCart);
                    flag = false;
                    return "redirect:/shop";
                }
            }
            if(flag){
                Cart cart = new Cart(product,quantity);
                lstCart.add(cart);
            }
        }
        session.setAttribute("cart", lstCart);

        return "redirect:/shop";
    }

    public float getTotal(List<Cart> listCart){
        float total = 0;
            for(Cart c: listCart){
                total += c.getQuantityBuy() * c.getProduct().getPrize();
            }
            return total;
    }

}
