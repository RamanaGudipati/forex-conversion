package com.ram.forex.service;


import com.ram.forex.client.CurrencyConverterClient;
import com.ram.forex.dto.Contract;
import com.ram.forex.entity.ContractDetail;
import com.ram.forex.repository.ContractRepository;
import com.ram.forex.repository.CurrencyRatesRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ContractServiceImpl implements ContractService {

    private static final String BASE_CURRENCY = "USD";

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CurrencyRatesRepository currencyRatesRepository;

    @Value("${spring.exchange.service}")
    @Setter
    private Boolean isExchangeApiEnabled;

    @Autowired
    private CurrencyConverterClient currencyConverterClient;

    @Autowired
    private ContractDetail contractDetail;

    public void saveContract(Contract contract) {
        if (isExchangeApiEnabled) {
            Map<String, Double> currencyMap = currencyConverterClient.getCurrencyValueinUSD(contract.getCurrency());
            contractDetail.setPrice(currencyMap.get(contract.getCurrency() + "_USD") * contract.getPrice());
            contractDetail.setCountry(contract.getCountry());
            contractDetail.setSourceCurrency(contract.getCurrency());
            contractDetail.setBaseCurrency(BASE_CURRENCY);
            contractDetail.setName(contract.getName());
        } else {
            contractDetail.setPrice(currencyRatesRepository.findByCurrencyCode(contract.getCurrency()).getCurrencyValue()
                    * contract.getPrice());
            contractDetail.setCountry(contract.getCountry());
            contractDetail.setSourceCurrency(contract.getCurrency());
            contractDetail.setBaseCurrency(BASE_CURRENCY);
            contractDetail.setName(contract.getName());
        }
        contractRepository.save(contractDetail);
    }

    public List<Contract> getContractsByCurrency(String currencyCode) {
        List<Contract> contractList = new ArrayList<Contract>();
        List<ContractDetail> contractDetailList = contractRepository.findBySourceCurrency(currencyCode);
        if (isExchangeApiEnabled) {
            Map<String, Double> currencyMap = currencyConverterClient.getCurrencyValueByCurrencyCode(currencyCode);
            contractDetailList.stream().forEach(contractDetail -> {
                Contract contract = Contract.builder()
                        .country(contractDetail.getCountry()).name(contractDetail.getName()).price(Math.ceil(contractDetail.getPrice() * currencyMap.get("USD_" + currencyCode)))
                        .currency(contractDetail.getSourceCurrency()).build();
                contractList.add(contract);
            });
        } else {
            Double usdToLocalValue = currencyRatesRepository.findByCurrencyCode(currencyCode).getCurrencyValue();
            contractDetailList.stream().forEach(contractDetail -> {
                Contract contract = Contract.builder()
                        .country(contractDetail.getCountry()).name(contractDetail.getName()).price(Math.ceil(contractDetail.getPrice() * usdToLocalValue))
                        .currency(contractDetail.getSourceCurrency()).build();
                contractList.add(contract);
            });
        }
        return contractList;
    }
}
