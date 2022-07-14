package com.cts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.entities.TollDetails;
import com.cts.repositories.TollDetailsRepository;

/*@author Rasheem
Service class for TollDetails entity class*/
@Service
@Transactional
public class TollDetailsService {

	@Autowired
	private TollDetailsRepository repo;

	public void saveTollDetail(TollDetails tollDetails) {
		repo.save(tollDetails);
	}

	public List<TollDetails> showAllTollDetails() {
		return repo.findAll();
	}
}
