package com.devrenno.oauth2ssincation02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain asFilterChain(HttpSecurity http) throws Exception {

        // Nova forma: obter o configurador e aplicar configurações padrão
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
                OAuth2AuthorizationServerConfigurer.authorizationServer();

        http.with(authorizationServerConfigurer, Customizer.withDefaults());

        // Configurar OIDC (se necessário)
        authorizationServerConfigurer.oidc(Customizer.withDefaults());

        // Exception handling
        http.exceptionHandling(e ->
                e.authenticationEntryPoint(
                        new LoginUrlAuthenticationEntryPoint("/login"))
        );

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{

        http.formLogin(Customizer.withDefaults());

        http.authorizeHttpRequests(c ->c.anyRequest().authenticated());

        return http.build();
    }


}
