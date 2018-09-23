package br.edu.utfpr.aps.bmi.servlet.security.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(
        filterName = "CounterAccessFilter",
        description = "count access bmi calculator",
        servletNames = "LoginServlet",
        urlPatterns = "/login"
)
public class CounterAccessFilter implements Filter {
    private Long totalLoginFilter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        totalLoginFilter = (Long) filterConfig.getServletContext().getAttribute("TotalLoginFilter");

        if (totalLoginFilter == null) {
            filterConfig.getServletContext().setAttribute("TotalLoginFilter", 0L);
        }

        totalLoginFilter = (Long) filterConfig.getServletContext().getAttribute("TotalLoginFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                                                                                throws IOException, ServletException {
        synchronized (this) {
            totalLoginFilter++;
            servletRequest.getServletContext().setAttribute("TotalLoginFilter", totalLoginFilter);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        totalLoginFilter = null;
    }
}
