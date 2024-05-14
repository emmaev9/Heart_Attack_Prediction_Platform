package com.user_microservice.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        if (response == null) {
            System.out.println("Response object is null!");
            new Exception().printStackTrace();  // This will print the stack trace to help identify where the null is coming from
        } else {
            System.out.println("Unauthorized error: " + authException.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED); //401
        }
    }
}
