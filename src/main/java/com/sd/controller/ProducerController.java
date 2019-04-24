package com.sd.controller;

import com.sd.entity.Category;
import com.sd.entity.Producer;
import com.sd.entity.Product;
import com.sd.service.ProducerService;
import com.sd.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@Controller
@RequestMapping("/producer")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @GetMapping("get-all")
    public String list(Model model) {
        Pageable pageable = PageRequest.of(0, 10);
        model.addAttribute("producers", producerService.findAll(pageable));
        return "back/producer";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        producerService.delete(id);
        return "redirect:/producer/get-all";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("producer", new Producer());
        return "back/producer_edit";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Pageable pageable = PageRequest.of(0, 10);
        Optional<Producer> opProducer = producerService.findOne(id);
        model.addAttribute("producer", opProducer);
        return "back/producer_edit";
    }

    @PostMapping("/save")
    public String save(Producer producer, BindingResult result, RedirectAttributes redirect,
                       Model model) {
        producerService.save(producer);
        return "redirect:/producer/get-all";
    }
}
