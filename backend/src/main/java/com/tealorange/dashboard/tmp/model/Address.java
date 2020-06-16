package com.tealorange.dashboard.tmp.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {
    private String client;

    private String streetName;

    private String streetNumber;

    private String zipCode;

    private String countryCode;
}