package sof3021.ph18485.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sof3021.ph18485.entities.Account;
import sof3021.ph18485.entities.AccountRole;

@Getter
@Setter
@ToString
public class AccountForm {

	private Integer id;
	
	@Length(max = 255, message = "{Length.Account.username}")
	@NotEmpty(message = "{NotEmpty.Account.username}")
	private String username;
	
	@Length(max = 255, message = "{Length.Account.fullname}")
	@NotEmpty(message = "{NotEmpty.Account.fullname}")
	private String fullname;

	@Length(min = 8, max = 255, message = "{Length.Account.password}")
	private String password;
	
	private String rePassword;

	@Length(max = 255, message = "{Length.Account.email}")
	@NotEmpty(message = "{NotEmpty.Account.email}")
	@Email(message = "{Email.Account.email}")
	private String email;
	
	@NotNull
	private Integer activated;
	
	@NotNull
	private AccountRole role;
	
}
