package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.data.Taco;

@Repository
public interface TacoJpaRepository extends CrudRepository<Taco, Long> {
}
