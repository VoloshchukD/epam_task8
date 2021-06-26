package by.epamtc.servlet.command;

import by.epamtc.exception.CandyParsingException;
import by.epamtc.parser.AbstractCandyParser;
import by.epamtc.parser.CandyParserFactory;
import by.epamtc.servlet.Command;
import by.epamtc.validator.CandyValidator;
import org.apache.logging.log4j.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class ValidatorCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equals("POST")) {
            Part targetFilePart = request.getPart("target");
            Part schemaFilePart = request.getPart("schema");
            InputStream targetFileContent = targetFilePart.getInputStream();
            InputStream schemaFileContent = schemaFilePart.getInputStream();
            boolean valid = false;
            try {
                valid = CandyValidator.validate(targetFileContent, schemaFileContent);
            } catch (CandyParsingException e) {
                logger.log(Level.ERROR, "Error while parsing " + e.getMessage());
            }
            request.getSession().setAttribute("valid", valid);

            response.sendRedirect("http://localhost:8080/disp?command=main");
        } else if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher("/jsp/validator.jsp").forward(request, response);
        }
    }

}
