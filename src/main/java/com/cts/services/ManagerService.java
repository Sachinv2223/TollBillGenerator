package com.cts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.entities.Manager;
import com.cts.repositories.ManagerRepository;

/*@author Anjusha
Service class forManager entity class*/
@Service
@Transactional
public class ManagerService {

	@Autowired
	private ManagerRepository mrepo;

	public void saveManager(Manager manager) {
		mrepo.save(manager);
	}

	public boolean validateManager(String muserName, String mpassword) {
		Manager manager = mrepo.findByMuserNameAndMpassword(muserName, mpassword);
		if (manager == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean approvedManager(String muserName, String mpassword) {
		Manager manager = mrepo.findByMuserNameAndMpassword(muserName, mpassword);
		if (manager.getMapproval().equalsIgnoreCase("NOT APPROVED")) {
			return false;
		} else {
			return true;
		}

	}

	public List<Manager> showAllManagers() {
		return mrepo.findAll();
	}
}
