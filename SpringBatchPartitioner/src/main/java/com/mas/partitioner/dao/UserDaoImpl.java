package com.mas.partitioner.dao;

import com.mas.partitioner.configuration.BatchDbConfiguration;
import com.mysql.jdbc.Connection;
import java.sql.Statement;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDaoImpl implements UserDao{

	public static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Override
	public void insertDataIntoUserTable() throws Exception {

		Connection dbConnection = null;
		Statement statement = null;
		BatchDbConfiguration dbConfiguration = new BatchDbConfiguration(); 

		try {

			dbConnection = (Connection) dbConfiguration.getDBConnection();
			statement = dbConnection.createStatement();
			
			Random generator = new Random();

			for (int i = 1; i <= 100; i++) {

				String insertTableSQL = "INSERT INTO USERS (ID, USER_LOGIN, USER_PASS, AGE) VALUES (':id',':name',':pass',':age')";

				insertTableSQL = insertTableSQL.replaceAll(":id", String.valueOf(i));
				insertTableSQL = insertTableSQL.replaceAll(":name", "user_" + i);
				insertTableSQL = insertTableSQL.replaceAll(":pass", "pass_" + i);

				//insertTableSQL = insertTableSQL.replaceAll(":age", String.valueOf(10 + (int) (Math.random() * 90)));
				insertTableSQL = insertTableSQL.replaceAll(":age", String.valueOf(generator.nextInt(100)));
				
				LOGGER.info(insertTableSQL);
				statement.addBatch(insertTableSQL);

			}
			statement.executeBatch();
			LOGGER.info("Record is inserted into USER table!");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}
}
