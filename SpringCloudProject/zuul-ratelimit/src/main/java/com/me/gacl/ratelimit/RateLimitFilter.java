package com.me.gacl.ratelimit;

import com.me.gacl.ratelimit.pojo.Policy;
import com.me.gacl.ratelimit.pojo.Rate;
import com.me.gacl.ratelimit.pojo.RateLimitProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import static com.me.gacl.ratelimit.pojo.Policy.Type.*;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.X_FORWARDED_FOR_HEADER;
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

/**
 * @author CH-yfy
 * @date 2018/5/31
 * 限流过滤器
 */
public class RateLimitFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(RateLimitFilter.class);
    private static final String LIMIT_HEADER = "X-RateLimit-Limit";
    private static final String REMAINING_HEADER = "X-RateLimit-Remaining";
    private static final String RESET_HEADER = "X-RateLimit-Reset";

    private final RateLimiter rateLimiter;
    private final RateLimitProperties properties;
    private final RouteLocator routeLocator;

    public RateLimitFilter(RateLimiter rateLimiter,
                           RateLimitProperties properties,
                           RouteLocator routeLocator) {
        this.rateLimiter = rateLimiter;
        this.properties = properties;
        this.routeLocator = routeLocator;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
       return this.properties.isEnabled() && policy().isPresent();
    }

    @Override
    public Object run() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletResponse response = ctx.getResponse();
        final HttpServletRequest request = ctx.getRequest();

        policy().ifPresent(policy -> {
            final Rate rate = this.rateLimiter.consume(policy, key(request, policy.getType()));
            response.setHeader(LIMIT_HEADER, rate.getLimit().toString());
            response.setHeader(REMAINING_HEADER, String.valueOf(Math.max(rate.getRemaining(), 0)));
            response.setHeader(RESET_HEADER, rate.getReset().toString());
            if (rate.getRemaining() < 0) {
                ctx.setResponseStatusCode(TOO_MANY_REQUESTS.value());
                ctx.put("rateLimitExceeded", "true");
                logger.warn("----单位时间内请求数量超过限制, 请求上限数为{}-----", rate.getLimit());
                throw new ZuulRuntimeException(new ZuulException(TOO_MANY_REQUESTS.toString(), TOO_MANY_REQUESTS.value(), null));
            }
        });
        logger.info("---------成功通过流量过滤------");
        return null;
    }

    /**
     * 获取请求路由信息
     * @return
     */
    private Route route() {
        String requestURI = new UrlPathHelper().getPathWithinApplication(RequestContext.getCurrentContext().getRequest());
        return this.routeLocator.getMatchingRoute(requestURI);
    }

    /**
     * 获取限流策略
     * @return
     */
    private Optional<Policy> policy() {
        return (route() != null) ? Optional.ofNullable(this.properties.getPolicies().get(route().getId())) : Optional.empty();
    }

    /**
     * 生成唯一的请求标识
     * @param request
     * @param types
     * @return
     */
    private String key(final HttpServletRequest request, final List<Policy.Type> types) {
        final Route route = route();
        final StringJoiner joiner = new StringJoiner(":");
        joiner.add(route.getId());
        if (!types.isEmpty()) {
            if (types.contains(URL)) {
                joiner.add(route.getPath());
            }
            if (types.contains(ORIGIN)) {
                joiner.add(getRemoteAddr(request));
            }
            if (types.contains(USER)) {
                joiner.add(request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "anonymous");
            }
        }
        return joiner.toString();
    }

    private String getRemoteAddr(final HttpServletRequest request) {
        String xForwardedFor = request.getHeader(X_FORWARDED_FOR_HEADER);
        if (xForwardedFor != null) {
            return xForwardedFor;
        }
        return request.getRemoteAddr();
    }
}
