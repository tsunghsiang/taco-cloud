package tacos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.data.Ingredient;
import tacos.data.Order;
import tacos.data.Taco;
import tacos.data.Ingredient.Type;
import tacos.repository.IngredientRepository;
import tacos.repository.TacoRepository;

/* Simple Logging Facade for Java */
@Slf4j
/* Identify the class as a controller; 
 * mark it as a candidate for component scanning
 * */
@Controller
/* When annotation '@RequestMapping' is applied at the class-level,
 * it indicates what kinds of requests a controller would handle */
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	
	private IngredientRepository ingredientRepo;
	private TacoRepository tacoRepo;
	
	/*
	 * In general, Spring-MVC will always make a call first to that method, 
	 * before it calls any request handler methods. 
	 * 
	 * That is, @ModelAttribute methods are invoked before the controller methods
	 * annotated with @RequestMapping are invoked. 
	 * 
	 * The logic behind the sequence is that, the model object has to be created 
	 * before any processing starts inside the controller methods.
	 * 
	 * Reference: https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation
	 * */
	@ModelAttribute(name = "order")
	 public Order order() {
		return new Order();
	 }
	
	 @ModelAttribute(name = "taco")
	 public Taco taco() {
		 return new Taco();
	 }
	 
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, 
								TacoRepository tacoRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		addIngredientsToModel(model);
		
		// return a logical name of a view
		return "design";
	}
	
	public void addIngredientsToModel(Model model) {
		// Prepare a list of ingredients
		List<Ingredient> ingredients = new ArrayList<>();
		this.ingredientRepo.findAll().forEach(elem->ingredients.add(elem));
		/*
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
		*/
		
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
	public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {
	    if (errors.hasErrors()) {
	      return "design";
	    }
		System.out.println(taco);
		
		// Save taco information to database
		// Save taco to order model attributes for accessing tacoId
		if(taco.getIngredients() != null) {
			Taco saved = this.tacoRepo.save(taco);
			order.setTacos(saved);
		}
		
		/*
		 * redirect: direct current form to next view
		 * orders/current: Direct to a controller that receives 'orders' mapping
		 * with GET mapping request 'current'
		 * */
		return "redirect:/orders/current";
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
