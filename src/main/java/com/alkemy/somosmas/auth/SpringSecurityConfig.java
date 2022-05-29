package com.alkemy.somosmas.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userService;

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/* Se agrego durante el meet */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**");
	}
	/* Para validar que el usuario sea de rol ADMIN*/
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
	httpSecurity.csrf().disable();
		httpSecurity.authorizeRequests().antMatchers("/categories/**").hasRole("")
				.and()
				.authorizeRequests().anyRequest().permitAll();}

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

//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//        .csrf()
//        .disable()
//        .authorizeRequests()
//        .antMatchers(new String[]{"/auth/register", "/auth/login", "/h2/**"}).permitAll()
//        .and()
//        .authorizeRequests()
//        .antMatchers("/test").hasAnyAuthority("ADMIN");
//	}
}
