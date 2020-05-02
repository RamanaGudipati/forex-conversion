package com.ram.forex.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRate {
    private String currency;
    private Double exchangeRate;
}
