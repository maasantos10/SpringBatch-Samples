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
		AppBatch obj = new AppBatch();
		obj.run();
	}

	private void run() {

		String[] springConfig = { "spring/batch/jobs/job-listener-read-files.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("jobReadMultiFileJob");

		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			LOGGER.info("Exit Status : " + execution.getStatus());
			LOGGER.error("Exit Status : " + execution.getAllFailureExceptions());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("Done");
	}
}
