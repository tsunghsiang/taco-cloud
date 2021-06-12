package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tacos.data.User;
import tacos.repository.jpa.UserJpaRespository;

/**
 * @Service flags the class for inclusion in Spring's component scanning, 
 * so there's no need to declare the class as a bean
 */
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

	private UserJpaRespository repo;
	
	@Autowired
	public UserRepositoryUserDetailsService(UserJpaRespository repo) {
		this.repo = repo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User detail = repo.findByUsername(username);
		
		if(detail != null) {
			return detail;
		}else {
			throw new UsernameNotFoundException("Username: " + username + " not found");
		}
	}

}
