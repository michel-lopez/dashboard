package com.tealorange.dashboard.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.tealorange.dashboard.model.configuration.DashboardConfiguration;
import com.tealorange.dashboard.model.configuration.DashboardConfigurationConverter;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Dashboard {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String name;

    public Dashboard(String aName) {
        this.name = aName;
    }

    @Convert(converter = DashboardConfigurationConverter.class)
    private DashboardConfiguration configuration = new DashboardConfiguration();

    @Cascade(CascadeType.ALL)
    @ManyToMany
    @JoinTable(
        name = "dashboard_query", 
        joinColumns = @JoinColumn(name = "dashboard_id"), 
        inverseJoinColumns = @JoinColumn(name = "query_id"))
    private List<Query> queries = new ArrayList<>();
}