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

import sof3021.ph18485.beans.AccountForm;
import sof3021.ph18485.entities.Account;
import sof3021.ph18485.entities.AccountRole;
import sof3021.ph18485.entities.Product;
import sof3021.ph18485.repositoties.AccountRepository;
import sof3021.ph18485.services.AccountService;
import sof3021.ph18485.services.ExcelService;
import sof3021.ph18485.services.ParamService;

@Controller
@RequestMapping("/admin")
public class AdminAccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private ParamService paramService;
	@Autowired
	private ExcelService excelService;

	@GetMapping("/accounts")
	public String table(Model model) {
		String keyword = paramService.getString("keyword", "");
		int currPage = paramService.getInt("p", 1);
		if (currPage <= 0) {
			return "redirect:/admin/accounts";
		}
		Pageable pageable = PageRequest.of(currPage - 1, 5);
		Page<Account> page = accountService.findByKeyword("%" + keyword + "%", pageable);
		model.addAttribute("keyword", keyword);
		model.addAttribute("page", page);
		return "admin/accounts/table";
	}

	@GetMapping("/accounts/create")
	public String createForm(Model model) {
		Account account = new Account();
		account.setActivated(1);
		account.setRole(AccountRole.USER);
		model.addAttribute("data", account);
		return "admin/accounts/create";
	}

	@PostMapping("/accounts/create")
	public String createHandler(Model model, RedirectAttributes redirect, @RequestParam("image") MultipartFile image,
			@Valid @ModelAttribute("data") AccountForm form, BindingResult result) {
		if (accountService.existsByUsername(form.getUsername())) {
			result.addError(new FieldError("register", "username", "Tên đăng nhập đã tồn tại"));
		}
		if (accountService.existsByUsernameAndEmail(form.getUsername(), form.getEmail())) {
			result.addError(new FieldError("register", "email", "Email đã được sử dụng"));
		}
		if (!result.hasErrors()) {
			String fileName = image.getOriginalFilename();
			paramService.save(image, "/account");
			// lấy giá trị từ form
			Account account = new Account();
			account.setId(form.getId());
			account.setUsername(form.getUsername());
			account.setPassword(form.getPassword());
			account.setFullname(form.getFullname());
			account.setEmail(form.getEmail());
			account.setActivated(form.getActivated());
			account.setRole(form.getRole());
			//
			model.addAttribute("image", fileName);
			if (accountService.save(account) != null) {
				redirect.addFlashAttribute("message", "Thêm người dùng thành công");
				return "redirect:/admin/accounts/create";
			} else {
				model.addAttribute("error", "Thêm người dùng thất bại");
			}
		}
		return "admin/accounts/create";
	}

	@GetMapping("/accounts/update/{id}")
	public String updateForm(Model model, @PathVariable("id") Integer id) {
		Account account = accountService.findById(id);
		if (account != null) {
			account.setPassword("");
			model.addAttribute("image", account.getPhoto());
			model.addAttribute("data", account);
			return "admin/accounts/update";
		} else {
			return "redirect:/admin/accounts";
		}
	}

	@PostMapping("/accounts/update")
	public String updateHandler(Model model, RedirectAttributes redirect, @RequestParam("image") MultipartFile image,
			@Valid @ModelAttribute("data") AccountForm form, BindingResult result) {
		if (form.getId() == null) {
			return "redirect:/admin/accounts";
		}
		if (accountService.existsByUsernameAndEmail(form.getUsername(), form.getEmail())) {
			result.addError(new FieldError("register", "email", "Email đã được sử dụng"));
		}
		if (!result.hasErrors()) {
			String fileName = image.getOriginalFilename();
			paramService.save(image, "/account");
			// lấy giá trị từ form
			Account account = accountService.findById(form.getId());
			account.setPassword(form.getPassword());
			account.setFullname(form.getFullname());
			account.setEmail(form.getEmail());
			account.setActivated(form.getActivated());
			account.setRole(form.getRole());
			//
			model.addAttribute("image", fileName);
			if (accountService.save(account) != null) {
				redirect.addFlashAttribute("message", "Cập nhật người dùng thành công");
				return "redirect:/admin/accounts/update/" + form.getId();
			} else {
				redirect.addFlashAttribute("error", "Cập nhật người dùng thất bại");
				return "redirect:/admin/accounts/update/" + form.getId();
			}
		}
		return "admin/accounts/update";
	}

	@PostMapping("/accounts/delete")
	public String delete(@RequestParam("id") Integer id) {
		accountService.deleteById(id);
		return "redirect:/admin/orders";
	}

	@GetMapping("/accounts/export")
	public void exportAccounts(HttpServletResponse resp) throws IOException {
		List<Account> accounts = accountService.findAll();
		excelService.exportAccounts(resp, accounts);
	}

}
