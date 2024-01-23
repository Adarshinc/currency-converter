package com.currencyConverter.repository;

import com.currencyConverter.model.CurrencyConversion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyConversionRepository extends MongoRepository<CurrencyConversion, String> {

    @Query("{'sourceCurrency' : ?0, 'lastUpdatedAt': { $gt: ?1 } }")
    CurrencyConversion findBySourceCurrencyAndLastUpdatedAtGreaterThan(String sourceCurrency, long lastUpdatedAt);

}
