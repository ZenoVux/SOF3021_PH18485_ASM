package sof3021.ph18485.beans;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordForm {
	
	private String token;

	@Length(min = 8, max = 255, message = "{Length.Account.password}")
	@NotEmpty(message = "{NotEmpty.Account.password}")
	private String password;
	
	private String rePassword;
}
