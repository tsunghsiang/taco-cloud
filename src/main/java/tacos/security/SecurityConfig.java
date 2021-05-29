package tacos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource ds;
	private final String query_user_by_username = "SELECT username, '{noop}' || password, enabled FROM Users WHERE username = ?";
	private final String query_auth_by_username = "SELECT username, authority FROM UserAuthorities WHERE username = ?";
	
	@Autowired
	protected void configure​(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(this.ds)
			.usersByUsernameQuery(query_user_by_username)
			.authoritiesByUsernameQuery(query_auth_by_username);
	}
	
}