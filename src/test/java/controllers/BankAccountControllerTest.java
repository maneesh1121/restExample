package controllers;

import com.jpm.restExample.controllers.BankAccountController;
import com.jpm.restExample.model.DataProvider;
import com.jpm.restExample.model.UserRequest;
import com.jpm.restExample.model.UserResponse;
import com.jpm.restExample.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BankAccountControllerTest {

    @InjectMocks
    private BankAccountController bankAccountController;

    @Mock
    private BankAccountService bankAccountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidateBankAccountWithValidInput() {

        UserRequest userRequest = new UserRequest();
        userRequest.setAccountNum("12345678");
        List<DataProvider> dataProviders = new ArrayList<>();


        when(bankAccountService.getDefaultDataProviders()).thenReturn(dataProviders);

        ResponseEntity<UserResponse> responseEntity = bankAccountController.validateBankAccount(userRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

    }

    @Test
    public void testValidateBankAccountWithEmptyAccountNumber() {

        UserRequest userRequest = new UserRequest();
        userRequest.setAccountNum(""); // Empty account number

        ResponseEntity<UserResponse> responseEntity = bankAccountController.validateBankAccount(userRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());

    }


}
