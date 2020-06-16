package com.tealorange.dashboard.tmp.restrepository;

import com.tealorange.dashboard.tmp.model.Order;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long>{
    
}