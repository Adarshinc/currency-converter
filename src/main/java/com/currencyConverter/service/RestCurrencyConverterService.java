package com.currencyConverter.service;

import com.currencyConverter.dto.CurrencyDetails;
import com.currencyConverter.model.SystemProperty;
import com.currencyConverter.repository.SystemPropertyRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RestCurrencyConverterService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SystemPropertyRepository systemPropertyRepository;

    String exchangeRateApiBaseUrl = "https://v6.exchangerate-api.com/v6/{key}/latest/";

    String exchangeRateApiKey;

    @PostConstruct
    public void init() {

        Optional<SystemProperty> systemProperty = systemPropertyRepository.findById("exchange.rate.api.key");
        systemProperty.ifPresent(property -> exchangeRateApiKey = property.getValue());
    }

    public RestCurrencyConverterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CurrencyDetails fetchExchangeRateForCurrency(@NonNull String sourceCurrencyCode) {

        if(!StringUtils.hasLength(exchangeRateApiKey)) {
            String errorMessage = String.format("Exchange rate api key not found for currency exchange");
            throw new RuntimeException(errorMessage);
        }

        String apiUrl = exchangeRateApiBaseUrl.replace("{key}",exchangeRateApiKey);
        return restTemplate.getForObject(apiUrl, CurrencyDetails.class);
        // check this - Adarsh, need to handle the correct http status codes, you can use restTemplate.exchange to get entire ResponseEntity object itself.
    }
}
