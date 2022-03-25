package com.sapient.aem.dao;


import com.sapient.aem.model.User;

public interface UserDAO {
	public abstract Boolean isValidUser(String userName,String password) throws Exception;
	public abstract Integer addUser(User user) throws Exception;
	public abstract Integer getUserId(User user) throws Exception;
}
