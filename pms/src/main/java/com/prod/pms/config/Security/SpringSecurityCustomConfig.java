package com.prod.pms.config.Security;

import com.prod.pms.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                .requestMatchers("/user/login").permitAll()
                .requestMatchers("/user/create").permitAll()
                .requestMatchers("/user/auth/**").authenticated()
                .requestMatchers("/user/menu/**").authenticated()
                .requestMatchers("/menu/**").hasAnyRole(SUPER_ADMIN.name(),ADMIN.name(),LEADER.name(),PART_LEADER.name(),USER.name())
                .requestMatchers("/admin/**").hasAnyRole(SUPER_ADMIN.name(), ADMIN.name())
                .anyRequest().permitAll())
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
