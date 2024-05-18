package com.gfg.jdblblr213springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(
//                        (autz) ->
//                                autz.requestMatchers("/home")
//                                        .permitAll()
////                                .requestMatchers("/api/user")
////                                .hasAnyRole("ADMIN")
//                                        .requestMatchers("/api/**")
//                                        .authenticated()
//
//                )
//                .formLogin(form -> form.disable())
//                .csrf(csrf -> csrf.disable())
//                .build();
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic(x->Customizer.withDefaults())
                .authorizeHttpRequests(authz ->
                        authz.requestMatchers("/home")
                                .permitAll()
                                .requestMatchers("/api/**")
                                .authenticated()
//                                .requestMatchers("/secure")
//                                .authenticated()
//                                .requestMatchers("/secret")
//                                .hasAnyAuthority("ADMIN")


                )
                .formLogin((x)->x.disable())
                .csrf(x->x.disable())
                .cors(x->x.disable())
                .build();
    }

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
