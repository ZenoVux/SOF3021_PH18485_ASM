package sof3021.ph18485.beans;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

	@NotEmpty(message = "{NotEmpty.Account.username}")
	private String username;

	@NotEmpty(message = "{NotEmpty.Account.password}")
	private String password;
}
