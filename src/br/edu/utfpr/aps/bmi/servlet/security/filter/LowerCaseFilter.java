package br.edu.utfpr.aps.bmi.servlet.security.filter;

import br.edu.utfpr.aps.bmi.servlet.security.wrapper.LowerCaseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(
        filterName = "SecondaryFilterConfigureParams",
        description = "wrapper that alter the name parameter name to lower case",
        urlPatterns = "/login",
        servletNames = "LoginServlet"
)
public class LowerCaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                                                                                throws IOException, ServletException {
        HttpServletRequest request = new LowerCaseWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
