package com.cts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.entities.User;
import com.cts.repositories.UserRepository;

/*@author Sachin
Service class for User entity class*/
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repo;

	public void saveUser(User user) {
		repo.save(user);
	}
	
	public boolean validateUser(String uuserName, String upassword) {
		User user = repo.findByUuserNameAndUpassword(uuserName, upassword);
		if(user == null) {
			return false;
		}
		else {
			return true;
		}
	}
}
