package sof3021.ph18485.beans;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sof3021.ph18485.entities.Category;
import sof3021.ph18485.entities.Product;

@Getter
@Setter
@ToString
public class ProductForm {

	private Integer id;

	@Length(max = 255, message = "{Length.Product.name}")
	@NotEmpty(message = "{NotEmpty.Product.name}")
	private String name;

	@Length(max = 255, message = "{Length.Product.description}")
	private String description;
	
	@Min(value = 1, message = "{Min.Product.price}")
	@NotNull(message = "{NotNull.Product.price}")
	private Double price;
	
	@DecimalMin(value = "0", message = "{DecimalMin.Product.discount}")
	@DecimalMax(value = "1", message = "{DecimalMax.Product.discount}")
	@NotNull(message = "{NotNull.Product.discount}")
	private Double discount;

	@Min(value = 1, message = "{Min.Product.quantity}")
	@NotNull(message = "{NotNull.Product.quantity}")
	private Integer quantity;
	
	@NotNull(message = "{NotNull.Product.available}")
	private Integer available;
	
	@NotNull(message = "{NotNull.Product.category}")
	private Category category;
	
	public Product copyAttributes(Product product) {
		if (product == null) {
			product = new Product();
		}
		product.setId(this.getId());
		product.setName(this.getName());
		product.setDescription(this.getDescription());
//		product.setImage(this.image.getOriginalFilename());
		product.setPrice(this.getPrice());
		product.setDiscount(this.getDiscount());
		product.setQuantity(this.getQuantity());
		product.setAvailable(this.getAvailable());
		product.setCategory(this.getCategory());
		return product;
	}
	
}
