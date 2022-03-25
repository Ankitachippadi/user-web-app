package com.sapient.aem.service;



import com.sapient.aem.Exception.UserException;
import com.sapient.aem.model.User;

public interface UserService {
	public abstract Boolean isValidUser(String userName,String password) throws UserException;
	public abstract Integer addUser(User user) throws UserException;
}