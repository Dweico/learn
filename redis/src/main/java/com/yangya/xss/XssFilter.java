package com.yangya.xss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter
@Component
public class XssFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/main/excludefilter", "/brand/**", "/logout", "/register")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length()).replaceAll("[/]+$", "");
        //boolean allowedPath = ALLOWED_PATHS.contains(path);
        //boolean allowedPath = matcher(path);
        if (matcher(path)) {
            System.out.println("这里是不需要处理的url进入的方法");
            chain.doFilter(req, res);
        } else {
            XssHttpServletRequestWrapper xssRequestWrapper = new XssHttpServletRequestWrapper(req);
            chain.doFilter(xssRequestWrapper, response);
        }
    }

    @Override
    public void destroy() {

    }

    private  boolean matcher(String path){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String str : ALLOWED_PATHS) {
            boolean match = antPathMatcher.match(str, path);
            if (match){
                return match;
            }
        }
   /*     ALLOWED_PATHS.forEach(a->{
            boolean match = antPathMatcher.match(path, a);
            if (match){
                return;
            }
        });*/
        return false;

    }

    /**
     * 过滤json类型的
     *
     * @param builder
     * @return
     */
    @Bean
    @Primary
    public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
        // 解析器
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        // 注册 xss 解析器
        SimpleModule xssModule = new SimpleModule("XssStringJsonSerializer");
        xssModule.addSerializer(new XssStringJsonSerializer());
        objectMapper.registerModule(xssModule);
        // 返回
        return objectMapper;
    }

}
