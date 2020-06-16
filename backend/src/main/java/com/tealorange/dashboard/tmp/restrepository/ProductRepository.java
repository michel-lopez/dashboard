package com.tealorange.dashboard.tmp.restrepository;

import com.tealorange.dashboard.tmp.model.Product;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    
}