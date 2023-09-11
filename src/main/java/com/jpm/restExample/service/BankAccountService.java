package com.jpm.restExample.service;

import com.jpm.restExample.client.DataProviderClient;
import com.jpm.restExample.config.BankServiceConfig;
import com.jpm.restExample.model.DataProvider;
import com.jpm.restExample.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {


    @Autowired
    private BankServiceConfig config;


    DataProviderClient dataProviderClient;


    public List<DataProvider> getDefaultDataProviders() {
        return config.getDataProviders();
    }

    public List<DataProvider> mapProviderNamesToConfigs(List<String> providerNames) {
        List<DataProvider> providerConfigs = new ArrayList<>();

        for (String providerName : providerNames) {
            DataProvider providerConfig = getProviderConfigByName(providerName);
            if (providerConfig != null) {
                providerConfigs.add(providerConfig);
            }
        }

        return providerConfigs;
    }

    private DataProvider getProviderConfigByName(String providerName) {
        for (DataProvider providerConfig : config.getDataProviders()) {
            if (providerConfig.getName().equalsIgnoreCase(providerName)) {
                return providerConfig;
            }
        }
        return null; // Provider not found
    }

    public List<Result> validateAccount(String accountNumber, List<DataProvider> providers) {
        // Implement your validation logic here
        // Query data providers based on the 'providers' list
        // Return a BankAccountValidationResult object
        List<Result> validationResults = null;

        for (DataProvider provider : providers) {
            String providerUrl = provider.getUrl();
            String providerName = provider.getName();

            if (providerUrl != null) {
                 boolean validationResult = dataProviderClient.makeRequestToProvider(providerUrl, accountNumber);
                 Result result = new Result(providerName, validationResult);
                 validationResults.add(result);
            }
        }


        return validationResults;
    }
}
