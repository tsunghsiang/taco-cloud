package tacos.repository.jdbc;

import tacos.data.Order;

public interface OrderRepository {
	Order save(Order order);
}
