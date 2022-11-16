package sof3021.ph18485.services;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import sof3021.ph18485.entities.Order;
import sof3021.ph18485.entities.OrderDetail;

@Service
public class PdfService {

	public void OrderPdfExport(HttpServletResponse resp, Order order) throws DocumentException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=order_" + order.getId() + "_" + currentDateTime + ".pdf";
		resp.setHeader(headerKey, headerValue);
		//
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, resp.getOutputStream());
		document.open();

		Font infoFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		infoFont.setColor(Color.BLACK);
		Font shopNameFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
		shopNameFont.setSize(14);
		shopNameFont.setColor(Color.BLACK);
		// tên shop
		Paragraph shopName = new Paragraph("PH18485 SHOP", shopNameFont);
		shopName.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(shopName);
		// địa chỉ shop
		Paragraph addressShop = new Paragraph("Dia chi: Tien Hai, Thai Binh", infoFont);
		addressShop.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(addressShop);
		// số điện thoại shop
		Paragraph phoneNumber = new Paragraph("SDT: 0366963693", infoFont);
		phoneNumber.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(phoneNumber);

		// tiêu đề
		Font titleFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
		titleFont.setSize(20);
		titleFont.setColor(Color.BLACK);

		Paragraph title = new Paragraph("HOA DON BAN HANG", titleFont);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);
		// id hoá đơn
		Paragraph orderId = new Paragraph("So: " + order.getId(), infoFont);
		orderId.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(orderId);
		// tên khách hàng
		Paragraph fullname = new Paragraph("Khach hang: " + order.getAccount().getFullname(), infoFont);
		fullname.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(fullname);
		// địa chỉ khách hàng
		Paragraph address = new Paragraph("Dia chi: " + order.getAddress(), infoFont);
		address.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(address);

		// tạo bảng
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1f, 4f, 2.5f, 1.5f, 2.5f });
		table.setSpacingBefore(10);
		// tạo phần đầu bảng hoá đơn chi tiết
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.WHITE);
		cell.setPadding(5);

		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		headerFont.setColor(Color.BLACK);

		cell.setPhrase(new Phrase("STT", headerFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Ten san pham", headerFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Don gia", headerFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("So luong", headerFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Thanh tien", headerFont));
		table.addCell(cell);

		// tạo phần thân bảng hoá đơn chi tiết
		int count = 1;
		double amount = 0;
		DecimalFormat df = new DecimalFormat("###,###,###");
		for (OrderDetail orderDetail : order.getOrderDetails()) {
			PdfPCell cellRow = new PdfPCell();
			cellRow.setPadding(5);
			
			cellRow.setPhrase(new Phrase(String.valueOf(count)));
			table.addCell(cellRow);
			cellRow.setPhrase(new Phrase(orderDetail.getProduct().getName()));
			table.addCell(cellRow);
			cellRow.setPhrase(new Phrase(df.format(orderDetail.getPrice())));
			table.addCell(cellRow);
			cellRow.setPhrase(new Phrase(String.valueOf(orderDetail.getQuantity())));
			table.addCell(cellRow);
			cellRow.setPhrase(new Phrase(df.format(orderDetail.getPrice() * orderDetail.getQuantity())));
			table.addCell(cellRow);
			
			amount += (orderDetail.getPrice() * orderDetail.getQuantity());
			count++;
		}
		document.add(table);
		
		Paragraph totalMoney = new Paragraph("Tong tien: " + df.format(amount), headerFont);
		totalMoney.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(totalMoney);
		
		String date = "Ngay " + order.getCreatedDate().getDate() + " thang " + (order.getCreatedDate().getMonth() + 1)
				+ " nam " + (order.getCreatedDate().getYear() + 1900);
		Paragraph createdDate = new Paragraph(date, infoFont);
		createdDate.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(createdDate);

		document.close();

	}
}
