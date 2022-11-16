package sof3021.ph18485.beans;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sof3021.ph18485.entities.Account;

@Getter
@Setter
@ToString
public class ChangePasswordForm {

	private String oldPassword;
	
	@Length(min = 8, max = 255, message = "{Length.Account.password}")
	@NotEmpty(message = "{NotEmpty.Account.password}")
	private String newPassword;
	
	private String rePassword;
	
}
