package sof3021.ph18485.services;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jobrunr.jobs.annotations.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import sof3021.ph18485.entities.Account;
import sof3021.ph18485.entities.Category;
import sof3021.ph18485.entities.Order;
import sof3021.ph18485.entities.Product;
import sof3021.ph18485.entities.ReportCategory;
import sof3021.ph18485.repositoties.CategoryRepository;
import sof3021.ph18485.repositoties.ProductRepository;

@Service
public class ExcelService {

	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private ProductRepository productRepo;

	@Job(name = "Import data from excel file")
	public void importExcel(File file) throws EncryptedDocumentException, IOException {
		Workbook workbook = WorkbookFactory.create(file);
		for (Sheet sheet : workbook) {
			String sheetName = sheet.getSheetName();
			if (sheetName.equalsIgnoreCase("products")) {
				this.readProductsSheet(sheet);
				continue;
			}
			if (sheetName.equalsIgnoreCase("categories")) {
				this.readCategoriesSheet(sheet);
				continue;
			}
		}
		workbook.close();
	}

	public void readProductsSheet(Sheet sheet) {
		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			Row row = sheet.getRow(i);
			Product product = new Product();
			if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.NUMERIC) {
				int id = (int) row.getCell(0).getNumericCellValue();
				product.setId(id);
			}
			if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
				String name = row.getCell(1).getStringCellValue();
				product.setName(name);
			} else {
				continue;
			}
			if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
				String image = row.getCell(2).getStringCellValue();
				product.setImage(image);
			}
			if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.NUMERIC) {
				double price = row.getCell(3).getNumericCellValue();
				product.setPrice(price);
			} else {
				continue;
			}
			if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.NUMERIC) {
				double discount = row.getCell(4).getNumericCellValue();
				product.setDiscount(discount);
			} else {
				continue;
			}
			if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.NUMERIC) {
				int quantity = (int) row.getCell(5).getNumericCellValue();
				product.setQuantity(quantity);
			} else {
				continue;
			}
			if (row.getCell(6) != null && row.getCell(6).getCellType() == CellType.STRING) {
				String description = row.getCell(6).getStringCellValue();
				product.setDescription(description);
			} else {
				continue;
			}
			if (row.getCell(7) != null && row.getCell(7).getCellType() == CellType.NUMERIC) {
				Date createdDate = row.getCell(7).getDateCellValue();
				product.setCreatedDate(createdDate);
			}
			if (row.getCell(8) != null && row.getCell(8).getCellType() == CellType.NUMERIC) {
				int categoryId = (int) row.getCell(8).getNumericCellValue();
				Category category = new Category();
				category.setId(categoryId);
				product.setCategory(category);
			} else {
				continue;
			}
			if (row.getCell(9) != null && row.getCell(9).getCellType() == CellType.NUMERIC) {
				int available = (int) row.getCell(9).getNumericCellValue();
				product.setAvailable(available);
			} else {
				continue;
			}
//			System.out.println(product);
			productRepo.save(product);
		}
	}

	public void readCategoriesSheet(Sheet sheet) {
		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			Row row = sheet.getRow(i);
			Category category = new Category();
			if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.NUMERIC) {
				int id = (int) row.getCell(0).getNumericCellValue();
				category.setId(id);
			}
			if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
				String name = row.getCell(1).getStringCellValue();
				category.setName(name);
			} else {
				continue;
			}
//			System.out.println(category);
			categoryRepo.save(category);
		}

	}

	public void exportAccounts(HttpServletResponse resp, List<Account> accounts) throws IOException {
		resp.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=accounts_" + currentDateTime + ".xlsx";
		resp.setHeader(headerKey, headerValue);

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Account");
		String[] headers = new String[] { "Id", "Họ tên", "Tên đăng nhập", "Email", "Ảnh", "Trạng thái", "Vai trò" };
		this.createHeader(workbook, sheet, headers);
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Account account : accounts) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			this.createCell(sheet, row, columnCount++, account.getId(), style);
			this.createCell(sheet, row, columnCount++, account.getFullname(), style);
			this.createCell(sheet, row, columnCount++, account.getUsername(), style);
			this.createCell(sheet, row, columnCount++, account.getEmail(), style);
			this.createCell(sheet, row, columnCount++, account.getPhoto(), style);
			this.createCell(sheet, row, columnCount++, account.getActivated(), style);
			this.createCell(sheet, row, columnCount++, account.getRole().toString(), style);
		}
		workbook.write(resp.getOutputStream());
		workbook.close();

	}

	public void exportCategories(HttpServletResponse resp, List<Category> categories) throws IOException {
		resp.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=categories_" + currentDateTime + ".xlsx";
		resp.setHeader(headerKey, headerValue);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Category");
		String[] headers = new String[] { "Id", "Tên danh mục" };
		this.createHeader(workbook, sheet, headers);
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Category category : categories) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			this.createCell(sheet, row, columnCount++, category.getId(), style);
			this.createCell(sheet, row, columnCount++, category.getName(), style);
		}
		workbook.write(resp.getOutputStream());
		workbook.close();
	}

	public void exportProducts(HttpServletResponse resp, List<Product> products) throws IOException {
		resp.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=products_" + currentDateTime + ".xlsx";
		resp.setHeader(headerKey, headerValue);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Product");
		String[] headers = new String[] { "Id", "Tên sản phẩm", "Hình ảnh", "Giá", "Giảm giá", "Số lượng", "Mô tả",
				"Ngày tạo", "Danh mục", "Trạng thái" };
		this.createHeader(workbook, sheet, headers);
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Product product : products) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			this.createCell(sheet, row, columnCount++, product.getId(), style);
			this.createCell(sheet, row, columnCount++, product.getName(), style);
			this.createCell(sheet, row, columnCount++, product.getImage(), style);
			this.createCell(sheet, row, columnCount++, product.getPrice(), style);
			this.createCell(sheet, row, columnCount++, product.getDiscount(), style);
			this.createCell(sheet, row, columnCount++, product.getQuantity(), style);
			this.createCell(sheet, row, columnCount++, product.getDescription(), style);
			this.createCell(sheet, row, columnCount++, product.getCreatedDate(), style);
			this.createCell(sheet, row, columnCount++, product.getCategory().getName(), style);
			this.createCell(sheet, row, columnCount++, product.getAvailable(), style);
		}
		workbook.write(resp.getOutputStream());
		workbook.close();

	}

	public void exportOrders(HttpServletResponse resp, List<Order> orders) throws IOException {
		resp.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=orders_" + currentDateTime + ".xlsx";
		resp.setHeader(headerKey, headerValue);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Order");
		String[] headers = new String[] { "Id", "Khách hàng", "Địa chỉ" };
		this.createHeader(workbook, sheet, headers);
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Order order : orders) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			this.createCell(sheet, row, columnCount++, order.getId(), style);
			this.createCell(sheet, row, columnCount++, order.getAccount().getFullname(), style);
			this.createCell(sheet, row, columnCount++, order.getAddress(), style);
		}
		workbook.write(resp.getOutputStream());
		workbook.close();
	}

	public void exportReportsCategory(HttpServletResponse resp, List<ReportCategory> reportsCategory)
			throws IOException {
		resp.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=reports_category_" + currentDateTime + ".xlsx";
		resp.setHeader(headerKey, headerValue);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Report Category");
		String[] headers = new String[] { "Danh mục", "Doanh thu", "Số lượng" };
		// tạo phần header bảng
		this.createHeader(workbook, sheet, headers);
		int rowCount = 1;
		// set style cho body
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		// tạo phần body bảng
		for (ReportCategory reportCategory : reportsCategory) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			this.createCell(sheet, row, columnCount++, reportCategory.getLoai().getName(), style);
			this.createCell(sheet, row, columnCount++, reportCategory.getDoanhThu(), style);
			this.createCell(sheet, row, columnCount++, reportCategory.getSoLuong(), style);
		}
		workbook.write(resp.getOutputStream());
		workbook.close();
	}

	private void createHeader(XSSFWorkbook workbook, XSSFSheet sheet, String[] headers) {
		Row row = sheet.createRow(0);
		// set style cho header
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		font.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(font);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		int colCount = 0;
		for (String header : headers) {
			this.createCell(sheet, row, colCount++, header, style);
		}
	}

	private void createCell(XSSFSheet sheet, Row row, int columnCount, Object value, CellStyle style) {
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if (value instanceof Date) {
			cell.setCellValue((Date) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

}
