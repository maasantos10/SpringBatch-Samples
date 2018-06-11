package com.mas.batch.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.mas.batch.listener.JobNotificationListenerSupport;
import com.mas.batch.model.CriptoEvent;
import com.mas.batch.model.CriptoVolumeStore;
import com.mas.batch.model.Trade;
import com.mas.batch.processor.CriptoEventProcessor;
import com.mas.batch.reader.CriptoEventReader;
import com.mas.batch.writer.StockVolumeAggregator;

/***
 * 
 * @author Marcos Santos
 * description: Spring Batch sample with aggregation of values from one csv file 
 * and multithreading 
 *
 */

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public CriptoVolumeStore criptoPricesStore() {
	
		return new CriptoVolumeStore();
	}
	
	// EventReader (Reader)
	@Bean
	public CriptoEventReader criptoEventReader() {
		//MAS
		return new CriptoEventReader();
	}

	// EventProcessor (Processor)
	@Bean
	public CriptoEventProcessor criptoEventProcessor() {
		//MAS
		return new CriptoEventProcessor();
	}

	// StockVolumeAggregator (Writer)
	@Bean
	public StockVolumeAggregator stockVolumeAggregator() {
		//MAS
		return new StockVolumeAggregator();
	}

	// JobCompletionNotificationListener (File loader)
	@Bean
	public JobExecutionListener listener() {
		//MAS
		return new JobNotificationListenerSupport();
	}

	// Configure job step
	@Bean
	public Job criptoPricesETLJob() {
		//MAS
		return jobBuilderFactory.get("Cripto Volume ETL Job").incrementer(new RunIdIncrementer()).listener(listener())
				.flow(etlStep()).end().build();
	}
	
	@Bean
	public TaskExecutor taskExecutor(){
		//MAS
	    SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor("spring_batch");
	    asyncTaskExecutor.setConcurrencyLimit(5);
	    return asyncTaskExecutor;
	}
    
	@Bean
	public Step etlStep() {
		//MAS
		return stepBuilderFactory.get("Extract -> Transform -> Aggregate -> Load").<CriptoEvent, Trade> chunk(10000)
				.reader(criptoEventReader()).processor(criptoEventProcessor())
				.writer(stockVolumeAggregator())
				.taskExecutor(taskExecutor()).build();
	}
	
	
}
