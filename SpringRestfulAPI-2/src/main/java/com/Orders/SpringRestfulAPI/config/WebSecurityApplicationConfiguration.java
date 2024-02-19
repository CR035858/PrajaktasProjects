package com.Orders.SpringRestfulAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityApplicationConfiguration {

	private final UserDetailsService userDetailsService;

	@Bean
	PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}

	//authorization
		@Bean
		SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authz -> authz
				.requestMatchers("/h2-console**", "/login**", "/logout**", "/actuator**").permitAll()	
				.requestMatchers(HttpMethod.GET, "/api/orders/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/orders").hasAnyRole("USER", "ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/orders**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/orders/**").hasRole("ADMIN")
				.anyRequest()
				.fullyAuthenticated()
			);
			http.cors(c ->c.disable());
			http.csrf(csrf -> csrf.disable());
			http.httpBasic(Customizer.withDefaults());
			http.formLogin(Customizer.withDefaults());
			http.headers(customizer -> customizer.frameOptions(o -> o.disable()));
			return http.build();/*	 */
			
			
			
			
			/*	 http.authorizeRequests().antMatchers("/students/list", "/students/showFormForAdd", "/students/save")
				.hasAnyAuthority("ADMIN", "USER").antMatchers("/students/showFormForUpdate", "/students/delete")
				.hasAuthority("ADMIN").anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login")
				.defaultSuccessUrl("/students/list").permitAll().and().logout().logoutSuccessUrl("/login").permitAll()
				.and().exceptionHandling().accessDeniedPage("/students/403").and().csrf().and().cors().disable();
		 	*/
		}
		@Bean
		AuthenticationProvider authProvider() {
			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			authProvider.setPasswordEncoder(passwordEncoder());
			authProvider.setUserDetailsService(this.userDetailsService);
			return authProvider;
		}

	}

