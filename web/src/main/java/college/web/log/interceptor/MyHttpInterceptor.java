package college.web.log.interceptor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * User: EDY
 * Date: 2024/5/20
 * Time: 14:33
 * Version:V1.0
 */
@Slf4j
public class MyHttpInterceptor implements HandlerInterceptor {
    public static final String ATTRIBUTE_HANDLER_METHOD = "HANDLER_METHOD";

    private static final String ATTRIBUTE_STOP_WATCH = "ApiAccessLogInterceptor.StopWatch";

    private static String getBody(HttpServletRequest request) {
        // 只有在 json 请求在读取，因为只有 CacheRequestBodyFilter 才会进行缓存，支持重复读取
        if (StrUtil.startWithIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
            return JakartaServletUtil.getBody(request);
        }
        return null;
    }


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String> queryString = JakartaServletUtil.getParamMap(request);
        String requestBody = StrUtil.startWithIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE)
                ? getBody(request) : null;
        if (CollUtil.isEmpty(queryString) && StrUtil.isEmpty(requestBody)) {
            log.info("[preHandle][开始请求 URL({}) 无参数]", request.getRequestURI());
        } else {
            log.info("[preHandle][开始请求 URL({}) 参数({})]", request.getRequestURI(),
                    StrUtil.nullToDefault(requestBody, queryString.toString()));
        }
        // 计时
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        request.setAttribute(ATTRIBUTE_STOP_WATCH, stopWatch);

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println(handler);
        System.out.println(modelAndView);
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        StopWatch stopWatch = (StopWatch) request.getAttribute(ATTRIBUTE_STOP_WATCH);
        stopWatch.stop();
        log.info("[afterCompletion][完成请求 URL({}) 耗时({} ms)]",
                request.getRequestURI(), stopWatch.getTotalTimeMillis());
    }
}
