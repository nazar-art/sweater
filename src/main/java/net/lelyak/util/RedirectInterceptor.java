package net.lelyak.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Nazar Lelyak.
 */
public class RedirectInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (modelAndView != null) {

            String args = request.getQueryString() != null ? request.getQueryString() : "";
            String url = StringUtils.isNotBlank(args)
                    ? String.format("%s?%s", request.getRequestURI(), args)
                    : request.getRequestURI();

            response.setHeader("Turbolinks-Location", url);
        }
    }
}
