package br.edu.utfpr.aps.bmi.servlet.security.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.function.BiPredicate;

@WebFilter(
        filterName = "CredentialsFilter",
        description = "check if credentials is content in parameters",
        urlPatterns = "/login",
        servletNames = "LoginServlet"
)
public class CredentialsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
                                                                                throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("username");

        BiPredicate<String, String> isValidCredentials = (u, p) -> u != null && p != null;

        boolean isValid = isValidCredentials
                .and((u, p) -> u.isEmpty() && p.isEmpty())
                .test(username, password);

        if (!isValid) {
            filterChain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("index.html").forward(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
