package sof3021.ph18485.services;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {

	private final String UPLOAD_DIRECTORY = "D:/upload";

	@Autowired
	private HttpServletRequest req;
	@Autowired
	private HttpServletResponse resp;

	/**
	 * Lấy URL của trang web
	 * 
	 * @return giá trị URL của trang web
	 */
	public String setSiteURL() {
		String reqURL = req.getRequestURL().toString();
		return reqURL.replace(req.getServletPath(), "");
	}

	/**
	 * Đọc chuỗi giá trị của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public String getString(String name, String defaultValue) {
		String value = req.getParameter(name);
		if (value != null) {
			if (value.trim().isEmpty()) {
				return defaultValue;
			}
			return value;
		}
		return defaultValue;
	}

	/**
	 * Đọc số nguyên giá trị của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public int getInt(String name, int defaultValue) {
		String value = req.getParameter(name);
		if (value != null) {
			if (value.trim().isEmpty()) {
				return defaultValue;
			}
			try {
				return Integer.parseInt(value);
			} catch (Exception e) {
			}
		}
		return defaultValue;
	}

	/**
	 * Đọc số thực giá trị của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public double getDouble(String name, double defaultValue) {
		String value = req.getParameter(name);
		if (value != null) {
			if (value.trim().isEmpty()) {
				return defaultValue;
			}
			try {
				return Double.parseDouble(value);
			} catch (Exception e) {
			}
		}
		return defaultValue;
	}

	/**
	 * Đọc giá trị boolean của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public boolean getBoolean(String name, boolean defaultValue) {
		String value = req.getParameter(name);
		if (value != null) {
			if (value.trim().isEmpty()) {
				return defaultValue;
			}
			try {
				return Boolean.parseBoolean(value);
			} catch (Exception e) {
			}
		}
		return defaultValue;
	}

	/**
	 * Đọc giá trị thời gian của tham số
	 * 
	 * @param name    tên tham số
	 * @param pattern là định dạng thời gian
	 * @return giá trị tham số hoặc null nếu không tồn tại
	 * @throws RuntimeException lỗi sai định dạng
	 */
	public Direction getDirection(String name, Direction defaultValue) {
		String value = req.getParameter(name);
		if (value != null) {
			if (value.trim().isEmpty()) {
				return defaultValue;
			}
			try {
				return Direction.fromString(value);
			} catch (Exception e) {
			}
		}
		return defaultValue;
	}

	/**
	 * Đọc giá trị thời gian của tham số
	 * 
	 * @param name    tên tham số
	 * @param pattern là định dạng thời gian
	 * @return giá trị tham số hoặc null nếu không tồn tại
	 * @throws RuntimeException lỗi sai định dạng
	 */
	public Date getDate(String name, String pattern) {
		String value = req.getParameter(name);
		if (value != null) {
			if (value.trim().isEmpty()) {
				return null;
			}
			try {
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				return format.parse(value);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	/**
	 * Lưu file upload vào thư mục
	 * 
	 * @param file chứa file upload từ client
	 * @param path đường dẫn tính từ webroot
	 * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
	 * @throws RuntimeException lỗi lưu file
	 */
	public File save(MultipartFile file, String path) {
		if (!file.isEmpty()) {
			File dir = new File(UPLOAD_DIRECTORY + path);
			try {
				if (!dir.exists()) {
					Files.createDirectory(dir.toPath());
				}
				File newFile = new File(dir, file.getOriginalFilename());
				file.transferTo(newFile);
				return newFile;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

}