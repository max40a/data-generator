package com.data.generator.interceptor;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Objects.nonNull;

public class RequestLogInterceptor extends HandlerInterceptorAdapter {
    private static final AtomicLong REQUEST_COUNTER = new AtomicLong();
    private static final String STOPWATCH_ATTR = "log_startTime";

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) {
        if (handler instanceof HandlerMethod) {
            Logger logger = getLogger((HandlerMethod) handler);
            request.setAttribute(STOPWATCH_ATTR, Stopwatch.createStarted());

            String requestString = createRequestString(request)
                .concat(" [req num: ")
                .concat(String.valueOf(REQUEST_COUNTER.incrementAndGet()))
                .concat("]");
            logger.info("BEGIN {}", requestString);
        }

        return true;
    }

    @Override
    public void postHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler,
        ModelAndView modelAndView
    ) {
        if (handler instanceof HandlerMethod) {
            Stopwatch sw = (Stopwatch) request.getAttribute(STOPWATCH_ATTR);
            request.removeAttribute(STOPWATCH_ATTR);

            if (nonNull(sw)) {
                Logger logger = getLogger((HandlerMethod) handler);
                logger.info("END: {} [{}]", createRequestString(request), sw.stop());
            }
        }
    }

    private String createRequestString(HttpServletRequest request) {
        return request.getMethod()
            .concat(" -> ")
            .concat(request.getRequestURI())
            .concat(nonNull(request.getQueryString()) ? "?" + request.getQueryString() : "");
    }

    private Logger getLogger(HandlerMethod handler) {
        return LoggerFactory.getLogger(handler.getBeanType());
    }
}
