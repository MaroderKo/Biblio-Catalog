//package com.smart.catalog.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
//
//@Configuration
//public class InMemorySecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin")
//                .password("$2a$12$K1CDMxbikZV.5lYFy8UeCewpBqj0YjvVyS71CWifE9Te9eqYU2zxu")
//                .roles("ADMIN");
//    }
//
//    // HTTP Basic Example
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .defaultSuccessUrl("/books", false);
////                .mvcMatchers("/").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .httpBasic();
//    }
//}
