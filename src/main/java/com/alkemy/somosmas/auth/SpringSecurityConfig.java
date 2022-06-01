package com.alkemy.somosmas.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userService;
	 private static final String[] AUTH_WHITELIST = {
	            "/swagger-resources/**",
	            "/swagger-ui.html",
	            "/v2/api-docs",
	            "/webjars/**",
	            "/api/docs"
	    };

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/* Se agrego durante el meet */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(AUTH_WHITELIST);
	}
	/* Para validar que el usuario sea de rol ADMIN*/
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
	httpSecurity.csrf().disable();
		httpSecurity
				.authorizeRequests().antMatchers(HttpMethod.GET).hasAnyAuthority("regular","admin")
				.and()
				.authorizeRequests().antMatchers("/users/auth/**").permitAll()
				.and()
				.authorizeRequests().antMatchers(HttpMethod.POST).hasAuthority("admin")
				.and()
				.authorizeRequests().antMatchers(HttpMethod.PUT).hasAuthority("admin")
				.and()
				.authorizeRequests().antMatchers(HttpMethod.DELETE).hasAuthority("admin")
				.anyRequest().permitAll();}

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userService).passwordEncoder(passwordEncoder());
	}

	@Bean("authenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
