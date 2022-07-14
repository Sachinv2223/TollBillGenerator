package com.cts.repositories;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.cts.entities.Manager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
/*
 * @Author Rasheem 
 * This class is to test Manager Repository
 */
public class ManagerRepositoryTest {

	@Autowired
	ManagerRepository managerRepository;

	/* Test for passed parameter not to be null */
	@Test
	public void set() {
		assertNotNull(managerRepository);
	}

	/* Test for Manager login validation */
	@Test
	public void testFindByMuserNameAndMpassword() {
		try {
			Manager manager = managerRepository.findByMuserNameAndMpassword("Manager1", "Manager@30");
			assertNull(manager);
		} catch (Exception e) {
			// assertEquals(expected, actual);
		}
	}

}
