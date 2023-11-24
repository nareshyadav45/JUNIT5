package com.nt.mockito;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.nt.dao.LoginDao;
import com.nt.mockito.service.LoggingServiceImpl;
import com.nt.mockito.service.LoginService;

public class TestLoginManagementAnno {

	@InjectMocks
	private LoggingServiceImpl service;

	@Mock
	private LoginDao dao;

	@Spy
	private LoginDao spy;

	public TestLoginManagementAnno() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	// @Disabled
	public void testWithValidCredentials() {
		// stub -->providing some functionality
		// Mockito.when(dao.authenticate("naresh","yadav")).thenReturn(1);
		BDDMockito.given(dao.authenticate("naresh", "yadav")).willReturn(1);
		boolean login = service.login("naresh", "yadav");
		Assertions.assertTrue(login);

	}

	@Test
	public void testWithInvalidCredentail() {
		// stub
		Mockito.when(dao.authenticate("naresh", "chinthala")).thenReturn(0);
		boolean login = service.login("naresh", "chinthala");
		Assertions.assertFalse(login);
	}

	@Test
	public void testWithNoCredentilas() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> service.login("", ""));
	}

	@Test
	public void testSpyRegister() {

		LoginDao spy = Mockito.spy(LoginDao.class);
		LoginService service = new LoggingServiceImpl(spy);
		service.registerUser("naresh", "admin");
		// serviceImpl.registerUser("naresh", "adminmclmx");
		service.registerUser("sanghamesh", "visitor");
		service.registerUser("saurabh", "");

		Mockito.verify(spy, Mockito.times(1)).addUser("naresh", "admin");
		// Mockito.verify(spy,Mockito.).addUser("naresh", "adminmclmx");
		Mockito.verify(spy, Mockito.times(0)).addUser("sanghamesh", "visitor");
		Mockito.verify(spy, Mockito.never()).addUser("saurabh", "");
	}

}
