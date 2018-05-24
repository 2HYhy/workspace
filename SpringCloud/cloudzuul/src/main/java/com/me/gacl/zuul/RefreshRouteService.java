package com.me.gacl.zuul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author CH-yfy
 * @date 2018/4/16
 */

@Service
public class RefreshRouteService {

    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    RouteLocator locator;

    public void refreshRoute() {
        RoutesRefreshedEvent event = new RoutesRefreshedEvent(locator);
        publisher.publishEvent(event);
    }
}
