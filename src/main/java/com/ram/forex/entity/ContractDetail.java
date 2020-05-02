package com.ram.forex.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection="contracts")
public class ContractDetail {
    @Id
    private String id;
    private String name;
    private String country;
    private Double price;
    private String sourceCurrency;
    private String baseCurrency;

}
