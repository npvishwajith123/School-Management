package com.np.school.management.entities;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDatabase {
	
	public interface Views {
	    interface BasicView {}
	    interface DetailedView extends BasicView {}
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(Views.BasicView.class)
	private int adminId;
	@JsonView(Views.BasicView.class)
	private String name;
	@JsonView(Views.BasicView.class)
	private String role;
	@JsonView(Views.BasicView.class)
	private String contactNumber;
	@JsonView(Views.DetailedView.class)
	private String office;
	@JsonView(Views.DetailedView.class)
	private String email;
	@JsonView(Views.DetailedView.class)
	private String emergencyContact;
	


}


