package com.mas.batch.adapter;

import java.math.BigDecimal;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/***
 * 
 * @author Marcos Santos
 * description: Spring Batch sample that read one XML file and generate a CSV file as result. 
 * 
 *
 */

public class JaxbBigDecimalAdapter extends XmlAdapter<String, BigDecimal> {

	@Override
	public String marshal(BigDecimal obj) throws Exception {
		
		return obj.toString();

	}

	@Override
	public BigDecimal unmarshal(String obj) throws Exception {
	
		return new BigDecimal(obj.replaceAll(",", ""));

	}

}