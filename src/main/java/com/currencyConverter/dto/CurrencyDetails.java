package com.currencyConverter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class CurrencyDetails {

    @JsonProperty("result")
    private String result;

    @JsonProperty("base_code")
    private String baseCode;

    @JsonProperty("conversion_rates")
    private HashMap<String, Double> conversionRates;

}
