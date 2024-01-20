package com.currencyConverter;

import com.currencyConverter.dto.CurrencyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestCurrencyConverterService {

    @Autowired
    private RestTemplate restTemplate;

    public RestCurrencyConverterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String baseUrl = ""; // this contains api secret as well, should not be exposed;

    public CurrencyDetails fetchExchangeRateForCurrency(@NonNull String sourceCurrencyCode) {

        String apiUrl = baseUrl + sourceCurrencyCode;
        return restTemplate.getForObject(apiUrl, CurrencyDetails.class);
        // check this - Adarsh, need to handle the correct http status codes, you can use restTemplate.exchange to get entire ResponseEntity object itself.
    }
}
