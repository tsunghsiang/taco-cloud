package tacos.repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tacos.data.Order;
import tacos.data.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository {

	private JdbcTemplate db = null;
	
	@Autowired
	public JdbcOrderRepository(JdbcTemplate db) { this.db = db; }

	@Override
	public Order save(Order order) {
		order.setPlacedAt(new Date());
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("INSERT INTO Taco_Order(deliveryName, deliveryStreet, deliveryCity, deliveryState, deliveryZip, ccNumber, ccExpiration, ccCVV, placedAt) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)", 
																				  Types.VARCHAR, 
																				  Types.VARCHAR,
																				  Types.VARCHAR,
																				  Types.VARCHAR,
																				  Types.VARCHAR,
																				  Types.VARCHAR,
																				  Types.VARCHAR,
																				  Types.VARCHAR, 
																				  Types.TIMESTAMP);
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(order.getName(),
																					 order.getStreet(),
																					 order.getCity(),
																					 order.getState(),
																					 order.getZip(),
																					 order.getCcNumber(),
																					 order.getCcExpiration(),
																					 order.getCcCVV(),
																					 new Timestamp(order.getPlacedAt().getTime())));
		KeyHolder holder = new GeneratedKeyHolder();
		this.db.update(psc, holder);
		
		Long tacoOrderId = holder.getKey().longValue();
		List<Taco> tacos = order.getTacos();
		for(Taco elem : tacos) {
			this.db.update("INSERT INTO Taco_Order_Tacos(tacoOrder, taco) VALUES (?, ?)", tacoOrderId, elem.getId());
		}
		
		return order;
	}		
}
