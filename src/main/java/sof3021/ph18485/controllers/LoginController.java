package sof3021.ph18485.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sof3021.ph18485.beans.LoginForm;
import sof3021.ph18485.entities.Account;
import sof3021.ph18485.services.AccountService;
import sof3021.ph18485.services.SessionService;

@Controller
public class LoginController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private SessionService sessionService;

	@GetMapping("/login")
	public String loginForm(Model model) {
		LoginForm login = new LoginForm();
		model.addAttribute("data", login);
		return "login";
	}
	
	@PostMapping("/login")
	public String loginHandler(Model model, @Valid @ModelAttribute("data") LoginForm login, BindingResult result) {
		String username = login.getUsername();
		String password = login.getPassword();
		if (!result.hasErrors()) {
			Account account = accountService.findByUsernameAndPassword(username, password);
			if (account != null) {
				sessionService.set("accountId", account.getId());
				sessionService.set("username", account.getUsername());
				sessionService.set("fullname", account.getFullname());
				sessionService.set("role", account.getRole());
				return "redirect:/home";
			} else {
				model.addAttribute("message", "Tài khoản hoặc mật khẩu không đúng");
			}
		}
		return "login";
	}


	@GetMapping("/logout")
	public String logout() {
		sessionService.remove("accountId");
		sessionService.remove("username");
		sessionService.remove("fullname");
		sessionService.remove("role");
		return "redirect:/home";
	}
}