package com.np.school.management.entities;

import java.util.Date;

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
public class StaffDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int staffId;
	private String name;
	private String position;
	private String department;
	private Date hireDate;
}
