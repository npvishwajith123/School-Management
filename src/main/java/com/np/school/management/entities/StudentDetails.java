package com.np.school.management.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int studentId;
	private String name;
	private int age;
	private String address;
	private String classEnrolled;
	private Date admissionDate;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, 
			   cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="facultyId")
	private FacultyDetails faculty;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<FeesManagement> fees;
}
