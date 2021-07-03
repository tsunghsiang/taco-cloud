package tacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.data.RegistrationForm;
import tacos.repository.jpa.UserJpaRespository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	private UserJpaRespository repo;
	
	@ModelAttribute(name="formdata")
	public RegistrationForm formdata() {
		return new RegistrationForm();
	}
	
	@Autowired
	public RegistrationController(UserJpaRespository repo) {
		this.repo = repo;
	}
	
	@GetMapping
	public String showRegisterForm(Model model) {
		return "registration";
	}
	
	/**
	 * @param userinfo
	 * @return
	 * @param name should be equivalent to th:object in Thymeleaf form
	 */
	@PostMapping
	public String processRegisteration(RegistrationForm formdata) {
		// System.out.print(formdata.toString());
		repo.save(formdata.toUser());
		return "redirect:/login";
	}
	
}
