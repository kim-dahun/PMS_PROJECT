package com.prod.pms.config.Security;

import com.prod.pms.constants.ApiConstants;
import com.prod.pms.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import static com.prod.pms.constants.ApiConstants.API_URL;
import static com.prod.pms.constants.Role.*;

@Configuration
@RequiredArgsConstructor
public class SpringSecurityCustomConfig  {

    private final UserDetailsService userDetailsService;
    private final JwtTokenUtils jwtTokenUtils;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(API_URL+"/user/login").permitAll()
                .requestMatchers(API_URL+"/user/create").permitAll()
                .requestMatchers(API_URL+"/user/auth/**").authenticated()
                .requestMatchers(API_URL+"/user/menu/**").authenticated()
                .requestMatchers(API_URL+"/menu/**").authenticated()
                .requestMatchers(API_URL+"/admin/**").authenticated()
                .anyRequest().authenticated())
                .addFilterBefore(new JwtCustomFilter(jwtTokenUtils,userDetailsService), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }


    // AuthenticationManager 빈 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
