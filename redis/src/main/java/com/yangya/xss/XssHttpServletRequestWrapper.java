package com.yangya.xss;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author jiaxw
 * @date 2020/12/2 19:42
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        String value = request.getParameter(name);
        if (!StringUtils.isEmpty(value)) {
            value = XssUtil.htmlEncode(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        if (null == parameterValues) {
            return null;
        }
        for (int i = 0; i < parameterValues.length; i++) {
            String value = parameterValues[i];
            parameterValues[i] = XssUtil.htmlEncode(value);
        }
        return parameterValues;
    }

}
