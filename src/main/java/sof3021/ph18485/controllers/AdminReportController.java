package sof3021.ph18485.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sof3021.ph18485.entities.ReportCategory;
import sof3021.ph18485.services.ExcelService;
import sof3021.ph18485.services.OrderDetailService;

@Controller
@RequestMapping("/admin")
public class AdminReportController {
	
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private ExcelService excelService;
	
	@GetMapping("/reports")
	public String reportTable(Model model) {
		List<ReportCategory> reports = orderDetailService.reportByCategory();
		model.addAttribute("reports", reports);
		return "/admin/reports/table";
	}

	@GetMapping("/reports/export")
	public void exportProduct(HttpServletResponse resp) throws IOException {
		List<ReportCategory> reportsCategory = orderDetailService.reportByCategory();
		excelService.exportReportsCategory(resp, reportsCategory);
	}
}
