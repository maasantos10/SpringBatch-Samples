package com.mas.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * 
 * @author Marcos Santos
 * description: Spring Batch sample with aggregation of values from one csv file 
 * and multithreading 
 *
 */

@SpringBootApplication
public class ApplicationBatch {

	public static void main (String[] args) throws Exception {
		SpringApplication.run(ApplicationBatch.class, args);
	}
	
}
