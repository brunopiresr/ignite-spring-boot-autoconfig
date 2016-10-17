package com.risuit.ignite;

import java.util.Arrays;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@SpringBootApplication
public class IgniteClientApplication {
	
	private static final Logger logger = LoggerFactory.getLogger( IgniteClientApplication.class );
	
	public static void main(String[] args) {
//		SpringApplication.run(IgniteClientApplication.class, args);
		
		TcpDiscoverySpi spi = new TcpDiscoverySpi();
		 
		TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
		 
		// Set initial IP addresses.
		// Note that you can optionally specify a port or a port range.
		ipFinder.setAddresses(Arrays.asList("localhost:47500..47509"));
		 
		spi.setIpFinder(ipFinder);
		 
		IgniteConfiguration cfg = new IgniteConfiguration();
		
		
//		cfg.setGridLogger( new Slf4jLogger( LoggerFactory.getLogger( "com.risuit.ignite.client.trace" )) );
		
		// Override default discovery SPI.
		cfg.setDiscoverySpi(spi);
		
		cfg.setClientMode( Boolean.TRUE );
		// Start Ignite node with given configuration.
		Ignite ignite = Ignition.start(cfg);
		
		
		
		IgniteCache<String, String> cache = ignite.getOrCreateCache( "test" );
		
		logger.info( "Cache value for key1 {}", cache.get( "key1" ) );
		
		String random = RandomStringUtils.randomAlphanumeric( 5 );
		logger.info( "Changing key value to {}", random );
		cache.put( "key1", random );
		
		ClusterGroup group = ignite.cluster().forRemotes().forAttribute("ROLE", "worker" );
		System.out.println( group.nodes().size() );
		System.exit( 0 );
	}
}
