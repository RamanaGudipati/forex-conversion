package com.ram.forex.controller;

import com.google.gson.Gson;
import com.ram.forex.dto.Contract;
import com.ram.forex.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
public class ForexController {

    @Autowired
    private ContractService contractService;

    @GetMapping(value = "/contracts/{currencyCode}")
    public ResponseEntity<?> fetchContracts(@PathVariable String currencyCode){
        List<Contract> contractList = contractService.getContractsByCurrency(currencyCode);
        return new ResponseEntity<>(contractList , OK);
    }

    @PostMapping(value = "/contracts/saveContract")
    public ResponseEntity<?> saveContracts(@RequestBody Contract contractDetail){
        String requestPayload = new Gson().toJson(contractDetail, Contract.class);
        log.info("save contract details paload {}", requestPayload);
        contractService.saveContract(contractDetail);
        return new ResponseEntity<>("contract details saved sucessful" , OK);
    }
}