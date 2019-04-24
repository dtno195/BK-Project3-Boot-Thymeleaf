package com.sd.controller;

import com.sd.entity.Category;
import com.sd.entity.Producer;
import com.sd.service.CategoryService;
import com.sd.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("get-all")
    public String list(Model model) {
        Pageable pageable = PageRequest.of(0, 10);
        model.addAttribute("categories", categoryService.findAll(pageable));
        return "back/category";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        categoryService.delete(id);
        return "redirect:/category/get-all";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("category", new Category());
        return "back/category_edit";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Pageable pageable = PageRequest.of(0, 10);
        Optional<Category> opCategory = categoryService.findOne(id);
        model.addAttribute("category", opCategory);
        return "back/category_edit";
    }

    @PostMapping("/save")
    public String save(Category category, BindingResult result, RedirectAttributes redirect,
                       Model model) {
        categoryService.save(category);
        return "redirect:/category/get-all";
    }
}
