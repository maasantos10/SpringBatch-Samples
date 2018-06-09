package com.mas.partitioner.service;

import com.mas.partitioner.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {

	@Override
	public void insertDataIntoUserTable() throws Exception {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		
		userDaoImpl.insertDataIntoUserTable();
	}
	
}
