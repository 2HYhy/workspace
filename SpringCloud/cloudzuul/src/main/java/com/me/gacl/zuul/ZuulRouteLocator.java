package com.me.gacl.zuul;

import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/4/16
 */
public class ZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator{

    private ZuulProperties properties;

    private JdbcTemplate jdbcTemplate;

    public ZuulRouteLocator(String servletPath, ZuulProperties properties, JdbcTemplate jdbcTemplate) {
        super(servletPath, properties);
        this.properties = properties;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        System.out.println(">>>---------加载路由信息-------->>>");
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        //从application.properties中加载路由信息
        routesMap.putAll(super.locateRoutes());
        //从数据库中加载路由信息
        routesMap.putAll(locateRoutesFromDB());
        //优化配置
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            System.out.println("locateRoutes ==>" + path + ", " + entry.getValue().getUrl() + ", " + entry.getValue().getServiceId());
            values.put(path, entry.getValue());
        }
        return values;
    }

    private Map<String, ZuulProperties.ZuulRoute> locateRoutesFromDB(){
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        //使用jdbcTemplate代替dao和mybatis的sql语句
        List<ZuulRouteDO> results = jdbcTemplate.query("select * from m_route where enabled = true", new BeanPropertyRowMapper<>(ZuulRouteDO.class));
        for(ZuulRouteDO route : results) {
            if(org.apache.commons.lang3.StringUtils.isBlank(route.getPath())
                    || org.apache.commons.lang3.StringUtils.isBlank(route.getUrl()) ){
                continue;
            }
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            try {
                org.springframework.beans.BeanUtils.copyProperties(route, zuulRoute);
            } catch (Exception e) {
                System.out.println("加载路由信息发生异常:" + e.getMessage());
                e.printStackTrace();
            }
            routes.put(zuulRoute.getPath(), zuulRoute);
        }
        return routes;
    }

    public static class ZuulRouteDO {
        private String id;
        private String path;
        private String serviceId;
        private String url;
        private Boolean stripPrefix;
        private Boolean retryAble;
        private Boolean enabled;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Boolean getStripPrefix() {
            return stripPrefix;
        }

        public void setStripPrefix(Boolean stripPrefix) {
            this.stripPrefix = stripPrefix;
        }

        public Boolean getRetryAble() {
            return retryAble;
        }

        public void setRetryAble(Boolean retryAble) {
            this.retryAble = retryAble;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        @Override
        public String toString() {
            return "ZuulRouteDO{" +
                    "id='" + id + '\'' +
                    ", path='" + path + '\'' +
                    ", serviceId='" + serviceId + '\'' +
                    ", url='" + url + '\'' +
                    ", stripPrefix=" + stripPrefix +
                    ", retryAble=" + retryAble +
                    ", enabled=" + enabled +
                    '}';
        }
    }
}
