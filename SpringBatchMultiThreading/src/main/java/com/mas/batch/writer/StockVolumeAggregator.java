package com.mas.batch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.mas.batch.model.CriptoVolumeStore;
import com.mas.batch.model.StockVolume;
import com.mas.batch.model.Trade;

/***
 * 
 * @author Marcos Santos
 * description: Spring Batch sample with aggregation of values from one csv file 
 * and multithreading 
 *
 */

public class StockVolumeAggregator implements ItemWriter<Trade> {

	@Autowired
	private CriptoVolumeStore criptotVolumeStore;

	private static final Logger log = LoggerFactory.getLogger(StockVolumeAggregator.class);

	@Override
	public void write(List<? extends Trade> trades) throws Exception {
		trades.forEach(trade -> {
			if (criptotVolumeStore.containsKey(trade.getStock())) {
				StockVolume stockVolume = criptotVolumeStore.get(trade.getStock());
				long newVolume = stockVolume.getVolume() + trade.getShares();
				//log.info("Increment stock volume {}", trade.getStock() + " - " + newVolume);
				stockVolume.setVolume(newVolume);
			} else {
				log.trace("Adding new stock {}", trade.getStock());
				criptotVolumeStore.put(trade.getStock(),
						new StockVolume(trade.getStock(), trade.getShares()));
			}
		});
		
		//MAS
		log.info("{ } @MAS - class StockVolumeAggregator implements ItemWriter<Trade> - write()..");
	}
	
}
