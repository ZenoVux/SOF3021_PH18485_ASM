package sof3021.ph18485.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportCategory implements Serializable {
	
	@Id
	private Category loai;
	private Double doanhThu;
	private Long soLuong;
	
}
