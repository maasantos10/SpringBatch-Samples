package com.mas.batch.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import com.mas.batch.model.Domain;

public class ItemCustomWriterListener implements ItemWriteListener<Domain> {

	public static final Logger LOGGER = LoggerFactory.getLogger(ItemCustomWriterListener.class);

	@Override
	public void afterWrite(List<? extends Domain> items) {
		LOGGER.info("ItemWriteListener - afterWrite");
	}

	@Override
	public void beforeWrite(List<? extends Domain> items) {
		LOGGER.info("ItemWriteListener - beforeWrite");
	}

	@Override
	public void onWriteError(Exception ex, List<? extends Domain> items) {
		LOGGER.info("ItemWriteListener - onWriteError");
		
	}
	
}
