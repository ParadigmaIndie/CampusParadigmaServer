package com.germany.paradigmaindie.ParadigmaIndieServer.config;

import com.germany.paradigmaindie.ParadigmaIndieServer.jwt.JwtConfig;
import com.germany.paradigmaindie.ParadigmaIndieServer.jwt.JwtTokenVerifier;
import com.germany.paradigmaindie.ParadigmaIndieServer.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.germany.paradigmaindie.ParadigmaIndieServer.services.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.SecretKey;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SegurityConfig extends WebSecurityConfigurerAdapter {

    private final AppUserDetailsService applicationUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    @Autowired
    public SegurityConfig(AppUserDetailsService applicationUserService, PasswordEncoder passwordEncoder, JwtConfig jwtConfig, SecretKey secretKey) {
        this.applicationUserService = applicationUserService;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http    .cors().and()
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //TODO Be CareFull WITH CORS IN PRODUCTION
                .addFilterBefore(new CrosFilter(),JwtUsernameAndPasswordAuthenticationFilter.class)
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig,  secretKey))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey),JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/","index", "/css/*", "/js/*",
                        "/h2-console/**",
                        "/register",

                        //
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/login",
                        //
                        "/api/v1/registration/**",
                        "/api/v1/registration").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/courses").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/videos/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/videos/singel/**").permitAll()
                .anyRequest()
                .authenticated();



                http.headers().frameOptions().disable();
    }

    // Used by spring security if CORS is enabled.
    /*@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

}
