package com.mas.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.mas.batch.model.Report;

/***
 * 
 * @author Marcos Santos
 * description: Spring Batch sample that read one XML file and generate a CSV file as result. 
 * 
 *
 */

//run before writing
public class FilterReportProcessor implements ItemProcessor<Report, Report> {

	@Override
	public Report process(Report item) throws Exception {

		//filter object which age = 30
		if(item.getAge()==30){
			return null; // null = ignore this object
		}
		return item;
	}

}