package com.sapient.aem.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import com.sapient.aem.model.User;

public class UserDaoImpl implements UserDAO{

	public Boolean isValidUser1(String userName, String password ) throws Exception{
		String sql="select * from registered_users where user_name=? and password=? ";
		Connection connection=null;
		try {
			Context envContext = (Context) new InitialContext().lookup("java:comp/env");		
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/emsDB");
			connection= dataSource.getConnection();
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
		
			ResultSet resultSet= preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			connection.close();
		}
		
	}
	
	//method return userid of the new user record
	@Override
	public Integer addUser(User user) throws Exception{
		String sql="insert into registered_users(user_name,password ) values(?,?)";
		Connection connection=null;
		try {
			Context envContext = (Context) new InitialContext().lookup("java:comp/env");		
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/emsDB");
			connection= dataSource.getConnection();
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());
		
			int n= preparedStatement.executeUpdate();
			if(n>0) {
				return this.getUserId(user);
			}else {
				return null;
			}
		}catch(Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			connection.close();
		}
		
	}

	@Override
	public Integer getUserId(User user) throws Exception {
		String sql="select user_id from registered_users where user_name=? and password=? ";
		Connection connection=null;
		try {
			Context envContext = (Context) new InitialContext().lookup("java:comp/env");		
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/emsDB");
			connection= dataSource.getConnection();
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());
			
			ResultSet resultSet= preparedStatement.executeQuery();
			if(resultSet.next()) {				
				return resultSet.getInt("user_id");
			}else {
				return null;
			}
		}catch(Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			connection.close();
		}
		
	}

	@Override
	public Boolean isValidUser(String userName, String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
