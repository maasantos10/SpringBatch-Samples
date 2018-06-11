package com.mas.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mas.batch.model.CriptoEvent;
import com.mas.batch.model.Trade;

import org.springframework.batch.item.ItemProcessor;

/***
 * 
 * @author Marcos Santos
 * description: Spring Batch sample with aggregation of values from one csv file 
 * and multithreading 
 *
 */

public class CriptoEventProcessor implements ItemProcessor<CriptoEvent, Trade> {

	private static final Logger log = LoggerFactory.getLogger(CriptoEventProcessor.class);
	
	@Override
	public Trade process(final CriptoEvent criptoEvent) throws Exception {

		final String stock = criptoEvent.getStock();
		final String time = criptoEvent.getTime();
		final double price = Double.valueOf(criptoEvent.getPrice());
		final long shares = Long.valueOf(criptoEvent.getShares());
		final Trade trade = new Trade(stock, time, price, shares);

		log.trace("Converting (" + criptoEvent + ") into (" + trade + ")");
		
		return trade;
	}
}
