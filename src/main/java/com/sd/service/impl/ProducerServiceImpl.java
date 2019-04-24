package com.sd.service.impl;

import com.sd.entity.Producer;
import com.sd.entity.Product;
import com.sd.repo.ProducerRepository;
import com.sd.service.ProducerService;
import com.sd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private ProducerRepository repo;

    @Override
    public Producer save(Producer producer) {
        return repo.save(producer);
    }

    @Override
    public Page<Producer> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Optional<Producer> findOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
