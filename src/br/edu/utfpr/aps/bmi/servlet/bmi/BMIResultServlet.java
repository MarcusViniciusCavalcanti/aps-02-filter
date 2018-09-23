package br.edu.utfpr.aps.bmi.servlet.bmi;

import br.edu.utfpr.aps.bmi.servlet.security.LoginServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(value = "/protected/result")
public class BMIResultServlet extends HttpServlet {
    private static final String COUNTER = "COUNTER";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Double bmi = (Double) session.getAttribute(BMIServlet.BMI_RESULT);

        String category = checkCategory(bmi);


        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.printf("Seu IMC é %.2f e você está %s", bmi, category);
        out.print("<br><br><a href= \"logout\">Sair</a><br><br>");
        writeContentCookie(request, out);
    }

    private void writeContentCookie(HttpServletRequest request, PrintWriter writer) {

        List<Cookie> cookies = Arrays.asList(request.getCookies());

        cookies.stream()
                .flatMap(BMIResultServlet::apply)
                .collect(Collectors.toList())
                .forEach(c -> writer.print(c.getValue()));
    }

    private String checkCategory(Double bmi) {
        String category = null;

        if(bmi < 18.5) {
            category = "Abaixo do peso";
        }
        else if(bmi < 25) {
            category = "Com o peso normal. Parabéns!!!";
        }
        else if(bmi < 30) {
            category = "Acima do peso. Cuidado!!";
        }
        else {
            category = "Obeso";
        }
        return category;
    }

    private static Stream<Cookie> apply(Cookie cookie) {
        if (cookie.getName().equals(COUNTER)) {
            String value = cookie.getValue();
            Long counter = Long.valueOf(value);
            counter++;
            cookie.setValue("Quantidade de visita: "+ counter + "<br>");
            return Stream.of(cookie);
        } else if (cookie.getName().equals(LoginServlet.COOKIE_NAME)) {
            String value = cookie.getValue();
            cookie.setValue("Data da ultima visita: " + value);
            return Stream.of(cookie);

        }

        return Stream.of();
    }


}
