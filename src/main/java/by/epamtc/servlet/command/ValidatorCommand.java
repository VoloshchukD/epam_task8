package by.epamtc.servlet.command;

import by.epamtc.servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidatorCommand implements Command  {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            System.out.println(request.getMethod().equals("POST"));
//            response.sendRedirect("http://localhost:8080/disp?command=main");
        request.getRequestDispatcher("/jsp/validator.jsp").forward(request, response);
    }

}
