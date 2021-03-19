package com.vitaapp.backend.tesis.web.security.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class BearerTokenAdminInterceptor implements HandlerInterceptor {
    private BearerTokenWrapper tokenWrapper;

    public BearerTokenAdminInterceptor(BearerTokenWrapper tokenWrapper) {
        this.tokenWrapper = tokenWrapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String authorizationHeaderValue = request.getHeader("Authorization");
        if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer")) {
            String token = authorizationHeaderValue.substring(7, authorizationHeaderValue.length());
            tokenWrapper.setToken(token);
        }

        return true;
    }

    public BearerTokenWrapper getTokenWrapper() {
        return tokenWrapper;
    }

    public void setTokenWrapper(BearerTokenWrapper tokenWrapper) {
        this.tokenWrapper = tokenWrapper;
    }
}
