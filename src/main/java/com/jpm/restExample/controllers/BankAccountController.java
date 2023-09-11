package com.jpm.restExample.controllers;

import com.jpm.restExample.model.DataProvider;
import com.jpm.restExample.model.Result;
import com.jpm.restExample.model.UserRequest;
import com.jpm.restExample.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankAccountController {


    @Autowired
    private BankAccountService bankAccountService;
    @PostMapping(value = "/validate", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Result>> validateBankAccount(
            @RequestBody UserRequest userRequest
            ) {
        List<DataProvider> dataProviderList = null;
        List<String> requestProviders = userRequest.getProviders();
        String accountNumber = userRequest.getAccountNum();
        if(accountNumber.isEmpty()){
            System.out.println("Account number cannot be empty");
            return null;
        }

        // If providers are not specified in the request, use the ones from configuration
        if (requestProviders == null || requestProviders.isEmpty()) {
            dataProviderList = bankAccountService.getDefaultDataProviders();
        }
        else{
            dataProviderList = bankAccountService.mapProviderNamesToConfigs(requestProviders);
        }

        List<Result> resultList = bankAccountService.validateAccount(accountNumber, dataProviderList);

       return ResponseEntity.ok(resultList);

    }
}
