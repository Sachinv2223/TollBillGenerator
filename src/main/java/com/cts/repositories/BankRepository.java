package com.cts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entities.BankDetails;

// Repository for Bank details
public interface BankRepository extends JpaRepository<BankDetails, Integer> {

	// repository method for getting bank details by bank username and bank password

	public BankDetails findByBuserNameAndBpassword(String buserName, String bpassword);

}
