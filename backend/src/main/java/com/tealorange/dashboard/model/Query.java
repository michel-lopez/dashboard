package com.tealorange.dashboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Query {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String name;

    private String sql;

    public Query(String aName) {
        this.name = aName;
    }
}