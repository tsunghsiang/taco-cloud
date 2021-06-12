package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.data.User;

@Repository
public interface UserJpaRespository extends CrudRepository<User, Long>{
	public User findByUsername(String username);
}
