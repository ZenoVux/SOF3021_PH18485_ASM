package sof3021.ph18485.beans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import sof3021.ph18485.entities.Account;

@Getter
@Setter
public class AdminOrderForm {
	
	private Integer id;

	@NotNull(message = "Không được để trống")
	private Account account;
	
	@NotEmpty(message = "{NotEmpty.Order.address}")
	private String address;
	
}
