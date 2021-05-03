package tacos;

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
import org.springframework.web.bind.support.SessionStatus;

import tacos.data.OrderRepository;

/**
 * Any request-handling methods in this controller will handle requests 
 * whose path begins with '/orders'
 */
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderRepository orderRepo;
	
	@ModelAttribute(name = "order")
	public Order order() { return new Order(); }
	
	@Autowired
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		return "orderForm";
	}
	
	@PostMapping
	public String proccessOrder(@Valid Order order, Errors errors, SessionStatus status) {
		if(errors.hasErrors()) {
			return "orderForm";
		}
		
		this.orderRepo.save(order);
		status.setComplete();
		System.out.println(order);
		return "redirect:/";
	}
	
}
