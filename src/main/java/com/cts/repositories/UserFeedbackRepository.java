package com.cts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entities.UserFeedback;


// Repository for user feedback*/
public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Integer> {

}
