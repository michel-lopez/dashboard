package com.tealorange.dashboard.model.configuration;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DashboardConfiguration {

    private List<DashboardColumn> columns = new ArrayList<>();

    private String baseSqlQuery;
}
