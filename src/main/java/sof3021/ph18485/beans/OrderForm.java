package sof3021.ph18485.beans;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm {
	
	@NotEmpty(message = "{NotEmpty.Order.address}")
	private String address;

}
