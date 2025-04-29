package com.example.project3.Config;

import com.example.project3.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@org.springframework.context.annotation.Configuration

@EnableWebSecurity
@RequiredArgsConstructor
public class Configuration {



    ///  set the method below with an object of this class

    private final MyUserDetailsService myUserDetailsService;

    /// bean runs everything
    @Bean
    /// methods
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        /// object same type
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        /// responsible for user username
        /// set it
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        /// BCryptPasswordEncoder() to get encrypted
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return authenticationProvider;
    }





    @Bean
    /// http security is the start of the chain>session
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        ///  csrf attach -> session Management ->authentication provider (from method above) -> endpoints not mention
        ///-> log out

        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/emp/register").permitAll()
                .requestMatchers("/api/v1/customer/register").permitAll()
                .requestMatchers("/api/v1/customer/get-customers").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/customer/update").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/delete").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/get-my-accounts").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/add").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/delete").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/withdraw").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/deposit").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/transferFunds").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/emp/update").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/emp/delete").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/emp/activate").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/emp/block").hasAuthority("EMPLOYEE")
//                .requestMatchers("/api/v1/auth/delete").hasAuthority("User")
//                .requestMatchers("/api/v1/todo/get-all").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/auth/get-users").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                /// any endpoints that are mentioned has to be authenticated to be accuses
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")            ///"/api/v1/auth/register" onw end point- ("api/v1/auth/**) all the end points in this class
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();

        return httpSecurity.build();


    }
}




