package com.vitaapp.backend.tesis.web.security.interceptor;

public class BearerTokenWrapper {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
