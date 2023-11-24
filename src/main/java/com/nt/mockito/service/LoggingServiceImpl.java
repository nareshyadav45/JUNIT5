package com.nt.mockito.service;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import com.nt.dao.LoginDao;

public class LoggingServiceImpl implements LoginService{

	
	private LoginDao loginDao;
	
	public LoggingServiceImpl(LoginDao dao) {
		this.loginDao=dao;
	}
	
	
	@Override
	public boolean login(String username, String password) {
		if(username.equals("")||password.equals(""))
			throw new IllegalArgumentException("Empty Credentials please check");
		
		int authenticate = loginDao.authenticate(username, password);
		System.out.println("service login method count"+authenticate);
		Predicate<Integer> i=i1-> (i1==0)?false:true;
		return i.test(authenticate);
	}


	@Override
	public String registerUser(String username, String role) {
		if(!role.equalsIgnoreCase("")&& !role.equalsIgnoreCase("visitor")) {
			this.loginDao.addUser(username, role);
		return "user added";
		}
		else {
		return "user can't add";
		}
	}

}
