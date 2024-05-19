package com.gfg.jdblblr213springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.provisioning.UserDetailsManagerResourceFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
//                                .requestMatchers("/user")
//                                .permitAll()
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
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                if (authentication == null){
                    throw new AuthenticationCredentialsNotFoundException(" no authentication");

                }
                UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
                String username = token.getName();
                String passsword = token.getCredentials().toString();

                if(username == ""){
                    throw new AuthenticationCredentialsNotFoundException(" no authentication");

                }
                UserDetails userFromDb = userDetailsService.loadUserByUsername(username);

                if(!passwordEncoder().matches(passsword, userFromDb.getPassword())){
                        throw new AuthenticationCredentialsNotFoundException(" invalid creds");
                    }
                return new UsernamePasswordAuthenticationToken(username, passsword,userFromDb.getAuthorities());

            }

            @Override
            public boolean supports(Class<?> authentication) {
                return authentication.equals(UsernamePasswordAuthenticationToken.class);
            }
        };
    }

    @Bean
    AuthenticationManager authenticationManager(){
        return new ProviderManager(authenticationProvider(inMemoryUserDetailsManager()),authenticationProvider(userManager()));
    }

    @Bean
    UserManagerImpl userManager(){
        return new UserManagerImpl();
    }



//    @Bean
//    AuthenticationManager authenticationManager(){
//        return new ProviderManager(
//                authenticationProvider(inMemoryUserDetailsManager()),
//                authenticationProvider( libraryAuthnManager())
//        );
//    }
//
//    @Bean
//    LibraryAuthnManager libraryAuthnManager(){
//        return new LibraryAuthnManager();
//    }
//
//    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
//        return new AuthenticationProvider() {
//            @Override
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                if (authentication == null){
//                    throw new AuthenticationCredentialsNotFoundException(" no authentication");
//
//                }
//                UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
//                String username = token.getName();
//                String passsword = token.getCredentials().toString();
//
//                if(username == ""){
//                    throw new AuthenticationCredentialsNotFoundException(" no authentication");
//
//                }
//
//                try {
//                    UserDetails userFromDb = userDetailsService.loadUserByUsername(username);
//
//                    if(!passwordEncoder().matches(passsword, userFromDb.getPassword())){
//                        throw new AuthenticationCredentialsNotFoundException(" invalid creds");
//                    }
//                    return new UsernamePasswordAuthenticationToken(username, passsword,userFromDb.getAuthorities());
//
//                }catch (UsernameNotFoundException usernameNotFoundException) {
//                    throw new AuthenticationCredentialsNotFoundException(" no such user");
//
//                }
//
//            }
//
//            @Override
//            public boolean supports(Class<?> authentication) {
//                return authentication.equals(UsernamePasswordAuthenticationToken.class);
//            }
//        };
//    }
}
