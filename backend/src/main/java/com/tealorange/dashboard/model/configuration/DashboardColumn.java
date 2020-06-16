package com.tealorange.dashboard.model.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardColumn {

    private String name;

    private String sql;

}
