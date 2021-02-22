package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Ask Lombok to generate missing methods as well as a constructor
 */
@Data
@RequiredArgsConstructor
public class Ingredient {

	public static enum Type { WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE };
	
	public String mId;
	public String mName;
	public Type mType;

	
	public Ingredient(String id, String name, Type type) {
		this.mId = id;
		this.mName = name;
		this.mType = type;
	}
	
	public Type getType() { return this.mType; }
}
