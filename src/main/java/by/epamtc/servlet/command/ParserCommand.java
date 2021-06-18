package by.epamtc.servlet.command;

import by.epamtc.parser.AbstractCandyParser;
import by.epamtc.parser.CandyParserFactory;
import by.epamtc.parser.dom.CandyDomParser;
import by.epamtc.servlet.Command;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class ParserCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equals("POST")) {
            Part filePart = request.getPart("target");
            InputStream fileContent = filePart.getInputStream();
            AbstractCandyParser parser =
                    CandyParserFactory.createCandyParser(request.getParameter("parser"));
            parser.parseCandies(fileContent);
            request.getSession().setAttribute("candies", parser.getCandies());

            response.sendRedirect("http://localhost:8080/disp?command=main");
        } else if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher("/jsp/parser.jsp").forward(request, response);
        }
    }

}