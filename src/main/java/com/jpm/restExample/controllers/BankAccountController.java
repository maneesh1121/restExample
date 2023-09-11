package com.jpm.restExample.controllers;

import com.jpm.restExample.model.DataProvider;
import com.jpm.restExample.model.Result;
import com.jpm.restExample.model.UserRequest;
import com.jpm.restExample.model.UserResponse;
import com.jpm.restExample.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<UserResponse> validateBankAccount(
            @RequestBody UserRequest userRequest
            ) {
        UserResponse userResponse = new UserResponse();
        List<DataProvider> dataProviderList = null;
        //extract fields from user payload
        List<String> requestProviders = userRequest.getProviders();
        String accountNumber = userRequest.getAccountNum();
        if(accountNumber.isEmpty()){
            //need better error handling
            //Use logger class
            System.out.println("Account number cannot be empty");
            //should include some message in response too
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // If providers are not specified in the request, use the ones from configuration
        if (requestProviders == null || requestProviders.isEmpty()) {
            dataProviderList = bankAccountService.getDefaultDataProviders();
        }
        else{
            dataProviderList = bankAccountService.mapProviderNamesToConfigs(requestProviders);
        }

        List<Result> resultList = bankAccountService.validateAccount(accountNumber, dataProviderList);
        userResponse.setResultList(resultList);

       return ResponseEntity.ok(userResponse);

    }
}
