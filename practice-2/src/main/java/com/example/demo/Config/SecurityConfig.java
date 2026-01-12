package com.example.demo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
public class SecurityConfig {

	/**@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/

	/**
	 * @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorizeRequests -> authorizeRequests
						.requestMatchers("/admin/signup", "/admin/signin").permitAll()
						.anyRequest().authenticated())
				.formLogin(formLogin -> formLogin
						.loginPage("/admin/signin")
						.usernameParameter("email")
						.passwordParameter("password")
						.defaultSuccessUrl("/admin/contacts", true)
						.permitAll())
				.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
						.logoutSuccessUrl("/admin/signin?logout")
						.permitAll());
		return http.build();
	}*/
}
