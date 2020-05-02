package com.ram.forex.repository;

import com.ram.forex.entity.CurrencyRate;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CurrencyRatesRepository extends MongoRepository<CurrencyRate, String> {
    public CurrencyRate findByCurrencyCode(String currencyCode);
}