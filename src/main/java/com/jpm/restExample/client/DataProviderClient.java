package com.jpm.restExample.client;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class DataProviderClient {

    @Autowired
    private RestTemplate restTemplate;


    public boolean makeRequestToProvider(String providerUrl, String accountNumber) {
        boolean isValid = false;
        try {
            // Make a GET request to the provider's URL
            JSONPObject jsonpObject = restTemplate.getForObject(providerUrl, JSONPObject.class, accountNumber);
            isValid = (boolean) jsonpObject.getValue();

        } catch (Exception e) {
            // Handle exceptions or errors as needed
        }

        return isValid;
    }
}
