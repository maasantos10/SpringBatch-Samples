package com.mas.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 
 * @author Marcos Santos
 * description: Spring Batch sample that read one XML file and generate a CSV file as result. 
 * 
 *
 */

public class BatchApp {

	public final static Logger LOGGER = LoggerFactory.getLogger(BatchApp.class);
	
	public static void main(String[] args){
		
		String [] springConfig = { "spring/batch/config/context.xml", "spring/batch/jobs/job-report.xml" };
		
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		
		JobLauncher jobLauncher = (JobLauncher)context.getBean("jobLauncher");
		Job job = (Job) context.getBean("jobReportXmlCsv");
		
		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("Finish");
	}
}
