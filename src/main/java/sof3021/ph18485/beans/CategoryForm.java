package sof3021.ph18485.beans;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sof3021.ph18485.entities.Category;

@Getter
@Setter
@ToString
public class CategoryForm {
	
	private Integer id;
	
	@NotEmpty(message = "{NotEmpty.Category.name}")
	private String name;
	
}
