package com.jpm.restExample.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    @JsonProperty("provider")
    private String provider;
    @JsonProperty("isValid")
    private boolean isValid;

    public Result(String provider, boolean isValid) {
        this.provider = provider;
        this.isValid = isValid;
    }
}
