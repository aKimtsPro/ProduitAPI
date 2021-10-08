package com.example.produitapi.config;

import com.example.produitapi.config.jwt.JwtAuthFilter;
import com.example.produitapi.config.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        jsr250Enabled = true,
        securedEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider tokenProvider;

    public WebSecurityConfig(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // desactivation de la gestion de CSRF
        http.csrf()
                .disable();

        // configuration des filtres
        http.addFilterBefore(new JwtAuthFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        // configuration de session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // securisation des points d'entrée
        http.authorizeRequests()
                // Test Security
                .antMatchers("/test/admin").hasAuthority("ADMIN")
                .antMatchers("/test/user").hasAuthority("USER")
                .antMatchers("/test/auth").authenticated()
//                // Produit
//                .antMatchers(HttpMethod.POST,"/produit/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/produit/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/produit/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.GET,"/produit/**").authenticated()
//                // Commande
//                .antMatchers(HttpMethod.POST, "/commande/**").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().permitAll();

        // pour h2
        http.headers()
                .frameOptions()
                .disable();

        http.httpBasic();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
