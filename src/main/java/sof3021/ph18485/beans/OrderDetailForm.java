package sof3021.ph18485.beans;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import sof3021.ph18485.entities.Order;
import sof3021.ph18485.entities.Product;

@Getter
@Setter
public class OrderDetailForm {

	private Integer id;

	@NotNull(message = "{NotNull.OrderDetail.order}")
	private Order order;

	@NotNull(message = "{NotNull.OrderDetail.product}")
	private Product product;

	@Min(value = 1, message = "{Min.OrderDetail.price}")
	@NotNull(message = "{NotNull.OrderDetail.price}")
	private Double price;

	@Min(value = 1, message = "{Min.OrderDetail.quantity}")
	@NotNull(message = "{NotNull.OrderDetail.quantity}")
	private Integer quantity;

}
