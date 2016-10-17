package com.risuit.ignite;

import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

@SpringBootApplication
public class IgniteServerApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(IgniteServerApplication.class, args);
		IgniteConfiguration cfg = ctx.getBean( IgniteConfiguration.class );
		Assert.notNull( cfg );
	}
}