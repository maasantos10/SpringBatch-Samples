package com.mas.partitioner.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mas.partitioner.processor.UserItemProcessor;

public class BatchDbConfiguration {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/db_batch_mas";
	private static final String DB_USER = "admin";
	private static final String DB_PASSWORD = "admin";
	
	public static final Logger LOGGER = LoggerFactory.getLogger(UserItemProcessor.class);
	
	public static Connection getDBConnection() {
		
		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			LOGGER.error(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			LOGGER.error(e.getMessage());

		}

		return dbConnection;

	}
	
}
