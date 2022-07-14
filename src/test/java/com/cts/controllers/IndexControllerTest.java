package com.cts.controllers;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.controller.IndexController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
/*
 * @Author Rasheem 
 * This class is to test Index Controller
 */
public class IndexControllerTest {

	@InjectMocks
	IndexController indexController;
	@Autowired
	private MockMvc mvc;
    
	/* Test for invalid URL for IndexManager page*/
	@Test
	public void testIndexManagerPage_WrongUrl() {

		try {
			mvc.perform(get("/managerwrong")).andExpect(status().is(404));

		} catch (Exception exception) {
			fail("Exception");
		}

	}
    
	/* Test for invalid URL for IndexAdmin page*/
	@Test
	public void testIndexAdminPage_WrongUrl() {

		try {
			mvc.perform(get("/adminwrong")).andExpect(status().is(404));

		} catch (Exception exception) {
			fail("Exception");
		}

	}
    
	/* Test for invalid URL for IndexUser page*/
	@Test
	public void testIndexUserPage_WrongUrl() {

		try {
			mvc.perform(get("/userwrong")).andExpect(status().is(404));

		} catch (Exception exception) {
			fail("Exception");
		}

	}
    
	/* Test for invalid URL for User Registration page*/
	@Test
	public void testRegUserPage_WrongUrl() {

		try {
			mvc.perform(get("/regUserwrong")).andExpect(status().is(404));

		} catch (Exception exception) {
			fail("Exception");
		}

	}

	/* Test for invalid URL for Manager Registration page*/
	@Test
	public void testRegManagerPage_WrongUrl() {

		try {
			mvc.perform(get("/regManagerwrong")).andExpect(status().is(404));

		} catch (Exception exception) {
			fail("Exception");
		}

	}

	/* Test for invalid URL for User Login page*/
	@Test
	public void testLoginUserPage_WrongUrl() {

		try {
			mvc.perform(get("/loginUserwrong")).andExpect(status().is(404));

		} catch (Exception exception) {
			fail("Exception");
		}

	}

	/* Test for invalid URL for Manager Login page*/
	@Test
	public void testLoginManagerPage_WrongUrl() {

		try {
			mvc.perform(get("/loginManagerwrong")).andExpect(status().is(404));

		} catch (Exception exception) {
			fail("Exception");
		}

	}

	/* Test for invalid URL for Admin Login page*/
	@Test
	public void testLoginAdminPage_WrongUrl() {

		try {
			mvc.perform(get("/loginAdminwrong")).andExpect(status().is(404));

		} catch (Exception exception) {
			fail("Exception");
		}

	}
}
