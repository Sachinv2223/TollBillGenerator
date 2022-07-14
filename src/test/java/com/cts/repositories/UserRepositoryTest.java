package com.cts.repositories;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.cts.entities.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
/*
 * @Author Rasheem 
 * This class is to test User Repository
 */
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	/* Test for passed parameter not to be null */
	@Test
	public void set() {
		assertNotNull(userRepository);
	}

	/* Test for User login validation */
	@Test
	public void testFindByUuserNameAndUpassword() {
		try {
			User user = userRepository.findByUuserNameAndUpassword("Nayana", "Nayana@23");
			assertNull(user);
		} catch (Exception e) {
			// assertEquals(expected, actual);
		}
	}

}
