package com.codigojava.biblioteca.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfiguration {
    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    public SecurityEntryPoint securityEntryPoint;

    @Autowired
    private RoleHierarchy roleHierarchy;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    static RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withDefaultRolePrefix()
                .role("ADMIN").implies("SUPPORT")
                .role("SUPPORT").implies("USER")
                .build();
    }

    // Helper reutilizable
    private AuthorityAuthorizationManager<RequestAuthorizationContext> withRole(String role) {
        var manager = AuthorityAuthorizationManager.<RequestAuthorizationContext>hasRole(role);
        manager.setRoleHierarchy(roleHierarchy);
        return manager;
    }

    @Bean
    @Order(0)
    public SecurityFilterChain authSecurityFilterChain(HttpSecurity http) throws Exception {

        applyCommonConfig(http);

        return http
                .securityMatcher("/auth/**")
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    req.requestMatchers("/auth/**").permitAll();
                })
                .build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {

        applyCommonConfig(http);

        return http
                .securityMatcher("/users/**")
                .authorizeHttpRequests(req->{
                    req.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    req.requestMatchers(HttpMethod.PUT,"/users/password", "/users/password/**").permitAll();
                    req.requestMatchers(HttpMethod.GET,"/users/id/**").access(withRole("USER"));;
                    req.requestMatchers(HttpMethod.DELETE, "/users/drop/**").access(withRole("USER"));
                    req.requestMatchers(HttpMethod.POST,"/users").access(withRole("ADMIN"));
                    req.requestMatchers(HttpMethod.GET, "/users","/users/name/**").access(withRole("SUPPORT"));
                    req.requestMatchers(HttpMethod.PUT,"/users/id/**").access(withRole("SUPPORT"));
                    req.requestMatchers(HttpMethod.DELETE, "/users/id/**").access(withRole("ADMIN"));
                    req.anyRequest().authenticated();
                })
                .build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain loansSecurityFilterChain(HttpSecurity http) throws Exception {

        applyCommonConfig(http);

        return http
                .securityMatcher("/loans/**")
                .authorizeHttpRequests(req->{
                    req.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/loans/me").access(withRole("USER"));
                    req.requestMatchers(HttpMethod.GET,"/loans/user/**", "/loans/isbn/**").access(withRole("SUPPORT"));
                    req.requestMatchers(HttpMethod.GET,"/loans" ,"/loans/id/**").access(withRole("USER"));
                    req.requestMatchers(HttpMethod.POST,"/loans" ).access(withRole("USER"));
                    req.requestMatchers(HttpMethod.PUT,"/loans/id/**").access(withRole("SUPPORT"));
                    req.requestMatchers(HttpMethod.DELETE,"/loans/id/**").access(withRole("SUPPORT"));
                    req.anyRequest().denyAll();
                })
                .build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain historiesSecurityFilterChain(HttpSecurity http) throws Exception {

        applyCommonConfig(http);

        return http
                .securityMatcher("/histories/**")
                .authorizeHttpRequests(req->{
                    req.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    req.requestMatchers(HttpMethod.GET,"/histories","/histories/id/**","/histories/loan/**").access(withRole("USER"));
                    req.requestMatchers(HttpMethod.POST,"/histories" ).access(withRole("SUPPORT"));
                    req.requestMatchers(HttpMethod.PUT,"/histories/id/**").access(withRole("SUPPORT"));
                    req.requestMatchers(HttpMethod.DELETE,"/histories/id/**").access(withRole("SUPPORT"));
                    req.anyRequest().denyAll();
                })
                .build();
    }

    @Bean
    @Order(4)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        applyCommonConfig(http);

        return http
                .securityMatcher("/books/**","/authors/**", "/publishers/**", "/categories/**")
                .authorizeHttpRequests(req->{
                    req.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    req.requestMatchers(HttpMethod.GET,"/books/public/**").permitAll();
                    req.requestMatchers(HttpMethod.GET).authenticated();
                    req.requestMatchers(HttpMethod.POST).access(withRole("SUPPORT"));
                    req.requestMatchers(HttpMethod.PUT).access(withRole("SUPPORT"));
                    req.requestMatchers(HttpMethod.DELETE).access(withRole("ADMIN"));
                    req.anyRequest().denyAll();
                })
                .build();
    }



    // Configuración común extraída aquí para no repetir
    private void applyCommonConfig(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {}) // ← ACTIVAR CORS
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> {
                        ex.authenticationEntryPoint(securityEntryPoint);
                        ex.accessDeniedHandler(customAccessDeniedHandler);
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
