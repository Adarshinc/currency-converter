package com.currencyConverter.repository;

import com.currencyConverter.model.SystemProperty;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemPropertyRepository extends MongoRepository<SystemProperty, String>  {

    Optional<SystemProperty> findByName(@NonNull String name);

}
