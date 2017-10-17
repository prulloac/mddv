package edu.usach.mddv;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .cors().disable()
            .authorizeRequests()
                .antMatchers("/bower_components/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/**").hasRole("SU")
                .and()
            .formLogin()
                .loginPage("/index.html").permitAll()
                .and()
            .logout()
                .permitAll();
    }
}
