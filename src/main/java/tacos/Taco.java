package tacos;

import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class Taco {
	
	@NonNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;
	@Size(min=1, message="You must choose at least 1 ingredient")
	private List<String> ingredients;
	  
	/**
	 * @return
	 * Get taco name
	 */
	public String getName() { return this.name; }
	/**
	 * @return
	 * Get list of ingredients
	 */
	public List<String> getIngredients(){ return this.ingredients; }
	/**
	 * Display contents of Taco design
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("[TACO] Name: %s", name));
		if(ingredients == null) {
			sb.append(", No Ingredients.");
		}
		else {
			ingredients.forEach(elem->System.out.println(elem));
			//	sb.append(String.format("\n[INGREDIENT]: %s", str));
			//}
		}
		return sb.toString();
	}
}
