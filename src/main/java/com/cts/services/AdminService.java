package com.cts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.entities.Admin;
import com.cts.repositories.AdminRepository;

/*@author Nayana
Service class for Admin entity class*/
@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminRepository arepo;


	public boolean validateAdmin(String auserName, String apassword) {
		Admin admin = arepo.findByAuserNameAndApassword(auserName, apassword);
		if (admin == null) {
			return false;
		} else {
			return true;
		}
	}

}
