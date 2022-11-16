package sof3021.ph18485.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.bytebuddy.utility.RandomString;
import sof3021.ph18485.beans.ResetPasswordForm;
import sof3021.ph18485.entities.Account;
import sof3021.ph18485.repositoties.AccountRepository;
import sof3021.ph18485.services.MailService;
import sof3021.ph18485.services.ParamService;

@Controller
public class ForgotPasswordController {

	@Autowired
	private ParamService paramService;
	@Autowired
	private MailService mailService;
	@Autowired
	private AccountRepository accountRepo;

	@GetMapping("/forgot-password")
	public String forgotPasswordForm() {
		return "forgot-password";
	}

	@PostMapping("/forgot-password")
	public String forgotPasswordHandler(Model model) {
		String email = paramService.getString("email", "");
		model.addAttribute("email", email);
		if (email.isEmpty()) {
			model.addAttribute("error", "Không để trống email!");
		}
		Optional<Account> optional = accountRepo.findByEmail(email);
		if (!optional.isPresent()) {
			model.addAttribute("error", "Email không tồn tại!");
		}
		Account account = optional.get();
		String token = RandomString.make(30);
		account.setResetPasswordToken(token);
		accountRepo.save(account);
		this.sendMail(account.getEmail(), paramService.setSiteURL() + "/reset-password?token=" + token);
		model.addAttribute("message", "Email khôi phục khẩu đã được gửi đến " + account.getEmail() + ".");
		return "forgot-password";
	}

	@GetMapping("/reset-password")
	public String resetPasswordForm(Model model) {
		String token = paramService.getString("token", "");
		Optional<Account> optional = accountRepo.findByResetPasswordToken(token);
		if (optional.isPresent()) {
			ResetPasswordForm form = new ResetPasswordForm();
			form.setToken(token);
			model.addAttribute("data", form);
		} else {
			return "redirect:/home";
		}
		return "reset-password";
	}

	@PostMapping("/reset-password")
	public String resetPasswordHandler(Model model, @Valid @ModelAttribute("data") ResetPasswordForm form,
			BindingResult result) {
		String token = form.getToken();
		String password = form.getPassword();
		String rePassword = form.getRePassword();
		if (!password.equals(rePassword)) {
			result.addError(new FieldError("rePassword", "rePassword", "Xác nhận mật khẩu không khớp"));
		}
		if (token == null || token.isEmpty()) {
			return "redirect:/home";
		}
		Optional<Account> optional = accountRepo.findByResetPasswordToken(token);
		if (!optional.isPresent()) {
			return "redirect:/home";
		}
		if (!result.hasErrors()) {
			Account account = optional.get();
			account.setPassword(form.getPassword());
			account.setResetPasswordToken(null);
			accountRepo.save(account);
			model.addAttribute("message", "Đổi mật khẩu thành công");
		}
		return "reset-password";
	}

	private void sendMail(String email, String link) {
		String body = "<p>Vui lòng vào đường đẫn dưới đây để khôi phục mật khẩu.</p>" + "<a href='" + link
				+ "'>Khôi phục mật khẩu</a>";
		mailService.push(email, "Quên mật khẩu?", body);
	}
}
