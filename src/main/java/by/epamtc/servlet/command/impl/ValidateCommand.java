package by.epamtc.servlet.command.impl;

import by.epamtc.exception.CandyParsingException;
import by.epamtc.servlet.command.Command;
import by.epamtc.validator.CandyValidator;
import org.apache.logging.log4j.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class ValidateCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

        response.sendRedirect("/controller?command=main");
    }

}
