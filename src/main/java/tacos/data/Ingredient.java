package tacos.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Ask Lombok to generate missing methods as well as a constructor
 */
@Data
@RequiredArgsConstructor
/*
 * JPA requires the entity has non-arguments constructor
 * */
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
/*
 * Declare Ingredient as a JPA entity
 * */
@Entity
@Table(name="Ingredient")
public class Ingredient {

	public static enum Type { WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE };
	/*
	 * Designate the property that will uniquely identify the entity in the database
	 * */
	@Id
	public String id;
	public String name;
	public Type type;

	public Ingredient() {}
	
	public Ingredient(String id, String name, Type type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}
	
	public String getId() { return id; }
	
	public String getName() { return name; }
	
	public Type getType() { return this.type; }
}
