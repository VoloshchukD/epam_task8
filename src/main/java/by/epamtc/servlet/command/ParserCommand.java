package by.epamtc.servlet.command;

import by.epamtc.servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ParserCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("POST")) {
            System.out.println(request.getPart("target").getContentType());
            System.out.println(request.getParameter("target"));
            response.sendRedirect("http://localhost:8080/disp?command=main");
            return;
        }

        request.getRequestDispatcher("/jsp/parser.jsp").forward(request, response);
    }

}