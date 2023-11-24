package com.nt.dao;

public interface LoginDao {
public int authenticate(String useranme,String password);
public int addUser(String userName,String role);
}
