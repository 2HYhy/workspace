package com.me.hyh;

import com.netflix.appinfo.AmazonInfo;
import com.netflix.appinfo.DataCenterInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Pair;
import com.netflix.eureka.EurekaServerContext;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author CH-yfy
 * 服务注册中心
 */
@EnableEurekaServer
@RestController
@SpringBootApplication
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

	@Value("${server.port}")
	private int port;
	@GetMapping("/server")
	public String function() {
		return "This is eureka-server, port = "+port;
	}

	/**
	 * eureka界面展示
	 * @return
	 */
	@RequestMapping(value = "eurekaMetrics", method = RequestMethod.GET)
	public Object status() {
		ArrayList<Map<String, Object>> result = this.getApps();
		return result;
	}
	private ArrayList<Map<String, Object>> getApps() {
		List<Application> sortedApplications = this.getRegistry().getSortedApplications();
		ArrayList<Map<String, Object>> apps = new ArrayList<>();
		for (Application app : sortedApplications) {
			LinkedHashMap<String, Object> appData = new LinkedHashMap<>();
			apps.add(appData);
			appData.put("name", app.getName());
			Map<String, Integer> amiCounts = new HashMap<>();
			Map<InstanceInfo.InstanceStatus, List<Pair<String, String>>> instancesByStatus = new HashMap<>();
			Map<String, Integer> zoneCounts = new HashMap<>();
			for (InstanceInfo info : app.getInstances()) {
				String id = info.getId();
				String url = info.getStatusPageUrl();
				InstanceInfo.InstanceStatus status = info.getStatus();
				String ami = "n/a";
				String zone = "";
				if (info.getDataCenterInfo().getName() == DataCenterInfo.Name.Amazon) {
					AmazonInfo dcInfo = (AmazonInfo) info.getDataCenterInfo();
					ami = dcInfo.get(AmazonInfo.MetaDataKey.amiId);
					zone = dcInfo.get(AmazonInfo.MetaDataKey.availabilityZone);
				}
				Integer count = amiCounts.get(ami);
				if (count != null) {
					amiCounts.put(ami, count + 1);
				} else {
					amiCounts.put(ami, 1);
				}
				count = zoneCounts.get(zone);
				if (count != null) {
					zoneCounts.put(zone, count + 1);
				} else {
					zoneCounts.put(zone, 1);
				}
				List<Pair<String, String>> list = instancesByStatus.get(status);
				if (list == null) {
					list = new ArrayList<>();
					instancesByStatus.put(status, list);
				}
				list.add(new Pair<>(id, url));
			}
			appData.put("amiCounts", amiCounts.entrySet());
			appData.put("zoneCounts", zoneCounts.entrySet());
			ArrayList<Map<String, Object>> instanceInfos = new ArrayList<>();
			appData.put("instanceInfos", instanceInfos);
			for (Iterator<Map.Entry<InstanceInfo.InstanceStatus, List<Pair<String, String>>>> iter = instancesByStatus
					.entrySet().iterator(); iter.hasNext(); ) {
				Map.Entry<InstanceInfo.InstanceStatus, List<Pair<String, String>>> entry = iter
						.next();
				List<Pair<String, String>> value = entry.getValue();
				InstanceInfo.InstanceStatus status = entry.getKey();
				LinkedHashMap<String, Object> instanceData = new LinkedHashMap<>();
				instanceInfos.add(instanceData);
				instanceData.put("status", entry.getKey());
				ArrayList<Map<String, Object>> instances = new ArrayList<>();
				instanceData.put("instances", instances);
				instanceData.put("isNotUp", status != InstanceInfo.InstanceStatus.UP);
				for (Pair<String, String> p : value) {
					LinkedHashMap<String, Object> instance = new LinkedHashMap<>();
					instances.add(instance);
					instance.put("id", p.first());
					instance.put("url", p.second());
					instance.put("isHref", p.second().startsWith("http"));
				}
			}
		}
		return apps;
	}
	private PeerAwareInstanceRegistry getRegistry() {
		//获取到所有界面的信息和全部注册实例
		PeerAwareInstanceRegistry peer = getServerContext().getRegistry();
		System.out.println("Lease expiration enabled="+peer.isLeaseExpirationEnabled());
		System.out.println("Renews (last min)="+peer.getNumOfRenewsInLastMin());
		System.out.println("Renews threshold="+peer.getNumOfRenewsPerMinThreshold());
		return peer;
	}
	private EurekaServerContext getServerContext() {
		return EurekaServerContextHolder.getInstance().getServerContext();
	}
}
