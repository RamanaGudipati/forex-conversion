package com.ram.forex.service;

import com.ram.forex.client.CurrencyConverterClient;
import com.ram.forex.dto.Contract;
import com.ram.forex.entity.ContractDetail;
import com.ram.forex.repository.ContractRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContractServiceImplTest {

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private CurrencyConverterClient currencyConverterClient;

    @InjectMocks
    private ContractServiceImpl contractService;

    @Test
    public void saveContract() {
    }

    @Test
    public void getContractsByCurrency() {
        Map<String, Double> currencyMap = new HashMap<>();
        currencyMap.put("USD_AED", 3.67305);
        ContractDetail contractDetailOne = ContractDetail.builder().name("AED Monthly Contract").country("UAE")
                .price(1361.265).sourceCurrency("AED").build();
        ContractDetail contractDetailTwo = ContractDetail.builder().name("AED Monthly Contract").country("UAE")
                .price(1415.7156).sourceCurrency("AED").build();
        ContractDetail contractDetailThree = ContractDetail.builder().name("AED Monthly Contract").country("UAE")
                .price(19084.0).sourceCurrency("AED").build();

        contractService.setIsExchangeApiEnabled(true);
        when(contractRepository.findBySourceCurrency(anyString())).thenReturn(Arrays.asList(contractDetailOne, contractDetailTwo, contractDetailThree));
        when(currencyConverterClient.getCurrencyValueByCurrencyCode(anyString())).thenReturn(currencyMap);
        List<Contract> contractDetailRequestList = contractService.getContractsByCurrency("AED");
        assertEquals(4999.994408250001, contractDetailRequestList.get(0).getPrice(), 0.00);
        assertEquals(5199.9941845799995, contractDetailRequestList.get(1).getPrice(), 0.00);
        assertEquals(70096.4862, contractDetailRequestList.get(2).getPrice(), 0.0);
    }
}