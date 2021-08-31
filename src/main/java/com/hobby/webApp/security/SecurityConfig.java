package com.hobby.webApp.security;

import com.hobby.webApp.entities.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority(Role.ADMIN.toString())
                .antMatchers("/user/**").hasAuthority(Role.USER.toString())
                .antMatchers("/guest/**").permitAll()
            .and()
                .formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/login/form")
                .defaultSuccessUrl("/index", true)
                .failureUrl("/guest/register/form")
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout");
    }
}
