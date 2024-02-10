package com.currencyConverter.repository;

import com.currencyConverter.model.SystemProperty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemPropertyRepository extends MongoRepository<SystemProperty, String>  {


}
