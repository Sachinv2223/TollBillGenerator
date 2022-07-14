package com.cts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entities.User;


// Repository class for User entity class*/
public interface UserRepository extends JpaRepository<User, Integer> {
    //Repository method for getting user by username and password
	public User findByUuserNameAndUpassword(String uuserName, String upassword);

}
