package sof3021.ph18485.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.DocumentException;

import net.bytebuddy.utility.RandomString;
import sof3021.ph18485.beans.ChangePasswordForm;
import sof3021.ph18485.beans.ProfileForm;
import sof3021.ph18485.entities.Account;
import sof3021.ph18485.entities.Order;
import sof3021.ph18485.entities.OrderDetail;
import sof3021.ph18485.repositoties.AccountRepository;
import sof3021.ph18485.services.AccountService;
import sof3021.ph18485.services.OrderService;
import sof3021.ph18485.services.ParamService;
import sof3021.ph18485.services.PdfService;
import sof3021.ph18485.services.SessionService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private SessionService sessionService;
	@Autowired
	private ParamService paramService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private PdfService pdfService;

	@GetMapping("/profile")
	public String profile(Model model) {
		String username = sessionService.get("username");
		Account account = accountService.findByUsername(username);
		model.addAttribute("image", account.getPhoto());
		model.addAttribute("data", account);
		return "/user/profile";
	}

	@PostMapping("/profile")
	public String updateProfileHandler(Model model, RedirectAttributes redirect,
			@RequestParam("image") MultipartFile image, @Valid @ModelAttribute("data") ProfileForm form,
			BindingResult result) {
		String username = sessionService.get("username");
		Account account = accountService.findByUsername(username);
		if (!result.hasErrors()) {
			String fullname = form.getFullname();
			String email = form.getEmail();
			String fileName = image.getOriginalFilename();

			paramService.save(image, "/account");
			accountService.updateProfile(account, fullname, email, fileName);
			model.addAttribute("image", fileName);
			redirect.addFlashAttribute("message", "C???p nh???t th??ng tin th??nh c??ng");
			return "redirect:/user/profile";
		}
		model.addAttribute("image", account.getPhoto());
		return "/user/profile";
	}

	@GetMapping("/change-password")
	public String changePasswordForm(Model model) {
		ChangePasswordForm form = new ChangePasswordForm();
		model.addAttribute("data", form);
		return "/user/change-password";
	}

	@PostMapping("/change-password")
	public String changePasswordHandler(Model model, RedirectAttributes redirect,
			@Valid @ModelAttribute("data") ChangePasswordForm form, BindingResult result) {
		String username = sessionService.get("username");
		String oldPassword = form.getOldPassword();
		String newPassword = form.getNewPassword();
		String rePassword = form.getRePassword();
		// n???u kh??ng t???n t???i username th?? redirect v??? home
		if (username == null || username.isEmpty()) {
			return "redirect:/home";
		}
		// l???y account b???ng username v?? password
		Account account = accountService.findByUsernameAndPassword(username, oldPassword);
		// ki???m tra n???u ko t???n t???i th?? sai mk c??
		if (account == null) {
			result.addError(new FieldError("oldPassword", "oldPassword", "M???t kh???u c?? kh??ng ????ng"));
		}
		// ki???m tra rePassword tr??ng vs newPassword hay k
		if (!newPassword.equals(rePassword)) {
			result.addError(new FieldError("rePassword", "rePassword", "X??c nh???n m???t kh???u kh??ng kh???p"));
		}
		// n???u ko c?? l???i t??? form
		if (!result.hasErrors()) {
			// c???p nh???t m???t kh???u m???i
			accountService.updatePassword(account, newPassword);
			redirect.addFlashAttribute("message", "?????i m???t kh???u th??nh c??ng");
			return "redirect:/user/change-password";
		}
		return "/user/change-password";
	}

	@GetMapping("/active")
	public String active(Model model) {
		RandomString.make(30);
		return "/user/active";
	}

	@GetMapping("/order-history")
	public String orderHistory(Model model) {
		int accountId = sessionService.get("accountId");
		int currPage = paramService.getInt("p", 1);
		if (currPage <= 0) {
			return "redirect:/user/order-history";
		}
		Pageable pageable = PageRequest.of(currPage - 1, 5);
		Page<Order> page = orderService.findByAccountId(accountId, pageable);
		model.addAttribute("page", page);
		return "user/order-history";
	}

	@GetMapping("/order-history/{id}/pdf")
	public void orderHistoryPDF(@PathVariable("id") Integer id, HttpServletResponse resp)
			throws DocumentException, IOException {
		Order order = orderService.findById(id);
		if (order == null) {
			return;
		}
		pdfService.OrderPdfExport(resp, order);
//		return "user/order-history";
	}

	@GetMapping("/order-history/detail")
	public String orderHistoryDetail(Model model) {
		int id = paramService.getInt("id", -1);
		if (id == -1) {
			return "redirect:/user/order-history";
		}
		Order order = orderService.findById(id);
		if (order == null) {
			return "redirect:/user/order-history";
		}
		List<OrderDetail> orderDetails = order.getOrderDetails();
		model.addAttribute("orderDetails", orderDetails);
		return "user/order-detail";
	}
}
