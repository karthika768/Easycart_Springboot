/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.configuration;

import com.example.EasyCart.security.AccessTokenProcessingFilter;
import com.example.EasyCart.security.AccessTokenAdminDetailsService;
import com.example.EasyCart.security.AccessTokenCustomerDetailService;
import com.example.EasyCart.security.config.SecurityConfig;
import com.example.EasyCart.security.util.TokenGenerator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

/**
 *
 * @author arun
 */
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled=true,proxyTargetClass = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public WebSecurityConfiguration() {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .requestMatcher(new NegatedRequestMatcher(new AntPathRequestMatcher("/error")))
                .addFilter(accessTokenProcessingFilter())
                .authenticationProvider(preAuthenticatedAuthenticationProvider())
                .exceptionHandling().and()
                .headers().and()
                .sessionManagement().sessionCreationPolicy(STATELESS).and()
                .securityContext().and()
                .anonymous().and()
                .authorizeRequests()
                .antMatchers(OPTIONS, "/admin").anonymous()
                .antMatchers(POST, "/admin").anonymous()
                .antMatchers(OPTIONS, "/login").anonymous()
                .antMatchers(POST, "/login").anonymous()
                .antMatchers(GET, "/activation").anonymous()
                .antMatchers(PUT, "/login").anonymous()
                .antMatchers(OPTIONS, "/**").anonymous()
                .antMatchers(OPTIONS, "/forgetPassword").anonymous()
                .antMatchers(POST, "/forgetPassword").anonymous()
                .antMatchers(OPTIONS, "/forgetPassword/verifyOtp").anonymous()
                .antMatchers(POST, "/forgetPassword/verifyOtp").anonymous()
                .antMatchers(OPTIONS, "/forgetPassword/changePassword").anonymous()
                .antMatchers(POST, "/forgetPassword/changePassword").anonymous()
                 .antMatchers(OPTIONS, "/customer/login").anonymous()
                .antMatchers(POST, "/customer/login").anonymous()
                .anyRequest().authenticated();

    }

    @Bean
    protected AccessTokenAdminDetailsService accessTokenAdminDetailsService() {
        return new AccessTokenAdminDetailsService();
    }

    @Bean
    protected PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider authProvider = new PreAuthenticatedAuthenticationProvider();
        authProvider.setPreAuthenticatedUserDetailsService(accessTokenAdminDetailsService());
        authProvider.setPreAuthenticatedUserDetailsService(accessTokenCustomerDetailService());
        return authProvider;
    }
    
    

    @Bean
    protected AccessTokenProcessingFilter accessTokenProcessingFilter() throws Exception {
        AccessTokenProcessingFilter filter = new AccessTokenProcessingFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    
    @Bean
    protected AccessTokenCustomerDetailService accessTokenCustomerDetailService() {
        return new AccessTokenCustomerDetailService();
    }

//    @Bean
//    protected PreAuthenticatedAuthenticationProvider CustomerPreAuthenticatedAuthenticationProvider() {
//        PreAuthenticatedAuthenticationProvider authProvider = new PreAuthenticatedAuthenticationProvider();
//        authProvider.setPreAuthenticatedUserDetailsService(accessTokenCustomerDetailService());
//        return authProvider;
//    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @ConfigurationProperties("app.security")
    public SecurityConfig securityConfig() {
        return new SecurityConfig();
    }

    @Bean
    @ConfigurationProperties("app.security.configuration")
    public TokenGenerator tokenGenerator(SecurityConfig securityConfig) {
        return new TokenGenerator(securityConfig.getTokenGeneratorPassword(), securityConfig.getTokenGeneratorSalt());
    }
}
