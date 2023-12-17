package com.example.demorestapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((req) -> req.requestMatchers("/ui**").authenticated()
                .requestMatchers("/person","/person/**").permitAll()
                .requestMatchers("/controller","/controller/hello","/controller/**").hasRole("ADMIN")
                .anyRequest().authenticated())
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable);//not recommended for PROD
                .cors((cors)->cors.configurationSource(corsConfigSource()));


        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigSource(){
        CorsConfiguration config= new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200","http://localhost:8080"));
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTION","PATCH"));
        config.setExposedHeaders(List.of("Authorization","content-type"));
        config.setAllowedHeaders(List.of("Authorization","content-type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user= User.withDefaultPasswordEncoder()
                .username("hulk")
                .password("rebel")
                .roles("ADMIN")
                .build();

        UserDetails user2= User.withDefaultPasswordEncoder()
                .username("thor")
                .password("avenger")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(List.of(user,user2));
    }
}
