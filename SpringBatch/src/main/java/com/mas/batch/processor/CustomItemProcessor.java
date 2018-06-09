package com.mas.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.mas.batch.model.Report;

public class CustomItemProcessor implements ItemProcessor<Report, Report>{

	public static final Logger LOGGER = LoggerFactory.getLogger(CustomItemProcessor.class);
	
	@Override
	public Report process(Report item) throws Exception {
		
		LOGGER.info("Loading ....   " +  item);
		return item;
	}
	
}
