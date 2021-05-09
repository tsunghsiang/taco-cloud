package tacos.data;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class Taco {
	@NonNull
	private Long id;
	@NonNull
	private Date createdAt;
	@NonNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;
	@Size(min=1, message="You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;
	  
	/**
	 * @return
	 * Get unique id of the Taco
	 */
	public Long getId() { return id; }
	/**
	 * @return
	 * Get the date when the Taco is created
	 */
	public Date getCreatedAt() { return createdAt; }
	/**
	 * @return
	 * Get taco name
	 */
	public String getName() { return this.name; }
	/**
	 * @return
	 * Get list of ingredients
	 */
	public List<Ingredient> getIngredients(){ return this.ingredients; }
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

	public void setCreatedAt(Date date) {
		this.createdAt = date;
	}

	public void setId(Long id) { this.id = id; }

}
