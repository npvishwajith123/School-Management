package com.np.school.management.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeesManagement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int feeId;
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER,
			   cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="studentId")
	private StudentDetails student;
	private Double amountDue;
	private Date dueDate;
	private String paymentStatus;
}









