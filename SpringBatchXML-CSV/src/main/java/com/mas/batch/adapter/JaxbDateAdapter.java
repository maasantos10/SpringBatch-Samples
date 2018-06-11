package com.mas.batch.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/***
 * 
 * @author Marcos Santos
 * description: Spring Batch sample that read one XML file and generate a CSV file as result. 
 * 
 *
 */

// sample based on the article: 
// http://loianegroner.com/2011/06/jaxb-custom-binding-java-util-date-spring-3-serialization/
public class JaxbDateAdapter extends XmlAdapter<String, Date> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public String marshal(Date date) throws Exception {
		return dateFormat.format(date);
	}

	@Override
	public Date unmarshal(String date) throws Exception {
		return dateFormat.parse(date);
	}

}