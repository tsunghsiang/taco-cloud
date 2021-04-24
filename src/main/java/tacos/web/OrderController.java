package tacos.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.Order;

/**
 * Any request-handling methods in this controller will handle requests 
 * whose path begins with '/orders'
 */
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	@ModelAttribute(name = "order")
	public Order order() { return new Order(); }
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		return "orderForm";
	}
	
	@PostMapping
	public String proccessOrder(@Valid Order order, Errors errors) {
		if(errors.hasErrors()) {
			return "orderForm";
		}
		System.out.println(order);
		return "redirect:/";
	}
	
}
