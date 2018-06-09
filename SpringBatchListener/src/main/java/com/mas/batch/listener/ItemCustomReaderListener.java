package com.mas.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

import com.mas.batch.model.Domain;

public class ItemCustomReaderListener implements ItemReadListener<Domain> {

	public static final Logger LOGGER = LoggerFactory.getLogger(ItemCustomReaderListener.class);
	
	@Override
	public void beforeRead() {
		LOGGER.info("ItemReadListener - beforeRead");
	}

	@Override
	public void afterRead(Domain item) {
		LOGGER.info("ItemReadListener - afterRead");
	}

	@Override
	public void onReadError(Exception ex) {
		LOGGER.error("ItemReadListener - onReadError");
	}

}
