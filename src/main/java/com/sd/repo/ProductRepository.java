package com.sd.repo;

import com.sd.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    String findByImage(String file);

    List<Product> findByNameContaining(String term);
}
