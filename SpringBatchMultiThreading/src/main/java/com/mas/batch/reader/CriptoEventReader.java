package com.mas.batch.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import com.mas.batch.model.CriptoEvent;

/***
 * 
 * @author Marcos Santos
 * description: Spring Batch sample with aggregation of values from one csv file 
 * and multithreading 
 *
 */

public class CriptoEventReader extends FlatFileItemReader<CriptoEvent> {

private static final Logger log = LoggerFactory.getLogger(CriptoEventReader.class);
	
	public CriptoEventReader() {
		
		//Set input file (it needs to change to dynamic path - MAS)
		this.setResource(new ClassPathResource("trades.csv"));
		
		//Skip the file header line
		this.setLinesToSkip(1);
		
		//Line is mapped to item (FxMarketEvent) using setLineMapper(LineMapper)
		this.setLineMapper(new DefaultLineMapper<CriptoEvent>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "stock", "time", "price", "shares" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<CriptoEvent>() {
					{
						setTargetType(CriptoEvent.class);
					}
				});
			}
		});
		
		//MAS
		log.info("{ } @MAS - class CriptoEventReader - CriptoEventReader()..");
	}
}
