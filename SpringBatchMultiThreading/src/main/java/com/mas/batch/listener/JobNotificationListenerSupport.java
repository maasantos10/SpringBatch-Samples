package com.mas.batch.listener;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.mas.batch.model.CriptoVolumeStore;
import com.mas.batch.model.StockVolume;

/***
 * 
 * @author Marcos Santos
 * description: Spring Batch sample with aggregation of values from one csv file 
 * and multithreading 
 *
 */

public class JobNotificationListenerSupport extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobNotificationListenerSupport.class);

	private static final String HEADER = "stock,volume";

	private static final String LINE_DILM = ",";

	@Autowired
	private CriptoVolumeStore criptoVolumeStore;

	@Override
	public void afterJob(JobExecution jobExecution) {
		
		//MAS
		log.info("{ } @MAS - class JobCompletionNotificationListener extends JobExecutionListenerSupport - afterJob()..");
		
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.trace("Loading the results into file");
			Path path = Paths.get("volume.csv");
			try (BufferedWriter fileWriter = Files.newBufferedWriter(path)) {
				fileWriter.write(HEADER);
				fileWriter.newLine();
				for (StockVolume pd : criptoVolumeStore.values()) {
					fileWriter.write(new StringBuilder().append(pd.getStock())
							.append(LINE_DILM).append(pd.getVolume()).toString());
					fileWriter.newLine();
				}
			} catch (Exception e) {
				log.error("Fatal error: error occurred while writing {} file", path.getFileName());
			}
		}
	}
}
