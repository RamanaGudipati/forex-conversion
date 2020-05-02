package com.ram.forex.service;

import com.ram.forex.dto.Contract;

import java.util.List;

public interface ContractService {

    void saveContract(Contract contract);
    List<Contract> getContractsByCurrency(String currencyCode);
}
