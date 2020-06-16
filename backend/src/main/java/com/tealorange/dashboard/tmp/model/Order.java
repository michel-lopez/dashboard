package com.tealorange.dashboard.tmp.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "test_order")
public class Order {
    
    @Id
    @GeneratedValue
    private long id;
    
    @Embedded
    private Address address;

    @ManyToOne
    private Product product;
}