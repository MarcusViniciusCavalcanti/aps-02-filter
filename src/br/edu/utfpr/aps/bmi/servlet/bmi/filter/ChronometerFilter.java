package br.edu.utfpr.aps.bmi.servlet.bmi.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(
        filterName = "ChronometerFilter",
        description = "calculate time of request",
        urlPatterns = "/protected/imc-calc",
        servletNames = "BMIServlet"
)
public class ChronometerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();

        filterChain.doFilter(servletRequest, servletResponse);

        long end = System.currentTimeMillis();

        long timeOfResponse = end - start;
        double formatTime = timeOfResponse / 1000.0;

        System.out.printf("Tempo de resposta %.3f em segundos", formatTime);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
