package com.mas.batch.tasklet;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class DeleteFileTasklet implements Tasklet, InitializingBean {

	private Resource directory;
	public static final Logger LOGGER = LoggerFactory.getLogger(DeleteFileTasklet.class);

	@Override
	public RepeatStatus execute(StepContribution stepContribution, 
			ChunkContext chunkContext) throws Exception {
		
		File dir = directory.getFile();
		Assert.state(dir.isDirectory());
		
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++ ) {
			boolean deleted = files[i].delete();
			if (!deleted) {
				//throw new UnexpectedJobExecutionException("Could not delete file " + files[i].getPath());
				LOGGER.error("Could not delete file " + files[i].getPath());
			} else {
				LOGGER.info(files[i].getPath() + " was deleted");
			}
		}
		
		return RepeatStatus.FINISHED;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(directory, "directory must be set");
	}
	
}
