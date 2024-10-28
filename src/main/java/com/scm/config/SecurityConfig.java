package com.scm.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.scm.services.impl.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
public class SecurityConfig {
//  user create and login uisng java code with in memory service
// @Bean
// public UserDetailsService userDetailsService(){


    // User 1 created programatically
    // UserDetails user1 = User
    // .withDefaultPasswordEncoder()
    // .username("admin123")
    // .password("admin123")
    // .roles("ADMIN","USER")
    // .build();

    // User 2 created programatically
    // UserDetails user2 = User
    // .withDefaultPasswordEncoder()
    // .username("user123")
    // .password("password")
    // // .roles(null)
    // .build();


    // var inMemoryUserDetailsManager =  new InMemoryUserDetailsManager(user1,user2);
    // return inMemoryUserDetailsManager;

// Configure user from the Database
@Autowired
private SecurityCustomUserDetailService userDetailService;

@Autowired
private OAuthAuthenticationSuccessHandler handler;

// configuration of authentication provider for spring security
@Bean
public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    // user detail service object
    daoAuthenticationProvider.setUserDetailsService(userDetailService);

    // password encoder object
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

    // configuration 
    // URL configuration which need to be private and public
    httpSecurity.authorizeHttpRequests(authorize->{
        // authorize.requestMatchers("/home", "/register", "/services").permitAll();
        authorize.requestMatchers("/user/**").authenticated();
        authorize.anyRequest().permitAll(); 
        // form default login
        // if we want to change related to form, we can change here. 
    });
        
 httpSecurity.formLogin(formLogin -> {
    formLogin.loginPage("/login");
    formLogin.loginProcessingUrl("/authenticate");
    formLogin.successForwardUrl("/user/profile");
    // formLogin.failureForwardUrl("/login?error=true");
    formLogin.usernameParameter("email");
    formLogin.passwordParameter("password");


// // failure login handler
//     formLogin.failureHandler(new AuthenticationFailureHandler() {

//         @Override
//         public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//                 AuthenticationException exception) throws IOException, ServletException {
//             // TODO Auto-generated method stub
//             throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
//         }
        
//     });

//     // success login handler
//     formLogin.successHandler(new AuthenticationSuccessHandler() {

//         @Override
//         public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                 Authentication authentication) throws IOException, ServletException {
//             // TODO Auto-generated method stub
//             throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
//         }
        
//     });
    
 });
 httpSecurity.csrf(AbstractHttpConfigurer::disable);
 httpSecurity.logout(logoutForm->{
    logoutForm.logoutUrl("/logout");
    logoutForm.logoutSuccessUrl("/login?logout=true");
 });

//  oauth configuration
httpSecurity.oauth2Login(oauth -> {
    oauth.loginPage("/login");
    oauth.successHandler(handler);
});

  return httpSecurity.build();
}
 
@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}

}
// }
