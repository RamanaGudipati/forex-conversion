package com.ram.forex.repository;

import com.ram.forex.entity.ContractDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContractRepository extends MongoRepository<ContractDetail, String> {

    public List<ContractDetail> findBySourceCurrency(String sourceCurrency);
}
