package br.edu.utfpr.aps.bmi.servlet.security.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class TrimWrapper extends HttpServletRequestWrapper {

    public TrimWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        return super.getParameter(name).trim();
    }
}
