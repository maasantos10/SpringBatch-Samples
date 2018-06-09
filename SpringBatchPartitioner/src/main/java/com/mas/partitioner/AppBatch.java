package com.mas.partitioner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppBatch {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AppBatch.class);
	
	public static void main(String[] args) {

		AppBatch appBatch = new AppBatch();
		appBatch.runBatch();

	}

	private void runBatch() {
		
		String[] springConfigBeans = { "spring/batch/jobs/job-partitioner.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfigBeans);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("partitionJob");

		try {

			//JobParameters param = new JobParametersBuilder().addString("age", "20").toJobParameters();

			JobExecution execution = jobLauncher.run(job, new JobParameters());
			LOGGER.info("Exit Status : " + execution.getStatus());
			LOGGER.info("Exit Status : " + execution.getAllFailureExceptions());

		} catch (Exception e) {
			LOGGER.error("Error: " + e.getMessage());
		}
		LOGGER.info("Done");
	}
}
