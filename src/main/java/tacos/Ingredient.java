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
	
	private String mId;
	private String mName;
	private Type mType;
}
