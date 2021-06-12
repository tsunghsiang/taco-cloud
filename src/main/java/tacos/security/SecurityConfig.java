package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/* JDBC-based authentication
	 * @Autowired
	 * private DataSource ds;
	 * private final String query_user_by_username = "SELECT username, '{noop}' || password, enabled FROM Users WHERE username = ?";
	 * private final String query_auth_by_username = "SELECT username, authority FROM UserAuthorities WHERE username = ?";
	 * */
	
	@Autowired
	private UserDetailsService uds;	
	
	@Bean
	public PasswordEncoder encoder() {
		return new StandardPasswordEncoder("53crt");
	}
	
	@Autowired
	protected void configure​(AuthenticationManagerBuilder auth) throws Exception {
		
		/* In-Memory-based authentication
		 * auth.inMemoryAuthentication()
		 *     .withUser("user")
		 *     .password("{noop}inifnity")
		 *     .authorities("ROLE_USER");
		 * */
		
		/* JDBC-based authentication
		 * auth.jdbcAuthentication()
		 *     .dataSource(this.ds)
		 *     .usersByUsernameQuery(query_user_by_username)
		 *     .authoritiesByUsernameQuery(query_auth_by_username);
		 * */

		/*
		 * LDAP-based authentication
		 * LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapAuth = auth.ldapAuthentication();
		 * ldapAuth.userSearchBase("ou=people")
		 *         .userSearchFilter("uid={0}")
		 *         .groupSearchBase("ou=groups")
		 *         .groupSearchFilter("member={0}")
		 *         .passwordCompare()
		 *         .passwordAttribute("userPassword");
		 * ldapAuth.contextSource()
		 *         .root("dc=tacocloud,dc=com");
		 * */
		
		auth.userDetailsService(uds)
			.passwordEncoder(encoder());
	}
}