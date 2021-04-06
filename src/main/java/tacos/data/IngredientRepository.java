package tacos.data;

import tacos.Ingredient;

public interface IngredientRepository {
	/**
	 * @return
	 * Query for all ingredients into a collection of Ingredient objects
	 */
	public Iterable<Ingredient> findAll();
	/**
	 * @param id
	 * @return
	 * Query for a single Ingredient by its id
	 */
	public Ingredient find(String id);
	/**
	 * @param ingredient
	 * @return
	 * Save an Ingredient object
	 */
	public Ingredient save(Ingredient ingredient);
}
