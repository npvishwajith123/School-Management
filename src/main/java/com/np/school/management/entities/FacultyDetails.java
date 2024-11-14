package com.np.school.management.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int facultyId;
	private String name;
	private String department;
	private String designation;
	private Date hireDate;
	private int experience;
	@JsonManagedReference
	@OneToMany(mappedBy = "faculty", 
			   cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	private List<StudentDetails> students;
}
