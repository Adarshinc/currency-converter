package com.currencyConverter;

import com.currencyConverter.dto.CurrencyDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CurrencyConverterService {

    @Autowired
    private RestCurrencyConverterService restCurrencyConverterService;

    /**
     * Method which accepts a source currency, destination currency and amount in source currency to be converted to the destination currency
     * @param currencyInput
     * @return - Converted amount in destination currency
     */
    public String convertCurrency(@NonNull String currencyInput) {

        String[] currencyConverterParams = currencyInput.split(":");
        if(currencyConverterParams.length != 3) {
            String errorMessage = "Invalid currency conversion input. Enter the input in the format" +
                    " 'SourceCurrencyCode:SourceCurrencyAmount:DestinationCurrencyCode' . ";
            log.error(errorMessage);

        }

        //check this - Adarsh, need to sanitise the source and destination currency code inputs.
        // Do this in a different method.

        String sourceCurrencyCode = currencyConverterParams[0];
        double conversionAmount = Double.parseDouble(currencyConverterParams[1]);
        String destinationCurrencyCode = currencyConverterParams[2];

        CurrencyDetails currencyDetails = restCurrencyConverterService.fetchExchangeRateForCurrency(sourceCurrencyCode);
        return fetchDestinationCurrencyAmount(currencyDetails, conversionAmount, destinationCurrencyCode);


    }

    /**
     * Method to do the actual conversion/ calculation of source currency into destination currency
     * @param currencyDetails
     * @param conversionAmount
     * @param destinationCurrencyCode
     * @return
     */
    private String fetchDestinationCurrencyAmount(@NonNull CurrencyDetails currencyDetails, double conversionAmount,
                                                  @NonNull String destinationCurrencyCode) {

        double amountInDestinationCurrencyCode = currencyDetails.getConversionRates().get(destinationCurrencyCode) * conversionAmount;
        return destinationCurrencyCode + amountInDestinationCurrencyCode;
    }
}
