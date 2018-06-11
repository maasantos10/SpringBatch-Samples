package com.mas.batch.model;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/***
 * 
 * @author Marcos Santos
 * description: Spring Batch sample with aggregation of values from one csv file 
 * and multithreading 
 *
 */

public class CriptoVolumeStore {
	
	
	private ConcurrentMap<String, StockVolume> stockPrices = new ConcurrentHashMap<String, StockVolume>();

	public boolean containsKey(Object key) {
		//MAS
		return stockPrices.containsKey(key);
	}

	public StockVolume put(String key, StockVolume value) {
		//MAS
		return stockPrices.put(key, value);
	}

	public Collection<StockVolume> values() {
		//MAS
		return stockPrices.values();
	}

	public StockVolume get(Object key) {
		//MAS
		return stockPrices.get(key);
	}

}