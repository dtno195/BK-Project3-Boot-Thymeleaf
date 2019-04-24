package com.sd.service.impl;

import com.sd.entity.Product;
import com.sd.repo.ProductRepository;
import com.sd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findOne(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> search(String term) {
        return Optional.empty();
    }
}
