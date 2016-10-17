package com.cmas.systems.ignite.autoconfiguration;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Ignite.class)
@ConditionalOnMissingBean(Ignite.class)
public class IgniteAutoConfiguration {
	
	private static final Logger logger = LoggerFactory.getLogger( IgniteAutoConfiguration.class );
	
	@Bean
	@ConditionalOnMissingBean( IgniteConfiguration.class )
	public Ignite ignite(){
		logger.debug( "Starting Ignite without configuration" );
		return Ignition.start();
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnBean( IgniteConfiguration.class )
	public Ignite ignite(IgniteConfiguration configuration){
		logger.debug( "Starting Ignite with configuration" );
		return Ignition.start(configuration);
	}
}
