package com.jpm.restExample.config;

import com.jpm.restExample.model.DataProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "bank-service")
public class BankServiceConfig {


    private List<DataProvider> dataProviders;

    public List<DataProvider> getDataProviders() {
        return dataProviders;
    }

    public void setDataProviders(List<DataProvider> dataProviders) {
        this.dataProviders = dataProviders;
    }
}