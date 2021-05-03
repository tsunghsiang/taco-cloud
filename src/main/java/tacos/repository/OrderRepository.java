package tacos.repository;

import tacos.data.Order;

public interface OrderRepository {
	Order save(Order order);
}
