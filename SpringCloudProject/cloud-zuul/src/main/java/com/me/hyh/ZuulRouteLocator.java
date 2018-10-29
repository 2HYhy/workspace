package com.me.hyh;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/8/20
 * 获取数据库+配置文件的所有路由信息
 * 若忽略网关前缀，会将path后面的内容拼接在url后面，若不忽略网关前缀，则将path及请求后面的内容拼接在url后面
 */
public class ZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator{

    private JdbcTemplate jdbcTemplate;
    private ZuulProperties properties;

    public ZuulRouteLocator(String servletPath, ZuulProperties properties, JdbcTemplate template) {
        super(servletPath, properties);
        this.jdbcTemplate = template;
        this.properties = properties;

    }

    @Override
    public void refresh() {
        //间隔一定时间段动态刷新路由
        doRefresh();
    }

    @Override
    public Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        System.out.println("---加载路由信息---"+ new Date());
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        //加载配置文件中的路由
        routesMap.putAll(super.locateRoutes());
        //加载数据库中的路由
        routesMap.putAll(this.locateRoutesFromDB());
        //用于存放规范、优化后的路由信息
        LinkedHashMap<String, ZuulProperties.ZuulRoute> results = new LinkedHashMap<>();
        for(Map.Entry<String, ZuulProperties.ZuulRoute> map : routesMap.entrySet()) {
            String path = map.getKey();
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            //判断配置文件中是否设置了prefix，有的话，需要添在所有路由前面
            if (org.springframework.util.StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            System.out.println("locateRoutes is : " + path + " , "+map.getValue().getUrl()+" , "+map.getValue().getServiceId());
            results.put(path, map.getValue());
        }
        return results;
    }

    private Map<String, ZuulProperties.ZuulRoute> locateRoutesFromDB() {
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        //使用jdbcTemplate代替dao和mybatis的sql语句
        List<ZuulRouteDO> zuulRouteDOList = jdbcTemplate.query("select * from t_route", new BeanPropertyRowMapper<>(ZuulRouteDO.class));
        for (ZuulRouteDO zuul : zuulRouteDOList) {
            //若path或者url为null就跳过，只有serviceId时，请求转发肯定是交给eureka的，否则没有具体的地址就会报错
          if (StringUtils.isBlank(zuul.getPath()) || StringUtils.isBlank(zuul.getUrl())) {
              //跳过此次循环，开始下一次循环
              continue;
          }
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            try {
                //将全部有效的route复制到zuulRoute中
                org.springframework.beans.BeanUtils.copyProperties(zuul, zuulRoute);
            } catch (Exception e) {
                System.out.println("加载路由信息发生异常:" + e.getMessage());
                e.printStackTrace();
            }
            routes.put(zuulRoute.getPath(), zuulRoute);
        }
        return routes;
    }

    private static class ZuulRouteDO {
        private String id;
        private String path;
        private String serviceId;
        private String url;
        private Boolean stripPrefix;
        private Boolean retryable;

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

        public Boolean getRetryable() {
            return retryable;
        }

        public void setRetryable(Boolean retryable) {
            this.retryable = retryable;
        }

        @Override
        public String toString() {
            return "ZuulRouteDO{" +
                    "id=" + id +
                    ", path='" + path + '\'' +
                    ", serviceId='" + serviceId + '\'' +
                    ", url='" + url + '\'' +
                    ", stripPrefix=" + stripPrefix +
                    ", retryable=" + retryable +
                    '}';
        }
    }
}
