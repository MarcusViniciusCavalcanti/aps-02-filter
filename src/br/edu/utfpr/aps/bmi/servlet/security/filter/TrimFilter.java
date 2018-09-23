package br.edu.utfpr.aps.bmi.servlet.security.filter;

import br.edu.utfpr.aps.bmi.servlet.security.wrapper.TrimWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(
        filterName = "PrimaryFilterConfigureParams",
        description = "wrapper that remove the name parameter name space empty",
        urlPatterns = "/login",
        servletNames = "LoginServlet"
)
public class TrimFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                                                                                throws IOException, ServletException {
        HttpServletRequest request = new TrimWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
