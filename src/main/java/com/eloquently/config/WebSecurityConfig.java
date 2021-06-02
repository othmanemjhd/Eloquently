package com.eloquently.config;

import com.eloquently.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(authenticationManager());
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider(){
        return new JwtTokenProvider();
    }
    @Bean
    public WebClient webClient(){
        return WebClient.create();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)     //no cookies
                .and()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/profile/**").hasRole("USER")
                .antMatchers("/*/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(jwtAuthorizationFilter());
    }
}
