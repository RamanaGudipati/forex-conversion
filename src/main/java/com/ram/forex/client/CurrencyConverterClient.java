package com.ram.forex.client;

import com.ram.forex.exception.ExternalServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class CurrencyConverterClient {


    private RestTemplate restTemplate;

    @Value(value = "${currency.base.endpoint}")
    private String endPoint;

    @Value(value = "${spring.apiKey}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate() {
        this.restTemplate = new RestTemplate();
        return restTemplate;
    }

    public Map<String, Double> getCurrencyValueinUSD(String currencyCode){
        String url = String.format("%s/convert?q=%s"+"_USD"+
                        "&compact=ultra&apiKey=%s",endPoint, currencyCode, apiKey);
        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (RestClientException e) {
            throw new ExternalServiceException("Could not fetch currency", e);
        }
    }

    public Map<String, Double> getCurrencyValueByCurrencyCode(String currencyCode){
        String url = String.format("%s/convert?q=USD_"+"%s"+
                "&compact=ultra&apiKey=%s",endPoint, currencyCode, apiKey);
        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (RestClientException e) {
            throw new ExternalServiceException("Could not fetch currency", e);
        }
    }
}
