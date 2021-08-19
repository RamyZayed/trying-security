package com.example.security.security;

import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepository userRepository;

    public SecurityConfig() {
        super();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .authorizeRequests()
                     .antMatchers("/signup","/user/register").permitAll()
                     .antMatchers("/delete/**").hasAnyAuthority("ADMIN","ADMIN2")
                     .antMatchers("/secured").access("hasRole('USER')")
                     .anyRequest().authenticated()

                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .loginProcessingUrl("/doLogin")

                    .and()
                    .logout().permitAll().logoutUrl("/logout ")
                //.logoutRequestMatcher(new AntPathRequestMatcher("/doLogout","GET"))// USE POST INSTEAD OF GET FOR PRODUCTION
                .and()
                .rememberMe()
                    .tokenValiditySeconds(3000)
                    .key("secret_something_to_validate_our_cookies")
                   // .useSecureCookie(true)  // this means it uses a cookie for https connections only
                    .rememberMeCookieName("choco-cookie")
                    .rememberMeParameter("hehe")

/*
                .rememberMe()
                    .tokenRepository(persistentTokenRepository() )*/


                .and()
                .csrf().disable()
                ;

    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

 /*   @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        final JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }*/
}
