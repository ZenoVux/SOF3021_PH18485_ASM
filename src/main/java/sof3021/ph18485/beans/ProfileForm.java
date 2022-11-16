package sof3021.ph18485.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileForm {

	@Length(max = 255, message = "{Length.Account.fullname}")
	@NotEmpty(message = "{NotEmpty.Account.fullname}")
	private String fullname;

	@Length(max = 255, message = "{Length.Account.email}")
	@NotEmpty(message = "{NotEmpty.Account.email}")
	@Email(message = "{Email.Account.email}")
	private String email;
	
}
