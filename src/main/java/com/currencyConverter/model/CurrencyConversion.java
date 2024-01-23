package com.currencyConverter.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document("currency_conversion")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversion {

    @Id
    private String sourceCurrency;

    private Map<String, Double> conversionRates;

    private long lastUpdatedAt;

}
