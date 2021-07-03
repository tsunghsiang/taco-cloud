package tacos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.data.Order;
import tacos.data.User;
import tacos.repository.jpa.OrderJpaRepository;

/**
 * Any request-handling methods in this controller will handle requests 
 * whose path begins with '/orders'
 */
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderJpaRepository orderRepo;
	
	@ModelAttribute(name = "order")
	public Order order() { return new Order(); }
	
	@Autowired
	public OrderController(OrderJpaRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		return "orderForm";
	}
	
	@PostMapping
	public String proccessOrder(@Valid Order order, 
								Errors errors, 
								SessionStatus status,
								@AuthenticationPrincipal User user) {
		if(errors.hasErrors()) {
			return "orderForm";
		}
		
		// Associate the user with the order
		order.setUser(user);
		// Save the order information into the database
		this.orderRepo.save(order);
		status.setComplete();
		System.out.println(order);
		return "redirect:/";
	}
	
}
