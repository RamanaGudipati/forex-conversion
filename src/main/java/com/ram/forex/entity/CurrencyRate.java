package com.ram.forex.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection="currency_rates")
public class CurrencyRate {
    private String id;
    private String currencyCode;
    private Double currencyValue;

}
