package com.sd.service.impl;

import com.sd.entity.Category;
import com.sd.repo.CategoryRepository;
import com.sd.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repo;

    @Override
    public void save(Category category) {
        repo.save(category);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Optional<Category> findOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
