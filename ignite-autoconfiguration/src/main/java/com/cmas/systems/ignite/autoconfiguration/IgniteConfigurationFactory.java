package com.cmas.systems.ignite.autoconfiguration;

import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.logger.slf4j.Slf4jLogger;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

import com.cmas.systems.ignite.autoconfiguration.IgniteProperties.Cluster;

public class IgniteConfigurationFactory {

	private IgniteProperties properties;

	public IgniteConfigurationFactory() {

	}

	public IgniteConfigurationFactory(IgniteProperties properties) {
		this.properties = properties;
	}

	public IgniteConfiguration create() {
		IgniteConfiguration cfg = new IgniteConfiguration();

		if (properties == null) {
			return cfg;
		}
		
		// add logger
		cfg.setGridLogger(new Slf4jLogger());
		
		cfg.setClientMode(properties.getClientMode());

		if( properties.getCluster() != null ){
			addClusterProperties(cfg);	
		}
		
		if( properties.getMulticast() != null ){
			addMulticastConfiguration(cfg);
		}else if( properties.getUnicast() != null ){
			addUnicastConfiguration(cfg);
		}

		return cfg;
	}

	private void addMulticastConfiguration( IgniteConfiguration cfg ){
		TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
		 
		// Set Multicast group.
		ipFinder.setMulticastGroup( properties.getMulticast().getGroup() );
		ipFinder.setMulticastPort( properties.getMulticast().getPort()  );

		if( properties.getUnicast() != null ){
			ipFinder.setAddresses( properties.getUnicast().getNodes() );	
		}
		
		((TcpDiscoverySpi)cfg.getDiscoverySpi()).setIpFinder(ipFinder);
	}
	
	private void addUnicastConfiguration( IgniteConfiguration cfg ){
		TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
		 
		ipFinder.setAddresses( properties.getUnicast().getNodes() );	
		
		((TcpDiscoverySpi)cfg.getDiscoverySpi()).setIpFinder(ipFinder);
	}
	
	private void addClusterProperties(IgniteConfiguration cfg) {
		Cluster cluster = properties.getCluster();
		
		TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
		discoverySpi.setLocalPort(cluster.getLocalPort());
		discoverySpi.setLocalPortRange( cluster.getLocalPortRange() );
		
		cfg.setDiscoverySpi(discoverySpi);
		cfg.setUserAttributes( cluster.getAttributes() );
	}
}
