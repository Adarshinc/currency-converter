package com.currencyConverter.controller;

import com.currencyConverter.service.CurrencyExchangeProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConverterController {

    @Autowired
    private CurrencyExchangeProcessingService currencyConverterService;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/convert/currency")
    public String convertCurrency(@RequestParam @NonNull String currencyInput) {
        return currencyConverterService.processCurrencyConversion(currencyInput);
    }



}