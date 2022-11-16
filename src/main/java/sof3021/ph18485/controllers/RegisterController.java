package sof3021.ph18485.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sof3021.ph18485.beans.RegisterForm;
import sof3021.ph18485.entities.Account;
import sof3021.ph18485.entities.AccountRole;
import sof3021.ph18485.services.AccountService;
import sof3021.ph18485.services.MailService;

@Controller
public class RegisterController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private MailService mailService;

	@GetMapping("/register")
	public String registerForm(Model model) {
		RegisterForm form = new RegisterForm();
		model.addAttribute("data", form);
		return "register";
	}

	@PostMapping("/register")
	public String registerHandler(Model model, RedirectAttributes redirect,
			@Valid @ModelAttribute("data") RegisterForm form, BindingResult result) {
		String username = form.getUsername();
		String email = form.getEmail();
		String password = form.getPassword();
		String rePassword = form.getRePassword();
		if (accountService.existsByUsername(username)) {
			result.addError(new FieldError("register", "username", "Tên đăng nhập đã tồn tại"));
		}
		if (accountService.existsByEmail(email)) {
			result.addError(new FieldError("register", "email", "Email đã được sử dụng"));
		}
		if (!password.equals(rePassword)) {
			result.addError(new FieldError("register", "rePassword", "Xác nhận mật khẩu không khớp"));
		}
		if (!result.hasErrors()) {
			Account account = new Account();
			account.setUsername(username);
			account.setPassword(password);
			account.setFullname(form.getFullname());
			account.setEmail(form.getEmail());
			account.setActivated(0);
			account.setRole(AccountRole.USER);
			// Email xác thực đã được gửi đến " + accountNew.getEmail() + ".
			if (accountService.save(account) != null) {
				redirect.addFlashAttribute("message", "Đăng ký tài khoản thành công.");
				return "redirect:/register";
			}
		}
		return "register";
	}

	public void sendMail(String email, String link) {
		String body = "<p>Vui lòng vào đường đẫn dưới đây để xác thực email.</p>" + "<a href='" + link
				+ "'>Xác thực email</a>";
		mailService.push(email, "Xác thực email", body);
	}
}
