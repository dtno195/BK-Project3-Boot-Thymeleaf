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
import org.springframework.util.StringUtils;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public String list(Model model,
                       @RequestParam(name = "page",required = false,defaultValue = "1") Integer page,
                       @RequestParam(name = "size",required = false,defaultValue = "5") Integer size
                       ) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Product> findAllProduct = productService.findAll(pageable);
        List<Product> fullProduct = productService.findAllProduct();
        double sizeProduct = Math.round(fullProduct.size()%size) +1 ;
        int totalPages = findAllProduct.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("pagesize" ,sizeProduct );
        model.addAttribute("products", findAllProduct);
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
        if (file.isEmpty()) {
            Optional<Product> lastProduct = productService.findOne(product.getId());
            Product pr = lastProduct.get();
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
        Pageable pageable = PageRequest.of(0, 10);
        model.addAttribute("product", new Product());
        model.addAttribute("categories",categoryService.findAll(pageable));
        model.addAttribute("producers",producerService.findAll(pageable));
        return "back/product_edit";
    }

    @GetMapping("/search")
    public String search(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/product/get-all";
        }
        List<Product> opProduct = productService.search(term);
        if (!opProduct.isEmpty()){
            model.addAttribute("products", opProduct);
            return "back/product";
        }
        return "redirect:/product/get-all";

    }
}
