package com.mas.batch;

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
		
		String[] springBatchConfig = 
			{ "spring/batch/jobs/job-batch.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springBatchConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("masJobBatch");

		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			LOGGER.info("Status: " + execution.getStatus());
			
		} catch (Exception e) {
			LOGGER.error("" + e.getMessage());
		}
		LOGGER.info("Done");
	}
}
