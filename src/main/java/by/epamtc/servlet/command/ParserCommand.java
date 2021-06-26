package by.epamtc.servlet.command;

import by.epamtc.exception.CandyParsingException;
import by.epamtc.parser.AbstractCandyParser;
import by.epamtc.parser.CandyParserFactory;
import by.epamtc.servlet.Command;
import org.apache.logging.log4j.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class ParserCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (request.getMethod().equals("POST")) {
            Part filePart = request.getPart("target");
            InputStream fileContent = filePart.getInputStream();
            AbstractCandyParser parser =
                    null;
            try {
                parser = CandyParserFactory.createCandyParser(request.getParameter("parser"));
                parser.parseCandies(fileContent);
            } catch (CandyParsingException e) {
                logger.log(Level.ERROR, "Error while parsing " + e.getMessage());
            }
            request.getSession().setAttribute("candies", parser.getCandies());
            response.sendRedirect("http://localhost:8080/disp?command=main");
        } else if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher("/jsp/parser.jsp").forward(request, response);
        }
    }

}