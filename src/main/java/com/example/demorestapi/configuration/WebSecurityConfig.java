package com.example.demorestapi.configuration;

import com.example.demorestapi.services.UserLoginService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserLoginService userLoginService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((req) -> req.requestMatchers("/ui**").authenticated()
                .requestMatchers("/person","/person/**").permitAll()   //allows all to access urls like person or whatever comes after person (**)
                .requestMatchers("/controller","/controller/hello","/controller/**").hasRole("ADMIN") //allows only users that have ADMIN role
                .anyRequest().authenticated()) //any other request must be accessed by a logged-in user
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll) // allows all users to access login page
                .httpBasic(withDefaults()) //enables basic authentication
                .csrf(AbstractHttpConfigurer::disable) //disable csrf
//                .cors(AbstractHttpConfigurer::disable);//not recommended for PROD
                .cors((cors)->cors.configurationSource(corsConfigSource()));


        return http.build();
    }

    /**
     * Creates a specific configuration for CORS. This configuration allows Angular to connect to a different backend
     * Defines methods allowed for CORS
     * Defines headers exposed
     * Defines allowed headers
     * Defines on which urls this configuration applies to
     * @return configuration
     */
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

    /**
     * Creates a bean which uses a specific UserDetailsService (loads from DB)
     * Sets a specific password encoder to be used for this authenticationProvider
     * @return provider
     */
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userLoginService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
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


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
