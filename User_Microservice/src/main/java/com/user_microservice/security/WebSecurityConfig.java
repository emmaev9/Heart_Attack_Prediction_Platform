package com.user_microservice.security;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig{
    private final UserDetailsService userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http     .cors(cors -> cors
                            .configurationSource(request -> {
                                CorsConfiguration config = new CorsConfiguration();
                                config.setAllowedOrigins(List.of("http://localhost:8083"));
                                config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                                config.setAllowedHeaders(List.of("*"));
                                config.setAllowCredentials(true);
                                return config;
                            })
                    )
                    .csrf(AbstractHttpConfigurer::disable)
                    .exceptionHandling(exceptionHandling ->
                            exceptionHandling.authenticationEntryPoint(unauthorizedHandler))
                    .sessionManagement(sessionManagement ->
                            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(authorize -> authorize
                            // .requestMatchers(HttpMethod.DELETE, "/person/**").hasAuthority("ADMIN")
                            // .requestMatchers("/admin/**").hasAuthority("ADMIN")
                            // .requestMatchers(HttpMethod.PUT, "/person/**").hasAuthority("ADMIN")
                            // .requestMatchers(HttpMethod.POST, "/person/**").hasAuthority("ADMIN")
                            //.requestMatchers(HttpMethod.GET, "/person/**").hasAuthority("ADMIN")
                            //.requestMatchers(HttpMethod.GET, "/admin/**").hasAuthority("ADMIN")
                           // .requestMatchers(HttpMethod.POST, "/admin/**").hasAuthority("ADMIN")
                            //.requestMatchers(HttpMethod.GET, "/admin/**").hasAuthority("ADMIN")
                            //.requestMatchers(HttpMethod.DELETE, "/admin/**").hasAuthority("ADMIN")
                            //.requestMatchers(HttpMethod.PUT, "/admin/**").hasAuthority("ADMIN")
                            //  .requestMatchers("/admin/**").hasAuthority("ADMIN")
                            //.antMatchers("/ClientHome").hasAuthority("USER")
                           // .anyRequest().authenticated())
                            .anyRequest().permitAll())
                    .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);




        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
}
