package com.example.loginwithspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public UserDetailsService userDetailsService() throws Exception{
		UserDetails admin=User.builder()
				.username("peter")
				.password(passwordEncoder().encode("pass1"))
				.roles("ADMIN")
				.build();
		UserDetails user=User.builder()
				.username("john")
				.password(passwordEncoder().encode("pass2"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(admin,user);
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((authorize)->authorize.anyRequest().authenticated())
			.formLogin().loginPage("/login").permitAll();
		return http.build();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

}
