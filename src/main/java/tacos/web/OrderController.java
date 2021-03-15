package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.Order;

/**
 * Any request-handling methods in this controller will handle requests 
 * whose path begins with '/orders'
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "orderForm";
	}
	
	@PostMapping
	public String proccessOrder(Order order) {
		System.out.println(order);
		return "redirect:/";
	}
	
}
