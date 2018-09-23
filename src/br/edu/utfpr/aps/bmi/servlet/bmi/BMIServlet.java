package br.edu.utfpr.aps.bmi.servlet.bmi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BMIServlet", value = "/protected/imc-calc")
public class BMIServlet extends HttpServlet {
    public static final String BMI_RESULT = "BMI_RESULT";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String weightParams = request.getParameter("weight").replaceAll(",", ".");
        String heightParams = request.getParameter("height").replaceAll(",", ".");

        Double weight = Double.parseDouble(weightParams);
        Double height = Double.parseDouble(heightParams);

        Double result = weight/(height*height);

        request.getSession().setAttribute(BMI_RESULT, result);

        response.sendRedirect("/protected/result");
    }
}
