package college.web.log.config;

import college.web.enums.WebFilterOrderEnum;
import college.web.log.filter.WebAccessLogFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * User: EDY
 * Date: 2024/5/7
 * Time: 16:24
 * Version:V1.0
 */
@AutoConfiguration
public class MyWebLogAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "my.access-log", value = "enable", matchIfMissing = true) // 允许使用 yudao.access-log.enable=false 禁用访问日志
    public FilterRegistrationBean<WebAccessLogFilter> apiAccessLogFilter() {
        WebAccessLogFilter filter = new WebAccessLogFilter();
        return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
    }

    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }
}
