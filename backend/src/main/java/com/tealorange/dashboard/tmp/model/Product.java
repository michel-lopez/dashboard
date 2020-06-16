package com.tealorange.dashboard.tmp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue
    private long id;

    private String name;

    public Product(String aName) {
        this.name = aName;
    }
}