package com.tealorange.dashboard.model.configuration;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;


public class DashboardConfigurationConverter implements AttributeConverter<DashboardConfiguration, String> {

    @Override
    public String convertToDatabaseColumn(DashboardConfiguration aConfiguration) {
        if (aConfiguration == null) {
            return null;
        }
        try {
            return new ObjectMapper().writeValueAsString(aConfiguration);
        } catch (JsonProcessingException e) {
            // Log error
            return null;
        }
    }

    @Override
    public DashboardConfiguration convertToEntityAttribute(String aStringData) {
        if (StringUtils.isBlank(aStringData)) {
            return null;
        }
        try {
            return new ObjectMapper().readValue(aStringData, DashboardConfiguration.class);
        } catch (JsonProcessingException e) {
            // Log error
            return null;
        }
    }

}
