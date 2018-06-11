package com.mas.batch.model;

/***
 * 
 * @author Marcos Santos
 * description: Spring Batch sample with aggregation of values from one csv file 
 * and multithreading 
 *
 */

public class CriptoEvent {
	
    private String stock;
    private String time;
    private String price;
    private String shares;
    
    public CriptoEvent() {
    }

	public CriptoEvent(String stock, String time, String price, String shares) {
		super();
		this.stock = stock;
		this.time = time;
		this.price = price;
		this.shares = shares;
	}

	public String getStock() {
		return stock;
	}
	
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getShares() {
		return shares;
	}
	
	public void setShares(String shares) {
		this.shares = shares;
	}

	@Override
	public String toString() {
		return "FxMarketEvent [stock=" + stock + ", time=" + time + ", price=" + price + ", shares=" + shares + "]";
	}
}