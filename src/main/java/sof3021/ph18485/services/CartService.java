package sof3021.ph18485.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import sof3021.ph18485.entities.Account;
import sof3021.ph18485.entities.Order;
import sof3021.ph18485.entities.OrderDetail;
import sof3021.ph18485.entities.Product;
import sof3021.ph18485.repositoties.ProductRepository;

@SessionScope
@Service
public class CartService {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private ProductService productService;

	private Map<Integer, Product> map;

	public CartService() {
		this.map = new HashMap<>();
	}

	public Product add(Integer id) {
		Product product = this.map.get(id);
		// kiểm tra id sản phẩm đã tồn tại hay chưa
		if (product != null) {
			// nếu tồn tại thì số lượng + 1
			this.update(id, product.getQuantity() + 1);
		} else {
			// ngược lại thêm sp vào giỏ hàng
			product = productService.findById(id);
			if (product != null) {
				if (product.getQuantity() < 0) {
					return product;
				}
				if (product.getDiscount() > 0) {
					double priceDiscount = product.getPrice() * product.getDiscount();
					product.setPrice(product.getPrice() - priceDiscount);
				}
				product.setQuantity(1);
				this.map.put(id, product);
			}
		}
		return product;
	}

	public void remove(Integer id) {
		this.map.remove(id);
	}

	public Product update(Integer id, int quantity) {
		Product product = this.map.get(id);
		if (product != null) {
			if (quantity <= 0) {
				product.setQuantity(1);
			} else {
				product.setQuantity(quantity);
			}
		}
		return product;
	}

	public void clear() {
		this.map.clear();
	}

	public Collection<Product> getItems() {
		return this.map.values();
	}

	public int getCount() {
		return this.getItems().size();
	}

	public int getQuantity() {
		int count = 0;
		for (Product item : this.map.values()) {
			count += item.getQuantity();
		}
		return count;
	}

	public double getAmount() {
		double amount = 0;
		for (Product item : this.map.values()) {
			amount += (item.getPrice() * item.getQuantity());
		}
		return amount;
	}

	public List<OrderDetail> toOrderDetails(Order order) {
		List<OrderDetail> orderDetails = new ArrayList<>();
		for (Product product : this.getItems()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(order);
			orderDetail.setProduct(product);
			orderDetail.setPrice(product.getPrice());
			orderDetail.setQuantity(product.getQuantity());
			orderDetails.add(orderDetail);
		}
		return orderDetails;
	}

	public void updateQuantityProducts(List<OrderDetail> orderDetails) {
		List<Product> products = new ArrayList<>();
		for (OrderDetail orderDetail : orderDetails) {
			Product product = productService.findById(orderDetail.getProduct().getId());
			if (product != null) {
				int quantity = product.getQuantity();
				int quantityUse = orderDetail.getQuantity();
				if ((quantity - quantityUse) >= 0) {
					product.setQuantity(quantity - quantityUse);
				} else {
					product.setQuantity(0);
				}
				products.add(product);
			}
		}
		productRepo.saveAll(products);
	}

}
