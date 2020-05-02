package com.ram.forex.response.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponse {
    @JsonProperty(value = "INR_USD")
    private String inrUsd;

}
