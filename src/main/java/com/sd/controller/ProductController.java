package com.sd.controller;

import com.sd.entity.Category;
import com.sd.entity.Producer;
import com.sd.entity.Product;
import com.sd.service.CategoryService;
import com.sd.service.ProducerService;
import com.sd.service.ProductService;
import com.sd.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/get-all")
    public String list(Model model) {
        Pageable pageable = PageRequest.of(0, 10);
        model.addAttribute("products", productService.findAll(pageable));
        return "back/product";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Pageable pageable = PageRequest.of(0, 10);
        Optional<Product> opProduct = productService.findOne(id);
        model.addAttribute("categories",categoryService.findAll(pageable));
        model.addAttribute("producers",producerService.findAll(pageable));
        model.addAttribute("product", opProduct);
        return "back/product_edit";
    }

    @PostMapping("/save")
    public String save(Product product, BindingResult result, RedirectAttributes redirect,
                       @RequestParam("image") MultipartFile file,
                       @RequestParam("producerId") Long producerId,
                       @RequestParam("categoryId") Long categoryId,
                       Model model) {
        Optional<Product> lastProduct = productService.findOne(product.getId());
        Product pr = lastProduct.get();
        if (file.isEmpty()) {
            String currentImage = pr.getImage();
            product.setImage(currentImage);
        } else {
            try {
                Path fileNameAndPath = Paths.get(Constant.UPLOAD_FOLDER, file.getOriginalFilename());
                Files.write(fileNameAndPath, file.getBytes());
                System.out.println("File Name : " + fileNameAndPath.toString());
                System.out.println(file.getOriginalFilename());
                String fileName = fileNameAndPath.toString();
                String curentImage = "/image/" + file.getOriginalFilename();
                product.setImage(curentImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Optional<Producer> producerIdNew = producerService.findOne(producerId);
        Producer producerNew = producerIdNew.get();
        product.setProducer(producerNew);
        Optional<Category> categoryIdNew = categoryService.findOne(categoryId);
        Category categoryNew = categoryIdNew.get();
        product.setCategory(categoryNew);
        productService.save(product);
        return "redirect:/product/get-all";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        productService.delete(id);
        return "redirect:/product/get-all";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "back/product_edit";
    }
}
