package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.khazrak.jdocker.abstraction.AuthConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthConfig129 implements AuthConfig {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

}
