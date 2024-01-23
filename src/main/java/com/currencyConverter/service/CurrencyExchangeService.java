package com.currencyConverter.service;

import com.currencyConverter.dto.CurrencyDetails;
import com.currencyConverter.model.CurrencyConversion;
import com.currencyConverter.repository.CurrencyConversionRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class CurrencyExchangeService {

    @Autowired
    private CurrencyConversionRepository currencyConversionRepository;

    public CurrencyConversion fetchConversionRatesForSourceCurrency(@NonNull String sourceCurrency, long updatedAt) {
        return currencyConversionRepository.findBySourceCurrencyAndLastUpdatedAtGreaterThan(sourceCurrency, updatedAt);
    }


    public CurrencyConversion updateCurrencyExchangeDetails(CurrencyDetails currencyDetails) {
        CurrencyConversion currencyConversion = new CurrencyConversion();
        currencyConversion.setSourceCurrency(currencyDetails.getBaseCode());
        currencyConversion.setConversionRates(currencyDetails.getConversionRates());
        // check this - Adarsh, update this
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        LocalDateTime currentTime = LocalDateTime.now();
        Instant instant = currentTime.atZone(istZone).toInstant();
        long timestampNow = instant.toEpochMilli();
        currencyConversion.setLastUpdatedAt(timestampNow);
        return currencyConversionRepository.save(currencyConversion);
    }
}

