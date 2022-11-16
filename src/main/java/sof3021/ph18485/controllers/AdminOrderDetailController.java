package sof3021.ph18485.controllers;

import java.io.IOException;
import java.sql.Date;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sof3021.ph18485.beans.AdminOrderForm;
import sof3021.ph18485.beans.OrderDetailForm;
import sof3021.ph18485.entities.Account;
import sof3021.ph18485.entities.Category;
import sof3021.ph18485.entities.Order;
import sof3021.ph18485.entities.OrderDetail;
import sof3021.ph18485.entities.Product;
import sof3021.ph18485.services.AccountService;
import sof3021.ph18485.services.ExcelService;
import sof3021.ph18485.services.OrderDetailService;
import sof3021.ph18485.services.OrderService;
import sof3021.ph18485.services.ParamService;
import sof3021.ph18485.services.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminOrderDetailController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private ParamService paramService;
	@Autowired
	private ExcelService excelService;

	@GetMapping("/order-details")
	public String table(Model model) {
		int currPage = paramService.getInt("p", 1);
		if (currPage <= 0) {
			return "redirect:/admin/order-details";
		}
		Pageable pageable = PageRequest.of(currPage - 1, 5);
		Page<OrderDetail> page = orderDetailService.findAll(pageable);
		model.addAttribute("page", page);
		return "admin/order-details/table";
	}

	@GetMapping("/order-details/create")
	public String createForm(Model model) {
		OrderDetailForm form = new OrderDetailForm();
		model.addAttribute("data", form);
		return "admin/order-details/create";
	}

	@GetMapping("/order-details/update/{id}")
	public String updateForm(Model model, @PathVariable("id") Integer id) {
		OrderDetail orderDetail = orderDetailService.findById(id);
		if (orderDetail == null) {
			return "redirect:/admin/order-details";
		}
		model.addAttribute("data", orderDetail);
		return "admin/order-details/update";
	}
	
	@PostMapping("/order-details/update")
	public String updateHandler(Model model, RedirectAttributes redirect, @Valid @ModelAttribute("data") OrderDetailForm form,
			BindingResult result) {
		if (form.getId() == null) {
			return "redirect:/admin/order-details";
		}
		if (!result.hasErrors()) {
			OrderDetail orderDetail = orderDetailService.findById(form.getId());
			orderDetail.setOrder(form.getOrder());
			orderDetail.setProduct(form.getProduct());
			orderDetail.setPrice(form.getPrice());
			orderDetail.setQuantity(form.getQuantity());
			if (orderDetailService.save(orderDetail) != null) {
				redirect.addFlashAttribute("message", "Cập nhật đơn hàng chi tiết thành công");
				return "redirect:/admin/order-details/update/" + form.getId();
			} else {
				redirect.addFlashAttribute("message", "Cập nhật đơn hàng chi tiết thất bại");
				return "redirect:/admin/order-details/update/" + form.getId();
			}
		}
		return "admin/order-details/update";
	}

	@PostMapping("/order-details/create")
	public String createHandler(Model model, RedirectAttributes redirect, @Valid @ModelAttribute("data") OrderDetailForm form,
			BindingResult result) {
		if (!result.hasErrors()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(form.getOrder());
			orderDetail.setProduct(form.getProduct());
			orderDetail.setPrice(form.getPrice());
			orderDetail.setQuantity(form.getQuantity());
			if (orderDetailService.save(orderDetail) != null) {
				redirect.addFlashAttribute("message", "Thêm đơn hàng chi tiết thành công");
				return "redirect:/admin/order-details/create";
			} else {
				redirect.addFlashAttribute("message", "Thêm đơn hàng chi tiết thất bại");
				return "redirect:/admin/order-details/create";
			}
		}
		return "admin/order-details/create";
	}

	@PostMapping("/order-details/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		orderDetailService.deleteById(id);
		return "redirect:/admin/order-details";
	}

	@GetMapping("/order-details/export")
	public void exportOrders(HttpServletResponse resp) throws IOException {
		List<Order> orders = orderService.findAll();
		excelService.exportOrders(resp, orders);
	}

	@ModelAttribute("orders")
	public List<Order> getOrders() {
		return orderService.findAll();
	}

	@ModelAttribute("products")
	public List<Product> getProducts() {
		return productService.findAll();
	}

}
