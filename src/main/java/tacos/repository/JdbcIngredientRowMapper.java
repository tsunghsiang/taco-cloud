package tacos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tacos.data.Ingredient;

public class JdbcIngredientRowMapper implements RowMapper<Ingredient> {

	@Override
	public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Ingredient ingredient = new Ingredient(	rs.getString("id"), 
												rs.getString("name"),
												Ingredient.Type.valueOf(rs.getString("type")));
		return ingredient;
	}

}
