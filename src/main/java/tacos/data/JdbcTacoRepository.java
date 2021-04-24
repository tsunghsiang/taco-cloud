package tacos.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

import tacos.Ingredient;
import tacos.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {

	private JdbcTemplate db = null;
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate db) { this.db = db;}
	
	@Override
	public Taco save(Taco taco) {
		// Insert taco
		long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		saveTacoIngredientsInfo(tacoId, taco.getIngredients());
		return taco;
	}

	/**
	 * @param taco
	 * @return
	 * Ref: PreparedStatementCreator:
	 *      https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/PreparedStatementCreator.html
	 *      PreparedStatement:
	 *      https://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html?is-external=true
	 *      KeyHolder interface:
	 *      https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/support/KeyHolder.html
	 *      KeyHolder implementation:
	 *      https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/support/GeneratedKeyHolder.html
	 */
	private long saveTacoInfo(Taco taco) {
		taco.setCreatedAt(new Date());
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("INSERT INTO Taco(name, createdAt) VALUES (?, ?", Types.VARCHAR, Types.TIMESTAMP); 
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));
				KeyHolder holder = new GeneratedKeyHolder();
		this.db.update(psc, holder);
		
		long tacoId = holder.getKey().longValue();
		return tacoId;
	}

	private void saveTacoIngredientsInfo(long id, List<Ingredient> group) {
		for(Ingredient elem : group)
			this.db.update("INSERT INTO Taco_Ingredients(taco, ingredient) VALUES(?, ?)", id, elem.getId());	
	}
}
