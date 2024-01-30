package com.greatlearning.studentmgmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.studentmgmt.service.UserDetailsServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService getDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationProvider getProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(getDetailsService());
		auth.setPasswordEncoder(getEncoder());
		return auth;
	}
	@Override
	public void configure(AuthenticationManagerBuilder builder) {
		builder.authenticationProvider(getProvider());
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/students/list","/students/showFormForAdd","/students/save")
		.hasAnyAuthority("ADMIN","USER")
		.antMatchers("/students/showFormForUpdate","/students/delete")
		.hasAuthority("ADMIN")
		.anyRequest().authenticated()
		.and().formLogin().loginProcessingUrl("/login")
		.defaultSuccessUrl("/students/list").permitAll()
		.and().logout().logoutSuccessUrl("/login").permitAll()
		.and().exceptionHandling().accessDeniedPage("/students/403")
		.and().csrf().and().cors().disable();
	}
}
