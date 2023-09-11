package com.jpm.restExample.controllers;

import com.jpm.restExample.model.DataProvider;
import com.jpm.restExample.model.Result;
import com.jpm.restExample.service.BankAccountService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankAccountController {


    private BankAccountService bankAccountService;

    @GetMapping(value = "/validate", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Result>> validateBankAccount(
            @RequestParam String accountNumber,
            @RequestParam(required = false) List<String> providers
    ) {
        List<DataProvider> dataProviderList = null;
        // If providers are not specified in the request, use the ones from configuration
        if (providers == null || providers.isEmpty()) {
            dataProviderList = bankAccountService.getDefaultDataProviders();
        }
        else{
            dataProviderList = bankAccountService.mapProviderNamesToConfigs(providers);
        }

        List<Result> resultList = bankAccountService.validateAccount(accountNumber, dataProviderList);

       return ResponseEntity.ok(resultList);

    }
}
