package com.mas.batch.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.mas.batch.model.Report;

public class ReportFieldSetMapper implements FieldSetMapper<Report>{
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ReportFieldSetMapper.class);
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public Report mapFieldSet(FieldSet fieldSet) throws BindException {
		
		Report report = new Report();
		report.setId(fieldSet.readInt(0));
		report.setAmount(fieldSet.readBigDecimal(1));
		report.setQuantity(fieldSet.readInt(2));
		report.setTeam(fieldSet.readString(3));
		
		//default format yyyy-MM-dd
		//fieldSet.readDate(4);
		String date = fieldSet.readString(4);
		try {
			report.setDate(dateFormat.parse(date));
		} catch (ParseException e) {
			LOGGER.error("Error: " + e.getMessage());
		}
		
		return report;
		
	}

}
