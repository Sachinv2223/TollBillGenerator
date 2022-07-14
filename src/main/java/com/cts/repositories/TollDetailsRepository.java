package com.cts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entities.TollDetails;


// Repository for toll details*/
public interface TollDetailsRepository extends JpaRepository<TollDetails, Integer> {
	// Repository method for getting toll details by using From Location, To
	// location and vehicle type

	public TollDetails findByFromLocationAndToLocationAndVechtype(String fromLocation, String toLocation,
			String vechtype);

}
