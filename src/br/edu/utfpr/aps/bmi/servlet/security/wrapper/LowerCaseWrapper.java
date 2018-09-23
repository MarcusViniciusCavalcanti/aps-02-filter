package br.edu.utfpr.aps.bmi.servlet.security.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class LowerCaseWrapper extends HttpServletRequestWrapper {
    public LowerCaseWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        return super.getParameter(name).toLowerCase();
    }
}
