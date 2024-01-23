package com.currencyConverter.service;

import com.currencyConverter.dto.CurrencyDetails;
import com.currencyConverter.model.CurrencyConversion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Slf4j
public class CurrencyExchangeProcessingService {

    @Autowired
    private RestCurrencyConverterService restCurrencyConverterService;

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    /**
     * Method which accepts a source currency, destination currency and amount in source currency to be converted to the destination currency
     * @param currencyInput
     * @return - Converted amount in destination currency
     */
    public String processCurrencyConversion(@NonNull String currencyInput) {

        String[] currencyConverterParams = currencyInput.split(":");
        if(currencyConverterParams.length != 3) {
            String errorMessage = "Invalid currency conversion input. Enter the input in the format" +
                    " 'SourceCurrencyCode:SourceCurrencyAmount:DestinationCurrencyCode' . ";
            log.error(errorMessage);
            return errorMessage;
        }

        //check this - Adarsh, need to sanitise the source and destination currency code inputs.
        // Do this in a different method.

        String sourceCurrencyCode = currencyConverterParams[0];
        double conversionAmount = Double.parseDouble(currencyConverterParams[1]);
        String destinationCurrencyCode = currencyConverterParams[2];

        // fetch currency conversion details of source currency which has been updated in < 24 hours from now
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        LocalDateTime oneDayBackDateTime = LocalDateTime.now().minusDays(1);
        Instant instant = oneDayBackDateTime.atZone(istZone).toInstant();
        long timestampOneDayBack = instant.toEpochMilli();

        CurrencyConversion currencyConversion = currencyExchangeService.fetchConversionRatesForSourceCurrency(sourceCurrencyCode, timestampOneDayBack);
        if(currencyConversion == null) {
            CurrencyDetails currencyDetails = restCurrencyConverterService.fetchExchangeRateForCurrency(sourceCurrencyCode);
            currencyConversion = currencyExchangeService.updateCurrencyExchangeDetails(currencyDetails);
        }

        // do a validation of the destination currency code as well
        if(currencyConversion == null || currencyConversion.getConversionRates().get(destinationCurrencyCode) == null) {
            String errorMessage = String.format("Currency conversion map does not contain destination currency code key: %s . Please enter valid destination currency code.", destinationCurrencyCode);
            log.error(errorMessage);
            return errorMessage;
        }

        return fetchDestinationCurrencyAmount(currencyConversion, conversionAmount, destinationCurrencyCode);


    }

    /**
     * Method to do the actual conversion/ calculation of source currency into destination currency
     * @param conversionAmount
     * @param destinationCurrencyCode
     * @return
     */
    private String fetchDestinationCurrencyAmount(@NonNull CurrencyConversion currencyConversion, double conversionAmount,
                                                  @NonNull String destinationCurrencyCode) {

        double amountInDestinationCurrencyCode = currencyConversion.getConversionRates().get(destinationCurrencyCode) * conversionAmount;
        return destinationCurrencyCode + " " + amountInDestinationCurrencyCode;
    }
}
