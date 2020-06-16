package com.tealorange.dashboard.restrepository;

import com.tealorange.dashboard.model.Dashboard;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DashboardRepository extends PagingAndSortingRepository<Dashboard, Long> {
}