package br.edu.utfpr.aps.bmi.servlet.security.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(
        filterName = "SessionFilterExpire",
        description = "check if user session is valid",
        urlPatterns = "/protected/**"
)
public class SessionExpireFilter implements Filter {
    private static final String IS_LOGGED_IN = "isLoggedIn";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
                                                                                throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean isNew = request.getSession().isNew();
        Boolean attribute = (Boolean) request.getSession().getAttribute(IS_LOGGED_IN);
        Boolean isLoggedIn = Optional.ofNullable(attribute).orElse(false);

        boolean sessionIsValid = isNew ? false : isLoggedIn;

        if (sessionIsValid) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("index.html").forward(request, response);
        }

    }

    @Override
    public void destroy() {

    }

}
