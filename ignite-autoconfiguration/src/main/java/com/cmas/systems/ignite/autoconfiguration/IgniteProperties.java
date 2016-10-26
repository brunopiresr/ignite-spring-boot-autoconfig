package com.cmas.systems.ignite.autoconfiguration;

import java.util.List;
import java.util.Map;

import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="spring.apache.ignite")
public class IgniteProperties {
	
	private Boolean clientMode = Boolean.FALSE;
	
	private Cluster cluster;
	
	private Unicast unicast;
	
	private Multicast multicast;
	
	private String workingDirectory;
	
	private long metricsLogFrequency = IgniteConfiguration.DFLT_METRICS_LOG_FREQ;
	
	public long getMetricsLogFrequency() {
		return metricsLogFrequency;
	}
	
	public void setMetricsLogFrequency(long metricsLogFrequency) {
		this.metricsLogFrequency = metricsLogFrequency;
	}
	
	public String getWorkingDirectory() {
		return workingDirectory;
	}
	
	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}
	
	public Unicast getUnicast() {
		return unicast;
	}
	
	public void setUnicast(Unicast unicast) {
		this.unicast = unicast;
	}
	
	public Multicast getMulticast() {
		return multicast;
	}
	
	public void setMulticast(Multicast multicast) {
		this.multicast = multicast;
	}
	
	public Cluster getCluster() {
		return cluster;
	}
	
	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}
	
	public Boolean getClientMode() {
		return clientMode;
	}
	
	public void setClientMode(Boolean clientMode) {
		this.clientMode = clientMode;
	}

	public static class Unicast{
		
		private List<String> nodes;
		
		public List<String> getNodes() {
			return nodes;
		}
		
		public void setNodes(List<String> nodes) {
			this.nodes = nodes;
		}
	}
	
	public static class Multicast{
		
		private String group;
		
		private Integer port;
		
		public Integer getPort() {
			return port;
		}
		
		public void setPort(Integer port) {
			this.port = port;
		}
		
		public String getGroup() {
			return group;
		}
		
		public void setGroup(String group) {
			this.group = group;
		}
	}
	
	public static class Cluster{
		
		private Integer localPort = Integer.valueOf(47500);
		
		private Integer localPortRange = Integer.valueOf(100);
		
		private Map<String, String> attributes;

		public Integer getLocalPort() {
			return localPort;
		}

		public void setLocalPort(Integer localPort) {
			this.localPort = localPort;
		}

		public Integer getLocalPortRange() {
			return localPortRange;
		}

		public void setLocalPortRange(Integer localPortRange) {
			this.localPortRange = localPortRange;
		}

		public Map<String, String> getAttributes() {
			return attributes;
		}

		public void setAttributes(Map<String, String> attributes) {
			this.attributes = attributes;
		}
	}
}
