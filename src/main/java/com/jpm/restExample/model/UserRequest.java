package com.jpm.restExample.model;

import java.util.List;

public class UserRequest {

    private String accountNum;
    private List<String> providers;

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public List<String> getProviders() {
        return providers;
    }

    public void setProviders(List<String> providers) {
        this.providers = providers;
    }
}
