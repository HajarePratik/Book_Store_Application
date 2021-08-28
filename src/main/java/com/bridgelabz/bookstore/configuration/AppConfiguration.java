package com.bridgelabz.bookstore.configuration;

import org.modelmapper.ModelMapper;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class AppConfiguration 
{
	// Rest Templete Configuration
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() 
	{
	     return new RestTemplate();
	}
	
	// Model Mapper Configuration
	@Bean
	public ModelMapper modelMapper() 
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}
