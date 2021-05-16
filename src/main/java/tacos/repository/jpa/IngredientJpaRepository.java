package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.data.Ingredient;

@Repository
public interface IngredientJpaRepository extends CrudRepository<Ingredient, String> {
}
