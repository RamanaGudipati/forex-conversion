package com.ram.forex.config;

import com.ram.forex.entity.ContractDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ContractDetail contractDetail(){
        return new ContractDetail();
    }
}
