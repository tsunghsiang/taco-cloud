package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

	private JdbcTemplate db = null;
	private JdbcIngredientRowMapper drm = new JdbcIngredientRowMapper();;
	
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate db) { 
		this.db = db;
	}
	
	@Override
	public Iterable<Ingredient> findAll() { 
		return this.db.query("SELECT id, name, type FROM Ingredient", drm);
	}

	@Override
	public Ingredient find(String id) {
		// TODO Auto-generated method stub
		return this.db.queryForObject("SELECT id, name, type FROM ingredient WHERE id=?", drm, id);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		// TODO Auto-generated method stub
		this.db.update(	"INSERT INTO Ingredient (id, name, type) VALUES (?, ?, ?)", 
						ingredient.getId(), 
						ingredient.getName(), 
						ingredient.getType().toString()	);
		return ingredient;
	}

}
