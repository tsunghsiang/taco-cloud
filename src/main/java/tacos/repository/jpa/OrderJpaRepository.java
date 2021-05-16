package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.data.Order;

@Repository
public interface OrderJpaRepository extends CrudRepository<Order, Long> {
}
