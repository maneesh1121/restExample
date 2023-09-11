package service;

import com.jpm.restExample.client.DataProviderClient;
import com.jpm.restExample.config.BankServiceConfig;
import com.jpm.restExample.model.DataProvider;
import com.jpm.restExample.model.Result;
import com.jpm.restExample.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BankAccountServiceTest {

    @InjectMocks
    private BankAccountService bankAccountService;

    @Mock
    private BankServiceConfig config;

    @Mock
    private DataProviderClient dataProviderClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDefaultDataProviders() {

        List<DataProvider> dataProviders = new ArrayList<>();

        when(config.getDataProviders()).thenReturn(dataProviders);

        List<DataProvider> result = bankAccountService.getDefaultDataProviders();

        assertEquals(dataProviders, result);
    }

    @Test
    public void testMapProviderNamesToConfigs() {

        List<String> providerNames = new ArrayList<>();
        providerNames.add("provider1");
        providerNames.add("provider2");

        List<DataProvider> dataProviders = new ArrayList<>();
        dataProviders.add(new DataProvider("provider1", "https://provider1.com/v1/api/account/validate"));
        dataProviders.add(new DataProvider("provider2", "https://provider2.com/v1/api/account/validate"));
        when(config.getDataProviders()).thenReturn(dataProviders);

        List<DataProvider> result = bankAccountService.mapProviderNamesToConfigs(providerNames);

        assertEquals("provider1", result.get(0).getName());
    }

    @Test
    public void testValidateAccount() {
        // Prepare mock data
        String accountNumber = "12345678";
        List<DataProvider> providers = new ArrayList<>();
        providers.add(new DataProvider("provider1", "https://provider1.com/v1/api/account/validate"));
        providers.add(new DataProvider("provider2", "https://provider2.com/v1/api/account/validate"));


        when(dataProviderClient.makeRequestToProvider(anyString(), anyString())).thenReturn(true);

        List<Result> result = bankAccountService.validateAccount(accountNumber, providers);

        assertTrue(result.get(0).isValid());
    }

    // Add more test cases for different scenarios as needed
}
