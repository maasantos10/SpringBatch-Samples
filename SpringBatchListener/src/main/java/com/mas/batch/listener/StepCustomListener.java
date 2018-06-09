package com.mas.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepCustomListener implements StepExecutionListener{
	
	public static final Logger LOGGER = LoggerFactory.getLogger(StepCustomListener.class);

	@Override
	public ExitStatus afterStep(StepExecution stepExec) {
		LOGGER.info("StepExecutionListener - beforafterStepeStep");
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExec) {
		LOGGER.info("StepExecutionListener - beforeStep");
		
	}
	
}
