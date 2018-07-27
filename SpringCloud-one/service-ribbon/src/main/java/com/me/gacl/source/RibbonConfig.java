package com.me.gacl.source;

import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.cloud.netflix.ribbon.DefaultServerIntrospector;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.ServerIntrospector;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Random;

/**
 * @author CH-yfy
 * @date 2018/7/27
 * 随机查询策略源码流程重现
 */
@Configuration
public class RibbonConfig implements LoadBalancerClient {
    Random rand;
    private SpringClientFactory clientFactory;

    public RibbonConfig(SpringClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public ServiceInstance choose(String serviceId) {
        Server server = getServer(serviceId);
        if(server == null) {
            return null;
        }
        return new RibbonLoadBalancerClient.RibbonServer(serviceId, server, isSecure(server, serviceId), serverIntrospector(serviceId).getMetadata(server));
    }

    private Server getServer(String serviceId) {
        ILoadBalancer loadBalancer = getLoadBalancer(serviceId);
        if (loadBalancer == null) {
            return null;
        }
        //该方法中调用了RandomRule.choose(ILoadBalancer lb, Object key)方法
        return loadBalancer.chooseServer("default");
    }

    private ILoadBalancer getLoadBalancer(String serviceId) {
        return this.clientFactory.getLoadBalancer(serviceId);
    }

    private boolean isSecure(Server server, String serviceId) {
        IClientConfig config = this.clientFactory.getClientConfig(serviceId);
        if (config != null) {
            Boolean isSecure = config.get(CommonClientConfigKey.IsSecure);
            if (isSecure != null) {
                return isSecure;
            }
        }

        return serverIntrospector(serviceId).isSecure(server);
    }

    private ServerIntrospector serverIntrospector(String serviceId) {
        ServerIntrospector serverIntrospector = this.clientFactory.getInstance(serviceId,
                ServerIntrospector.class);
        if (serverIntrospector == null) {
            serverIntrospector = new DefaultServerIntrospector();
        }
        return serverIntrospector;
    }


    /**
     * 在所有实例中进行随机选择的具体方法
     * @param lb
     * @param key
     * @return
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;
        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();
            int serverCount = allList.size();
            if (serverCount == 0) {
                return null;
            }
            int index = rand.nextInt(serverCount);
            server = upList.get(index);
            if (server == null) {
                Thread.yield();
                continue;
            }
            if (server.isAlive()) {
                return (server);
            }
            server = null;
            Thread.yield();
        }
        return server;
    }

    /**
     * 实现LoadBalancerClient的其他方法
     * @param serviceId
     * @param request
     * @param <T>
     * @return
     * @throws IOException
     */
    @Override
    public <T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException {
        return null;
    }

    @Override
    public <T> T execute(String serviceId, ServiceInstance serviceInstance, LoadBalancerRequest<T> request) throws IOException {
        return null;
    }

    @Override
    public URI reconstructURI(ServiceInstance instance, URI original) {
        return null;
    }
}
