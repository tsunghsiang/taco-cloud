package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected void configure​(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		 * Prior to Spring Security 5.0 the default PasswordEncoder was NoOpPasswordEncoder which required plain text passwords.
		 * Please refer to link below:
		 * ref: https://mkyong.com/spring-boot/spring-security-there-is-no-passwordencoder-mapped-for-the-id-null/
		 * */
		
		auth.inMemoryAuthentication()
			.withUser("buzzz")
			.password("{noop}inifnity")
			.authorities("ROLE_USER")
			.and()
			.withUser("woody")
			.password("{noop}bullseye")
			.authorities("ROLE_USER");
	}
	
}