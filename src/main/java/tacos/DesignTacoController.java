package tacos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient.Type;

/* Simple Logging Facade for Java */
@Slf4j
/* Identify the class as a controller; 
 * mark it as a candidate for component scanning
 * */
@Controller
/* When annotation '@RequestMapping' is applied at the class-level,
 * it indicates what kinds of requests a controller would handle */
@RequestMapping("/design")
public class DesignTacoController {
	
	@GetMapping
	public String showDesignForm(Model model) {
		addIngredientsToModel(model);
		model.addAttribute("taco", new Taco());
		
		// return a logical name of a view
		return "design";
	}
	
	public void addIngredientsToModel(Model model) {
		// Prepare a list of ingredients
		List<Ingredient> ingredients = Arrays.asList(
			new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
			new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
			new Ingredient("GRBP", "Ground Beef", Type.PROTEIN),
			new Ingredient("CARN", "Carnitas", Type.PROTEIN),
			new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
			new Ingredient("LETC", "Lettuce", Type.CHEESE),
			new Ingredient("CHED", "Cheddar", Type.CHEESE),
			new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
			new Ingredient("SLSA", "Salsa", Type.SAUCE),
			new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);
		
		// Add each group of same type to model attributes
		Type [] types = Ingredient.Type.values();
		for(Type type : types) {
			model.addAttribute(	type.toString().toLowerCase(), 
								filterByType(ingredients, type)	);
		}
	}	
	
	/**
	 * @param design (argument name is identical to th:object)
	 * Process taco submitted by user
	 */
	@PostMapping
	  public String processDesign(@Valid @ModelAttribute("taco") Taco taco, Errors errors, Model model) {
	    if (errors.hasErrors()) {
	      return "design";
	    }
		System.out.println(taco);
		return "home";
		// return "redirect:order/current"
	}
	
	/**
	 * @param ingredients
	 * @param type
	 * @return An array of same type
	 * The function filters ingredients by a specific type 
	 */
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
		return ingredients.stream()
						  .filter(elem -> elem.getType().equals(type))
						  .collect(Collectors.toList());
	}
}
