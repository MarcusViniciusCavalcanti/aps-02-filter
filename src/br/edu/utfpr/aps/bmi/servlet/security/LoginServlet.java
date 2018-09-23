package br.edu.utfpr.aps.bmi.servlet.security;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(
        name = "LoginServlet",
        value = { "/login"},
        initParams = {
                @WebInitParam(name = "username", value = "root"),
                @WebInitParam(name = "password", value = "qwerty")
        }
)
public class LoginServlet extends HttpServlet {
    private Credential credential;

    private final static String IS_LOGGED_IN        = "isLoggedIn";
    private final static String USERNAME_DEFAULT    = "USERNAME_DEFAULT";
    private final static String PASSWORD_DEFAULT    = "PASSWORD_DEFAULT";
    private final static Integer CYCLE_LIFE_SESSION = 60;

    public final static String COOKIE_NAME = "BMI_SESSION_RESULTS";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String username = config.getServletContext().getInitParameter(USERNAME_DEFAULT);
        String password = config.getServletContext().getInitParameter(PASSWORD_DEFAULT);

        credential = new Credential(username, password);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                                                        throws ServletException, IOException {
        String usernameParams = request.getParameter("username");
        String passwordParams = request.getParameter("password");

        boolean isValidCredentials = credential.isValidCredentials(usernameParams, passwordParams);

        if (isValidCredentials) {
            prepareSession(request);
            createCookie(response);

            request.getRequestDispatcher("/WEB-INF/form.html").forward(request, response);
        }
    }

    private void prepareSession(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.isNew()) {
            session.setAttribute(IS_LOGGED_IN, true);
        }

        session.setMaxInactiveInterval(CYCLE_LIFE_SESSION);
    }

    private void createCookie(HttpServletResponse response) {
        Date now = new Date();
        Cookie dateCookie = new Cookie(COOKIE_NAME, new SimpleDateFormat("dd-MM-yyyy").format(now));
        dateCookie.setMaxAge(-1);
        response.addCookie(dateCookie);
    }
}
