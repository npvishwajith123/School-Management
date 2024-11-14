package com.np.school.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.np.school.management.entities.FeesManagement;

public interface FeesManagementRepository extends JpaRepository<FeesManagement, Integer>{

	@Transactional
	@Modifying
	@Query("Update FeesManagement f Set f.paymentStatus = :#{#feeDetails.paymentStatus} Where f.feeId =:#{#feeDetails.feeId}")
	void updateFeeStatus(FeesManagement feeDetails);
	
	/**
	 * If we are passing the fields to update individually as a separate field instead of passing an 
	 * object, we can use the following query to update the details
	 * 
	 * @Query("Update FeesManagement f Set f.paymentStatus = :paymentStatus Where f.feeId =:feeId")
	 * void updateFeeStatus(String paymentStatus, int feeId);
	 * 
	 */

}
