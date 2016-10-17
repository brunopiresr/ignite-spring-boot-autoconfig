package com.cmas.systems.ignite.autoconfiguration;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Ignite.class)
@ConditionalOnMissingBean(IgniteConfiguration.class)
@EnableConfigurationProperties({IgniteProperties.class})
public class IgniteConfigurationAutoConfiguration {

	private static final Logger logger = LoggerFactory.getLogger( IgniteConfigurationAutoConfiguration.class );
	
	private IgniteProperties properties;
	
	
	public IgniteConfigurationAutoConfiguration(IgniteProperties properties) {
		this.properties = properties;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public IgniteConfiguration igniteConfiguration(){
		logger.debug( "Creating IgniteConfiguration bean" );
		return new IgniteConfigurationFactory(properties).create();
	}
}
