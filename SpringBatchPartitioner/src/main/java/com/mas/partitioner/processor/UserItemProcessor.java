package com.mas.partitioner.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mas.partitioner.model.User;

@Component("itemProcessor")
@Scope(value = "step")
public class UserItemProcessor implements ItemProcessor<User, User> {

	public static final Logger LOGGER = LoggerFactory.getLogger(UserItemProcessor.class);
	
	@Value("#{stepExecutionContext[name]}")
	private String threadName;
	
	@Override
	public User process(User item) throws Exception {
		
		LOGGER.info(threadName + " processing : " + item.getId() + " : " + item.getUsername());		
		return item;
	}
	
	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

}
