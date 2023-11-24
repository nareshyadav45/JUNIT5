package com.nt.mockito;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.nt.dao.LoginDao;
import com.nt.mockito.service.LoggingServiceImpl;
import com.nt.mockito.service.LoginService;

public class TestLoginManagementService {

	
	private static LoginDao dao;
	private static LoginService service;
	
	@BeforeAll
	public static void setUpOnce() {
		dao=Mockito.mock(LoginDao.class);
		System.out.println(dao.getClass()+""+Arrays.toString(dao.getClass().getInterfaces()));
		service=new LoggingServiceImpl(dao);
	}
	
	@AfterAll
	public static void clearOnce() {
		dao=null;
		service=null;
	}
	
	@Test
	//@Disabled
	public void testWithValidCredentials() {
		//stub -->providing some functionality 
		Mockito.when(dao.authenticate("naresh","yadav")).thenReturn(1);
		boolean login = service.login("naresh","yadav");
		Assertions.assertTrue(login);
	}
	
	@Test
	public void testWithInvalidCredentail() {
		//stub
		Mockito.when(dao.authenticate("naresh", "chinthala")).thenReturn(0);
		boolean login = service.login("naresh", "chinthala");
		Assertions.assertFalse(login);
	}
	
	@Test
	public void testWithNoCredentilas() {
		Assertions.assertThrows(IllegalArgumentException.class,()->service.login("", ""));
	}
	
	@Test
	public void testSpyRegister() {
		
		LoginDao spy = Mockito.spy(LoginDao.class);
		LoginService serviceImpl=new LoggingServiceImpl(spy);
		serviceImpl.registerUser("naresh", "admin");
		//serviceImpl.registerUser("naresh", "adminmclmx");
		serviceImpl.registerUser("sanghamesh", "visitor");
		serviceImpl.registerUser("saurabh", "");
		
		Mockito.verify(spy,Mockito.times(1)).addUser("naresh", "admin");
		//Mockito.verify(spy,Mockito.).addUser("naresh", "adminmclmx");
		Mockito.verify(spy,Mockito.times(0)).addUser("sanghamesh", "visitor");
		Mockito.verify(spy,Mockito.never()).addUser("saurabh", "");
	}
}
