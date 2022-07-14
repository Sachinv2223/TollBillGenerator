package com.cts.repositories;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
/*
 * @Author Rasheem 
 * This class is to test Bank Repository
 */
public class BankRepositoryTest {

	@Autowired
	BankRepository bankRepository;

	/* Test for passed parameter not to be null */
	@Test
	public void set() {
		assertNotNull(bankRepository);
	}

}
